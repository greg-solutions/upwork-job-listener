package com.upwork.repository;

import com.upwork.models.JobModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface JobRepository extends MongoRepository<JobModel, String> {

    public JobModel findByUid(String uid);

}