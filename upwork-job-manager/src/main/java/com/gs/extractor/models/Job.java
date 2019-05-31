package com.gs.extractor.models;

import com.gs.common.model.JobModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@EqualsAndHashCode(callSuper = true)
@Document(collection = "jobs")
public class Job extends JobModel {
    String url;
}

