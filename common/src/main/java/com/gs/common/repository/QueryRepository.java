package com.gs.common.repository;

import com.gs.common.model.QueryModel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.Instant;
import java.util.Optional;

public interface QueryRepository extends MongoRepository<QueryModel, String> {

    Optional<QueryModel> findByQuery(String query);

    Optional<QueryModel> findTopByLastCallTimeLessThanOrderByLastCallTimeAsc(Instant time);


}