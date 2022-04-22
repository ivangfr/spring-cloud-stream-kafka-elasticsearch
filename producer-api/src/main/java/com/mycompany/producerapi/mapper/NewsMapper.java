package com.mycompany.producerapi.mapper;

import com.mycompany.producerapi.model.News;
import com.mycompany.producerapi.rest.dto.CreateNewsRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface NewsMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "datetime", ignore = true)
    News toNews(CreateNewsRequest createNewsRequest);
}
