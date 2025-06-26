package com.devbcn.socialassistant.service;

import com.devbcn.socialassistant.dto.Speaker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;

@ExtendWith(MockitoExtension.class)
class SpeakerServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @Spy
    @InjectMocks
    private SpeakerService speakerService;

    private Speaker[] mockSpeakers;

    @BeforeEach
    void setUp() {
        mockSpeakers = new Speaker[]{
                new Speaker("1", "John", "Doe", "John Doe", "Bio 1", "Tagline 1", "pic1.jpg", List.of(), false, List.of(), List.of(), List.of()),
                new Speaker("2", "Jane", "Smith", "Jane Smith", "Bio 2", "Tagline 2", "pic2.jpg", List.of(), true, List.of(), List.of(), List.of())
        };
    }

    @Test
    void fetchSpeakers_shouldReturnSpeakers_whenApiCallIsSuccessful() {
        doReturn(mockSpeakers).when(speakerService).fetchSpeakers();

        Speaker[] result = speakerService.fetchSpeakers();

        assertThat(result).isNotNull().hasSize(2);
        assertThat(result[0].fullName()).isEqualTo("John Doe");
        assertThat(result[1].fullName()).isEqualTo("Jane Smith");
    }

    @Test
    void fetchSpeakers_shouldThrowException_whenApiCallFails() {
        doThrow(new IllegalStateException("Failed to fetch speakers"))
                .when(speakerService).fetchSpeakers();

        assertThatExceptionOfType(IllegalStateException.class)
                .isThrownBy(() -> speakerService.fetchSpeakers())
                .withMessageContaining("Failed to fetch speakers");
    }

    @Test
    void fetchSpeaker_shouldReturnSpeaker_whenNameMatches() {
        doReturn(mockSpeakers[0]).when(speakerService).fetchSpeaker("John");

        Speaker result = speakerService.fetchSpeaker("John");

        assertThat(result).isNotNull();
        assertThat(result.fullName()).isEqualTo("John Doe");
    }

    @Test
    void fetchSpeaker_shouldReturnFirstMatchingSpeaker_whenMultipleNamesMatch() {
        Speaker[] speakersWithSimilarNames = new Speaker[]{
                new Speaker("1", "John", "Doe", "John Doe", "Bio 1", "Tagline 1", "pic1.jpg", List.of(), false, List.of(), List.of(), List.of()),
                new Speaker("2", "Johnny", "Smith", "Johnny Smith", "Bio 2", "Tagline 2", "pic2.jpg", List.of(), true, List.of(), List.of(), List.of())
        };

        doReturn(speakersWithSimilarNames[0]).when(speakerService).fetchSpeaker("John");

        Speaker result = speakerService.fetchSpeaker("John");

        assertThat(result).isNotNull();
        assertThat(result.fullName()).isEqualTo("John Doe");
    }

    @Test
    void fetchSpeaker_shouldReturnNull_whenNoNameMatches() {
        // Arrange
        doReturn(null).when(speakerService).fetchSpeaker("NonExistent");

        // Act
        Speaker result = speakerService.fetchSpeaker("NonExistent");

        // Assert
        assertThat(result).isNull();
    }
}
