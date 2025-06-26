package com.devbcn.socialassistant.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;


public record Speaker(
        @JsonProperty("id") String id,
        @JsonProperty("firstName") String firstName,
        @JsonProperty("lastName") String lastName,
        @JsonProperty("fullName") String fullName,
        @JsonProperty("bio") String bio,
        @JsonProperty("tagLine") String tagLine,
        @JsonProperty("profilePicture") String profilePicture,
        @JsonProperty("sessions") List<SpeakerSession> speakerSessions,
        @JsonProperty("isTopSpeaker") boolean isTopSpeaker,
        @JsonProperty("links") List<Link> links,
        @JsonProperty("questionAnswers") List<Object> questionAnswers,
        @JsonProperty("categories") List<Object> categories
) {
}
