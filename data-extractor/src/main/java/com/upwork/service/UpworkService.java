package com.upwork.service;

import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.upwork.JsonMapper;
import com.upwork.apis.UpworkApi;
import com.upwork.models.JobModel;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
public class UpworkService {
    private final UpworkApi upworkApi;

    private static final String JOB_ITEMS_XPATH = "//section[@class='air-card air-card-hover job-tile-responsive']";
    private static final String JOB_ITEM_DATA_ATTRIBUTE = "data-ng-init";

    public UpworkService(UpworkApi upworkApi) {
        this.upworkApi = upworkApi;
    }

    public List<JobModel> searchJob(String query) {
        Optional<HtmlPage> htmlPage = upworkApi.search(query);
        List<JobModel> jobs = new ArrayList<>();
        if (!htmlPage.isPresent()) {
            return jobs;
        }


        List<HtmlElement> items = (List<HtmlElement>) htmlPage.get().getByXPath(JOB_ITEMS_XPATH);
        if (items.isEmpty()) {
            System.out.println("No items found !");
        } else {
            for (HtmlElement item : items) {

                String resultJson = getJsonModel(item);
                JobModel jobModel = JsonMapper.getJob(resultJson, JobModel.class);
                jobs.add(jobModel);
                System.out.println();
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
