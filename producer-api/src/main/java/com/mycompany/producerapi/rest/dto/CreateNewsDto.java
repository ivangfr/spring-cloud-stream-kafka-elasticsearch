package com.mycompany.producerapi.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CreateNewsDto {

    @Schema(title = "the title of the news", example = "Brazil")
    @NotBlank
    private String title;

    @Schema(title = "the text of the news", example = "This news is about Brasilia")
    @NotBlank
    private String text;

}
