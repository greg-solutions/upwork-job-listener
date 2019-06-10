package com.gs.common;

import com.gs.common.model.JobModel;

import java.util.Optional;

public interface JobTransportProvider {


    Optional<Model<JobModel>> get();

    void add(JobModel model);

    void delete(Model<JobModel> model);

}
