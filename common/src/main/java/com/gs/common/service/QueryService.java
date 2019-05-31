package com.gs.common.service;

import com.gs.common.model.QueryModel;
import com.gs.common.repository.QueryRepository;
import lombok.extern.slf4j.Slf4j;

import java.time.Instant;
import java.util.Optional;

@Slf4j
public class QueryService {

    private final QueryRepository queryRepository;

    public QueryService(QueryRepository queryRepository) {
        this.queryRepository = queryRepository;
    }

    public void add(QueryModel model) {
        queryRepository.save(model);
    }

    public Optional<QueryModel> getByUid(String query) {
        return queryRepository.findByQuery(query);
    }

    public Optional<QueryModel> getNexQuery() {
        return queryRepository.findTopByLastCallTimeLessThanOrderByLastCallTimeAsc(Instant.now());
    }

    public void update(QueryModel model) {
        if (model.getId() == null) {
            log.warn("Update is failed. Model should be persist before");
            return;
        }
        queryRepository.save(model);
    }

}
