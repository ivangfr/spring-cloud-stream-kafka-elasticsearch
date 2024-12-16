package com.ivanfranchin.newsclient.news;

import com.ivanfranchin.newsclient.news.client.PublisherApiClient;
import com.ivanfranchin.newsclient.news.dto.MyPage;
import com.ivanfranchin.newsclient.news.dto.News;
import com.ivanfranchin.newsclient.news.dto.SearchRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@RequiredArgsConstructor
@Controller
public class NewsController {

    private final PublisherApiClient publisherApiClient;

    @GetMapping(value = {"/"})
    public String getNews(@RequestParam(required = false) Integer page,
                          @RequestParam(required = false) Integer size,
                          @RequestParam(required = false, defaultValue = "datetime,desc") String sort,
                          Model model) {
        model.addAttribute("searchRequest", new SearchRequest());
        model.addAttribute("newsList", publisherApiClient.listNewsByPage(page, size, sort));
        return "news";
    }

    @PostMapping("/search")
    public String searchNews(@RequestParam(required = false) Integer page,
                             @RequestParam(required = false) Integer size,
                             @RequestParam(required = false, defaultValue = "datetime,desc") String sort,
                             @ModelAttribute SearchRequest searchRequest,
                             Model model) {
        MyPage<News> result;
        if (searchRequest.getText().trim().isEmpty()) {
            result = publisherApiClient.listNewsByPage(page, size, sort);
        } else {
            result = publisherApiClient.searchNewsByPage(searchRequest, page, size, sort);
        }
        model.addAttribute("newsList", result);
        return "news";
    }
}
