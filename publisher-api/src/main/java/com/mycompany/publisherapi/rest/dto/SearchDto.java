package com.mycompany.publisherapi.rest.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class SearchDto {

    @ApiModelProperty(value = "text to be searched", example = "Brazil")
    @NotBlank
    private String text;

}
