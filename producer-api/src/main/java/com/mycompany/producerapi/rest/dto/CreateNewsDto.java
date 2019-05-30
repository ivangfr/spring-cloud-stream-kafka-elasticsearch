package com.mycompany.producerapi.rest.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CreateNewsDto {

    @ApiModelProperty(value = "the title of the news", example = "Brazil")
    @NotBlank
    private String title;

    @ApiModelProperty(position = 2, value = "the text of the news", example = "This news is about Brasilia")
    @NotBlank
    private String text;

}
