package com.mycompany.collectorservice.mapper;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public class StringMapper {

    public String asString(CharSequence charSequence) {
        return charSequence != null ? charSequence.toString() : null;
    }

}
