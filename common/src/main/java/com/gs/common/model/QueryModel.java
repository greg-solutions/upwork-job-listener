package com.gs.common.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.Instant;

@EqualsAndHashCode(callSuper = true)
@Data
public class QueryModel extends PersistenceModel {
    String query;
    Instant lastCallTime = Instant.now();
}
