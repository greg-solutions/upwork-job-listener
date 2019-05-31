package com.gs.common.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document(collection = "queries")
@EqualsAndHashCode(callSuper = true)
@Data
public class QueryModel extends PersistenceModel {
    String query;
    Instant lastCallTime = Instant.now();
}
