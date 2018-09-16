package com.mycompany.newsclient.client;

import com.mycompany.newsclient.client.dto.MyPage;
import com.mycompany.newsclient.client.dto.News;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("publisher-api")
public interface PublisherApiClient {

    @GetMapping("/api/news")
    MyPage<News> listAllNewsByPage(@RequestParam Integer page, @RequestParam Integer size, @RequestParam String sort);

}
