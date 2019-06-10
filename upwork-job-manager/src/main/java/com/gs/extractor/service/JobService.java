package com.gs.extractor.service;

import com.gs.extractor.models.Job;
import com.gs.extractor.repository.JobRepository;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;
import java.util.Optional;

public class JobService {

    private final JobRepository jobRepository;

    public JobService(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    public void add(Job jobModel) {
        jobRepository.save(jobModel);
    }

    public List<Job> findByUids(List<String> uids) {
        return jobRepository.findByUidIn(uids);
    }

    public Optional<Job> getByUid(String uid) {
        return jobRepository.findByUid(uid);
    }
}
