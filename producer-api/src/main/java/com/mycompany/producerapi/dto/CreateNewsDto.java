package com.mycompany.producerapi.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class CreateNewsDto {

    @ApiModelProperty(value = "the title of the news", example = "Brazil")
    @NotNull
    @NotEmpty
    private String title;

    @ApiModelProperty(position = 1, value = "the text of the news", example = "This news is about Brasilia")
    @NotNull
    @NotEmpty
    private String text;

}
