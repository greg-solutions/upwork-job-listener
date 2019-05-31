package com.gs.extractor.repository;

import com.gs.extractor.models.Job;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface JobRepository extends MongoRepository<Job, String> {

    Optional<Job> findByUid(String uid);

    List<Job> findByUidIn(List<String> uid);
}