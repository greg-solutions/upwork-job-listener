package com.gs.provider.hazelcast.repository;

import com.gs.common.Model;
import com.gs.common.model.JobModel;
import org.springframework.data.hazelcast.repository.HazelcastRepository;

import java.util.Optional;

public interface JobHazelcastRepository extends HazelcastRepository<Model<JobModel>, String> {
    Optional<Model<JobModel>> findFirstByOrderByIdAsc();
}