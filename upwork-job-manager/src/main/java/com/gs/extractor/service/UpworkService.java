package com.gs.extractor.service;

import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gs.extractor.models.Job;
import com.gs.extractor.upwork.UpworkApi;
import com.gs.extractor.utils.JsonMapper;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
public class UpworkService {
    private static final String JOB_ITEMS_XPATH = "//section[@class='air-card air-card-hover job-tile-responsive']";
    private static final String JOB_ITEM_DATA_ATTRIBUTE = "data-ng-init";
    private final UpworkApi upworkApi;

    public UpworkService(UpworkApi upworkApi) {
        this.upworkApi = upworkApi;
    }

    public List<Job> searchJob(String query) {
        Optional<HtmlPage> htmlPage = upworkApi.search(query);
        List<Job> jobs = new ArrayList<>();
        if (!htmlPage.isPresent()) {
            return jobs;
        }


        List<HtmlElement> items = (List<HtmlElement>) htmlPage.get().getByXPath(JOB_ITEMS_XPATH);
        List<HtmlElement> links = (List<HtmlElement>) htmlPage.get().getByXPath("//a[@class='job-title-link break visited']");
        if (items.isEmpty()) {
            System.out.println("No items found !");
        } else {
            for (int i = 0; i < items.size(); i++) {
                HtmlElement item = items.get(i);
                HtmlElement link = links.get(i);
                String resultJson = getJsonModel(item);
                Job jobModel = JsonMapper.getJob(resultJson, Job.class);
                jobModel.setUrl(link.getAttribute("href"));
                jobs.add(jobModel);
            }
        }
        return jobs;
    }

    private String getJsonModel(HtmlElement item) {
        String data = extractData(item);
        data = cleanData(data);
        return data;
    }

    private String extractData(HtmlElement item) {
        return item.getAttribute(JOB_ITEM_DATA_ATTRIBUTE);
    }

    private String cleanData(String attribute) {
        return attribute.replace("jobTileResponsiveCtrl.setJob(", "").replace(")", "");
    }
}
