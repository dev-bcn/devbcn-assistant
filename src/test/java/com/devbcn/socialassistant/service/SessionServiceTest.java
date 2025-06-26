package com.devbcn.socialassistant.service;

import com.devbcn.socialassistant.dto.Session;
import com.devbcn.socialassistant.dto.Track;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;

@ExtendWith(MockitoExtension.class)
class SessionServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @Spy
    @InjectMocks
    private SessionService sessionService;

    private Track[] mockTracks;

    @BeforeEach
    void setUp() {
        Session mockSession1 = new Session(
                List.of(),
                "session1",
                "Session 1",
                "Description 1",
                LocalDateTime.now(),
                LocalDateTime.now().plusHours(1),
                false,
                false,
                List.of(),
                List.of(),
                1,
                "Room 1",
                "",
                "",
                "Accepted",
                true,
                true
        );

        Session mockSession2 = new Session(
                List.of(),
                "session2",
                "Session 2",
                "Description 2",
                LocalDateTime.now(),
                LocalDateTime.now().plusHours(1),
                false,
                false,
                List.of(),
                List.of(),
                2,
                "Room 2",
                "",
                "",
                "Accepted",
                true,
                true
        );

        mockTracks = new Track[]{
                new Track(1, "Track 1", List.of(mockSession1), false),
                new Track(2, "Track 2", List.of(mockSession2), false)
        };
    }

    @Test
    void fetchSessions_shouldReturnTracks_whenApiCallIsSuccessful() {
        // Arrange
        doReturn(mockTracks).when(sessionService).fetchSessions();

        // Act
        Track[] result = sessionService.fetchSessions();

        // Assert
        assertThat(result).isNotNull().hasSize(2);
        assertThat(result[0].groupName()).isEqualTo("Track 1");
        assertThat(result[1].groupName()).isEqualTo("Track 2");
        assertThat(result[0].sessions()).hasSize(1);
        assertThat(result[1].sessions()).hasSize(1);
        assertThat(result[0].sessions().getFirst().title()).isEqualTo("Session 1");
        assertThat(result[1].sessions().getFirst().title()).isEqualTo("Session 2");
    }

    @Test
    void fetchSessions_shouldThrowException_whenApiCallFails() {
        doThrow(new IllegalStateException("Failed to fetch speakers"))
                .when(sessionService).fetchSessions();

        assertThatExceptionOfType(IllegalStateException.class)
                .isThrownBy(() -> sessionService.fetchSessions())
                .withMessageContaining("Failed to fetch speakers");
    }

    @Test
    void getSessionById_shouldReturnSession_whenIdMatches() {
        Session mockSession = mockTracks[0].sessions().getFirst();
        doReturn(mockSession).when(sessionService).getSessionById("session1");

        Session result = sessionService.getSessionById("session1");

        assertThat(result).isNotNull();
        assertThat(result.title()).isEqualTo("Session 1");
        assertThat(result.id()).isEqualTo("session1");
    }

    @Test
    void getSessionById_shouldReturnNull_whenNoIdMatches() {
        // Arrange
        doReturn(null).when(sessionService).getSessionById("nonexistent");

        // Act
        Session result = sessionService.getSessionById("nonexistent");

        // Assert
        assertThat(result).isNull();
    }
}
