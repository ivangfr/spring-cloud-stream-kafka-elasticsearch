package com.mycompany.collectorservice.mapper;

import com.mycompany.collectorservice.model.News;
import com.mycompany.commonsnews.avro.NewsEvent;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = StringMapper.class)
public interface NewsMapper {

    News toNews(NewsEvent newsEvent);

}
