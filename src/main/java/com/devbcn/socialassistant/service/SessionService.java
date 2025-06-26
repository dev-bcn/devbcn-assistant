package com.devbcn.socialassistant.service;

import com.devbcn.socialassistant.dto.Session;
import com.devbcn.socialassistant.dto.Track;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Service
public class SessionService {

    private final RestTemplate restTemplate;

    public SessionService() {
        this.restTemplate = new RestTemplate();
    }

    public Track[] fetchSessions() {
        String url = "https://sessionize.com/api/v2/xhudniix/view/Sessions";
        ResponseEntity<Track[]> response = restTemplate.getForEntity(url, Track[].class);

        if (response.getStatusCode().is2xxSuccessful()) {
            return response.getBody();
        } else {
            throw new IllegalStateException("Failed to fetch speakers: " + response.getStatusCode());
        }
    }

    public Session getSessionById(String sessionId) {
        return Arrays.stream(fetchSessions())
                .flatMap(track -> track.sessions().stream())
                .filter(session -> session.id().equals(sessionId))
                .findFirst()
                .orElse(null);
    }
}
