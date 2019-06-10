package com.gs.extractor;

import com.gs.common.GenericBuilder;
import com.gs.common.JobTransportProvider;
import com.gs.common.model.JobModel;
import com.gs.common.model.QueryModel;
import com.gs.common.service.QueryService;
import com.gs.extractor.models.Job;
import com.gs.extractor.service.JobService;
import com.gs.extractor.service.UpworkService;
import lombok.extern.slf4j.Slf4j;
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
    private final JobTransportProvider transportProvider;

    public JobListener(QueryService queryService, JobService jobService, UpworkService upworkService, JobTransportProvider transportProvider) {
        this.queryService = queryService;
        this.jobService = jobService;
        this.upworkService = upworkService;
        this.transportProvider = transportProvider;
    }

    @Scheduled(cron = "${scheduler.upwork}")
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
                .forEach(job -> {

                    if (!jobService.getByUid(job.getUid()).isPresent()) {
                        jobService.add(job);
                        log.info("New JOB. Job Title: [{}]", job.getTitle());
                        JobModel jobModel = GenericBuilder
                                .of(JobModel::new)
                                .with(JobModel::setCreatedOn, job.getCreatedOn())
                                .with(JobModel::setDescription, job.getDescription())
                                .with(JobModel::setTitle, job.getTitle())
                                .with(JobModel::setAmount, job.getAmount())
                                .with(JobModel::setSkills, job.getSkills())
                                .with(JobModel::setAttributes, job.getAttributes())
                                .with(JobModel::setCategory2, job.getCategory2())
                                .with(JobModel::setSubcategory2, job.getSubcategory2())
                                .with(JobModel::setCiphertext, job.getCiphertext())
                                .with(JobModel::setDuration, job.getDuration())
                                .with(JobModel::setQuery, queryModel.get().getQuery())
                                .with(JobModel::setJobUrl, JOB_LINK_URL + job.getUrl())
                                .build();

                        transportProvider.add(jobModel);
                    }

                });

        queryModel.get().setLastCallTime(Instant.now());
        queryService.update(queryModel.get());

    }
}
