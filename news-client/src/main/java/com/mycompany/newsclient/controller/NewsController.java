package com.mycompany.newsclient.controller;

import com.mycompany.newsclient.client.PublisherApiClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
public class NewsController {

    private final PublisherApiClient publisherApiClient;

    public NewsController(PublisherApiClient publisherApiClient) {
        this.publisherApiClient = publisherApiClient;
    }

    @GetMapping(value = {"/news", "/"})
    public String getNews(@RequestParam(required = false) Integer page,
                          @RequestParam(required = false) Integer size,
                          @RequestParam(required = false, defaultValue = "datetime,desc") String sort,
                          Model model) {
        model.addAttribute("newsList", publisherApiClient.listAllNewsByPage(page, size, sort));
        return "news";
    }

}
