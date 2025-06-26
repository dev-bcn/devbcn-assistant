package com.devbcn.socialassistant.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record QuestionAnswer(
        @JsonProperty("id") int id,
        @JsonProperty("question") String question,
        @JsonProperty("questionType") String questionType,
        @JsonProperty("answer") String answer,
        @JsonProperty("sort") int sort,
        @JsonProperty("answerExtra") Object answerExtra
) {
}
