package com.devbcn.socialassistant.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record Category(
        @JsonProperty("id") int id,
        @JsonProperty("name") String name,
        @JsonProperty("categoryItems") List<CategoryItem> categoryItems,
        @JsonProperty("sort") int sort
) {
}
