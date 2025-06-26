package com.devbcn.socialassistant.service;

import com.devbcn.socialassistant.dto.Session;
import com.devbcn.socialassistant.dto.Speaker;
import com.devbcn.socialassistant.dto.Track;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SessionizeServiceTest {

    @Mock
    private SpeakerService speakerService;

    @Mock
    private SessionService sessionService;

    @InjectMocks
    private SessionizeService sessionizeService;

    private Speaker[] mockSpeakers;
    private Track[] mockTracks;
    private Session mockSession;

    @BeforeEach
    void setUp() {
        // Create mock speakers
        mockSpeakers = new Speaker[]{
                new Speaker("1", "John", "Doe", "John Doe", "Bio 1", "Tagline 1", "pic1.jpg", List.of(), false, List.of(), List.of(), List.of()),
                new Speaker("2", "Jane", "Smith", "Jane Smith", "Bio 2", "Tagline 2", "pic2.jpg", List.of(), true, List.of(), List.of(), List.of())
        };

        // Create mock session
        mockSession = new Session(
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

        // Create mock tracks
        mockTracks = new Track[]{
                new Track(1, "Track 1", List.of(mockSession), false)
        };
    }

    @Test
    void getSpeakers_shouldReturnListOfSpeakers() {
        when(speakerService.fetchSpeakers()).thenReturn(mockSpeakers);

        List<Speaker> result = sessionizeService.getSpeakers();

        assertThat(result).isNotNull().hasSize(2);
        assertThat(result.getFirst().fullName()).isEqualTo("John Doe");
        assertThat(result.get(1).fullName()).isEqualTo("Jane Smith");
    }

    @Test
    void getSessions_shouldReturnListOfSessions() {
        when(sessionService.fetchSessions()).thenReturn(mockTracks);

        List<Session> result = sessionizeService.getSessions();

        assertThat(result).isNotNull().hasSize(1);
        assertThat(result.getFirst().title()).isEqualTo("Session 1");
    }

    @Test
    void getSpeakerInfo_shouldReturnSpeakerByName() {
        when(speakerService.fetchSpeaker("John")).thenReturn(mockSpeakers[0]);

        Speaker result = sessionizeService.getSpeakerInfo("John");

        assertThat(result).isNotNull();
        assertThat(result.fullName()).isEqualTo("John Doe");
    }

    @Test
    void getSessionInfo_shouldReturnSessionById() {
        // Arrange
        when(sessionService.getSessionById("session1")).thenReturn(mockSession);

        // Act
        Session result = sessionizeService.getSessionInfo("session1");

        // Assert
        assertThat(result).isNotNull();
        assertThat(result.title()).isEqualTo("Session 1");
    }
}
