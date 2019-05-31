package com.gs.extractor.upwork;

import lombok.extern.slf4j.Slf4j;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Slf4j
public class UrworkUrlBuilder {

    private static final String BASE_URL = "https://www.upwork.com/";
    private String JOB_SEARCH_PATH = "o/jobs/browse/?sort=recency&q=";

    private String url;

    public UrworkUrlBuilder(String query) {
        try {
            this.url = BASE_URL + JOB_SEARCH_PATH + URLEncoder.encode(query, "UTF-8");

        } catch (UnsupportedEncodingException e) {
            log.warn("Cant encode query: {}", query);
        }
    }

    public UrworkUrlBuilder withPageIndex(Integer pageIndex) {
        this.url = this.url + "&page=" + pageIndex;
        return this;
    }

    public String get() {
        return this.url;
    }
}
