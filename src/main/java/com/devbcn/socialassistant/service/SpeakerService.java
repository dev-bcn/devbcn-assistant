package com.devbcn.socialassistant.service;

import com.devbcn.socialassistant.dto.Speaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Service
public class SpeakerService {
    private static final Logger logger = LoggerFactory.getLogger(SpeakerService.class);

    private final RestTemplate restTemplate;

    public SpeakerService() {
        this.restTemplate = new RestTemplate();
    }

    public Speaker[] fetchSpeakers() {
        logger.info("Fetching all speakers from API");
        String url = "https://sessionize.com/api/v2/xhudniix/view/Speakers";
        ResponseEntity<Speaker[]> response = restTemplate.getForEntity(url, Speaker[].class);

        if (response.getStatusCode().is2xxSuccessful()) {
            return response.getBody();
        } else {
            throw new IllegalStateException("Failed to fetch speakers: " + response.getStatusCode());
        }
    }

    public Speaker fetchSpeaker(String name) {
        logger.info("Fetching speaker by name {}", name);
        return Arrays
                .stream(fetchSpeakers())
                .filter(speaker -> speaker.fullName()
                        .contains(name))
                .findFirst()
                .orElse(null);
    }
}
