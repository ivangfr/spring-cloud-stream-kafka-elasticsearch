package com.ivanfranchin.producerapi.news;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public record CreateNewsRequest(
        @Schema(title = "the title of the news", example = "Brazil") @NotBlank String title,
        @Schema(title = "the text of the news", example = "This news is about Brasilia") @NotBlank String text) {
}
