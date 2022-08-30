package com.ivanfranchin.collectorservice.mapper;

import com.ivanfranchin.collectorservice.model.News;
import com.ivanfranchin.commonsnews.avro.NewsEvent;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = StringMapper.class)
public interface NewsMapper {

    News toNews(NewsEvent newsEvent);
}
