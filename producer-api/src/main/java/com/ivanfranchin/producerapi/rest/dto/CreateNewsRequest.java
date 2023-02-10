package com.ivanfranchin.producerapi.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateNewsRequest {

    @Schema(title = "the title of the news", example = "Brazil")
    @NotBlank
    private String title;

    @Schema(title = "the text of the news", example = "This news is about Brasilia")
    @NotBlank
    private String text;
}
