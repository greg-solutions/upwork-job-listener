package com.gs.extractor.upwork;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.CookieManager;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Optional;

@Slf4j
public class UpworkApi {


    public Optional<HtmlPage> search(final String query) {
        return search(query, null);
    }

    public Optional<HtmlPage> search(final String query, Integer pageId) {
        String searchUrl;

        WebClient client = new WebClient(BrowserVersion.CHROME);

        client.getOptions().setCssEnabled(false);
        client.getOptions().setJavaScriptEnabled(false);

        searchUrl = new UrworkUrlBuilder(query).get();
        try {
            HtmlPage page = client.getPage(searchUrl);
            return Optional.of(page);

        } catch (IOException e) {

            log.error("Search is failed.", e);
        }
        return Optional.empty();
    }
}
