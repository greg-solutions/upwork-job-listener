package com.gs.common.hazelcast.model;

import com.gs.common.model.JobModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class JobCachedModel extends JobModel {

    private String jobUrl;
    private String query;
}
