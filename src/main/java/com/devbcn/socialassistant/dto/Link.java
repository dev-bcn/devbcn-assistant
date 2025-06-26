package com.devbcn.socialassistant.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Link(
        @JsonProperty("title") String title,
        @JsonProperty("url") String url,
        @JsonProperty("linkType") String linkType
) {
}
