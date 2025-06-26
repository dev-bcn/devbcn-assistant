package com.devbcn.socialassistant.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.List;

public record Session(
        @JsonProperty("questionAnswers") List<QuestionAnswer> questionAnswers,
        @JsonProperty("id") String id,
        @JsonProperty("title") String title,
        @JsonProperty("description") String description,
        @JsonProperty("startsAt") LocalDateTime startsAt,
        @JsonProperty("endsAt") LocalDateTime endsAt,
        @JsonProperty("isServiceSession") boolean isServiceSession,
        @JsonProperty("isPlenumSession") boolean isPlenumSession,
        @JsonProperty("speakers") List<Speaker> speakers,
        @JsonProperty("categories") List<Category> categories,
        @JsonProperty("roomId") int roomId,
        @JsonProperty("room") String room,
        @JsonProperty("liveUrl") String liveUrl,
        @JsonProperty("recordingUrl") String recordingUrl,
        @JsonProperty("status") String status,
        @JsonProperty("isInformed") boolean isInformed,
        @JsonProperty("isConfirmed") boolean isConfirmed
) {
}
