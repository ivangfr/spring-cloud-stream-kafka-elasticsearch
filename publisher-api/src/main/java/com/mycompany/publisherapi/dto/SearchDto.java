package com.mycompany.publisherapi.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class SearchDto {

    @ApiModelProperty(value = "text to be searched", example = "Brazil")
    @NotNull
    @NotEmpty
    private String text;

}
