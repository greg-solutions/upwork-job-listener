package com.gs.api.controller;

import com.gs.api.request.QueryRequest;
import com.gs.common.model.QueryModel;
import com.gs.common.service.QueryService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v1/query")
public class QueryController {


    private final QueryService queryService;

    public QueryController(QueryService queryService) {
        this.queryService = queryService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public String addQuery(@RequestBody QueryRequest request) {
        if (queryService.getByUid(request.getQuery()).isPresent()) {
            return "Query already exists";
        }
        QueryModel queryModel = new QueryModel();
        queryModel.setQuery(request.getQuery());
        queryService.add(queryModel);
        return "OK";
    }
}
