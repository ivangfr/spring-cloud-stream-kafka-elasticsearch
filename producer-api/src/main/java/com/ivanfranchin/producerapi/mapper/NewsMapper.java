package com.ivanfranchin.producerapi.mapper;

import com.ivanfranchin.producerapi.model.News;
import com.ivanfranchin.producerapi.rest.dto.CreateNewsRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface NewsMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "datetime", ignore = true)
    News toNews(CreateNewsRequest createNewsRequest);
}
