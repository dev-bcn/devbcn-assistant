package com.devbcn.socialassistant.service;

import com.devbcn.socialassistant.dto.Session;
import com.devbcn.socialassistant.dto.Speaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class SessionizeService {
    private static final Logger logger = LoggerFactory.getLogger(SessionizeService.class);

    private final SpeakerService speakerService;
    private final SessionService sessionService;

    public SessionizeService(SpeakerService speakerService, SessionService sessionService) {
        this.speakerService = speakerService;
        this.sessionService = sessionService;
    }

    @Tool(description = "Retrieve a list of speakers")
    List<Speaker> getSpeakers() {
        logger.info("Getting speakers");
        var speakers = speakerService.fetchSpeakers();
        return Arrays
                .stream(speakers)
                .toList();
    }

    @Tool(description = "Retrieve a list of sessions")
    List<Session> getSessions() {
        logger.info("Getting sessions");
        return Arrays
                .stream(sessionService.fetchSessions())
                .flatMap(track -> track.sessions().stream())
                .toList();
    }

    @Tool(description = "Retrieve speaker information by its name")
    Speaker getSpeakerInfo(String speakerName) {
        logger.info("Getting speaker by name: {}", speakerName);
        return speakerService.fetchSpeaker(speakerName);
    }

    @Tool(description = "Retrieve session information by its UUID")
    Session getSessionInfo(String sessionId) {
        logger.info("Getting session by ID: {}", sessionId);
        return sessionService.getSessionById(sessionId);
    }
}
