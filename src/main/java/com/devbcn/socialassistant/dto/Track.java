package com.devbcn.socialassistant.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record Track(
        @JsonProperty("groupId") int groupId,
        @JsonProperty("groupName") String groupName,
        @JsonProperty("sessions") List<Session> sessions,
        @JsonProperty("isDefault") boolean isDefault
) {
}
