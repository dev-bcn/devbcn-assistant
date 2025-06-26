package com.devbcn.socialassistant.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record SpeakerSession(
        @JsonProperty("id") int id,
        @JsonProperty("name") String name
) {
}
