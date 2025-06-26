package com.devbcn.socialassistant.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record SessionSpeaker(
        @JsonProperty("id") String id,
        @JsonProperty("name") String name
) {
}
