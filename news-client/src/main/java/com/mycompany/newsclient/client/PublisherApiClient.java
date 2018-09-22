package com.mycompany.newsclient.client;

import com.mycompany.newsclient.client.dto.MyPage;
import com.mycompany.newsclient.client.dto.News;
import com.mycompany.newsclient.client.dto.SearchDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("publisher-api")
public interface PublisherApiClient {

    @GetMapping("/api/news")
    MyPage<News> listNewsByPage(@RequestParam Integer page, @RequestParam Integer size,
                                @RequestParam String sort);

    @PutMapping("/api/news/search")
    MyPage<News> searchNewsByPage(@RequestBody SearchDto searchDto, @RequestParam Integer page,
                                  @RequestParam Integer size, @RequestParam String sort);

}
