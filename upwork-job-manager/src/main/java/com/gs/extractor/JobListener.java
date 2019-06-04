package com.gs.extractor;

import com.gs.common.GenericBuilder;
import com.gs.common.hazelcast.model.JobCachedModel;
import com.gs.common.hazelcast.repository.JobCacheRepository;
import com.gs.common.model.JobModel;
import com.gs.common.model.QueryModel;
import com.gs.common.service.QueryService;
import com.gs.extractor.models.Job;
import com.gs.extractor.service.JobService;
import com.gs.extractor.service.UpworkService;
import lombok.extern.slf4j.Slf4j;
import net.javacrumbs.shedlock.core.SchedulerLock;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
public class JobListener {

    private static final String JOB_LINK_URL = "https://www.upwork.com";
    private final QueryService queryService;
    private final JobService jobService;
    private final UpworkService upworkService;
    private final JobCacheRepository jobCacheRepository;
    private static final String FOURTEEN_MIN = "PT30S";

    public JobListener(QueryService queryService, JobService jobService, UpworkService upworkService, JobCacheRepository jobCacheRepository) {
        this.queryService = queryService;
        this.jobService = jobService;
        this.upworkService = upworkService;
        this.jobCacheRepository = jobCacheRepository;
    }

    @Scheduled(cron = "${scheduler.upwork}")
    @SchedulerLock(name = "jobListenerTask", lockAtMostForString = FOURTEEN_MIN, lockAtLeastForString = FOURTEEN_MIN)
    public void checkNewJob() {
        log.info("Check new job");

        Optional<QueryModel> queryModel = queryService.getNexQuery();
        if (!queryModel.isPresent()) {
            log.info("No have actual query");
            return;
        }
        log.info("Check jobs for: [{}]", queryModel.get().getQuery());
        //List of jobs from gs
        List<Job> jobs = upworkService.searchJob(queryModel.get().getQuery());

        //Collect uid list of job from response
        List<String> jobIds = jobs.stream().map(JobModel::getUid).collect(Collectors.toList());

        //Collect uid list of job from response contains in db
        List<String> persistedModelUids = jobService.findByUids(jobIds)
                .stream()
                .map(JobModel::getUid)
                .collect(Collectors.toList());

        jobs.stream()
                .filter(job -> !persistedModelUids.contains(job.getUid()))
                .forEach(jobModel -> {
                    if (!jobService.getByUid(jobModel.getUid()).isPresent()) {
                        jobService.add(jobModel);
                        log.info("New JOB. Job Title: [{}]", jobModel.getTitle());
                        JobCachedModel jobCachedModel = GenericBuilder
                                .of(JobCachedModel::new)
                                .with(JobCachedModel::setCreatedOn, jobModel.getCreatedOn())
                                .with(JobCachedModel::setDescription, jobModel.getDescription())
                                .with(JobCachedModel::setTitle, jobModel.getTitle())
                                .with(JobCachedModel::setAmount, jobModel.getAmount())
                                .with(JobCachedModel::setSkills, jobModel.getSkills())
                                .with(JobCachedModel::setAttributes, jobModel.getAttributes())
                                .with(JobCachedModel::setCategory2, jobModel.getCategory2())
                                .with(JobCachedModel::setSubcategory2, jobModel.getSubcategory2())
                                .with(JobCachedModel::setCiphertext, jobModel.getCiphertext())
                                .with(JobCachedModel::setDuration, jobModel.getDuration())
                                .with(JobCachedModel::setQuery, queryModel.get().getQuery())
                                .with(JobCachedModel::setJobUrl, JOB_LINK_URL + jobModel.getUrl())
                                .build();

                        jobCacheRepository.save(jobCachedModel);
                    }

                });

        queryModel.get().setLastCallTime(Instant.now());
        queryService.update(queryModel.get());

    }
}
