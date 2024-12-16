package com.ivanfranchin.publisherapi.news.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public record SearchRequest(@Schema(title = "text to be searched", example = "Brazil") @NotBlank String text) {
}
