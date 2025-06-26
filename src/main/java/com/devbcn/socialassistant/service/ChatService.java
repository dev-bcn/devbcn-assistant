package com.devbcn.socialassistant.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ChatService {
    private static final Logger logger = LoggerFactory.getLogger(ChatService.class);
    private final ChatClient chatClient;
    private final FileUtilsService fileUtilsService;
    private final SessionizeService sessionizeService;

    public ChatService(ChatClient chatClient, FileUtilsService fileUtilsService, SessionizeService sessionizeService) {
        this.chatClient = chatClient;
        this.fileUtilsService = fileUtilsService;
        this.sessionizeService = sessionizeService;
    }

    public String getResponse(String userPrompt) {
        String systemMessage;
        try {
            systemMessage = fileUtilsService.getSystemMessage();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
        var response = chatClient.prompt()
                .system(systemMessage)
                .tools(sessionizeService)
                .user(userPrompt).call().content();
        logger.info("Response from ChatClient: {}", response);
        return response;
    }
}
