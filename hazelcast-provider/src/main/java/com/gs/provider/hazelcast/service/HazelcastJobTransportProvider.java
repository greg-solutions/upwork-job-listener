package com.gs.provider.hazelcast.service;

import com.gs.common.JobTransportProvider;
import com.gs.common.Model;
import com.gs.common.model.JobModel;
import com.gs.provider.hazelcast.repository.JobHazelcastRepository;

import java.util.Optional;

public class HazelcastJobTransportProvider implements JobTransportProvider {

    private final JobHazelcastRepository repository;

    public HazelcastJobTransportProvider(JobHazelcastRepository repository) {
        this.repository = repository;
    }

    @Override
    public void add(JobModel model) {
        repository.save(new Model<>(model));
    }

    @Override
    public void delete(Model<JobModel> model) {
        repository.delete(model);
    }

    @Override
    public Optional<Model<JobModel>> get() {
        return  repository.findFirstByOrderByIdAsc();
    }
}
