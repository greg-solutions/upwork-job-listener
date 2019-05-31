package com.gs.common.hazelcast.repository;

import com.gs.common.hazelcast.model.JobCachedModel;
import org.springframework.data.hazelcast.repository.HazelcastRepository;

import java.util.Optional;

public interface JobCacheRepository extends HazelcastRepository<JobCachedModel, String> {


    Optional<JobCachedModel> findFirstByOrderByIdAsc();
}