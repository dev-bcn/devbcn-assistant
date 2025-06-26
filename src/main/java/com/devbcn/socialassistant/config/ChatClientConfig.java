package com.devbcn.socialassistant.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ChatClientConfig {

    @Bean(name = "chatClientWithMemory")
    public ChatClient chatClientWithMemory(ChatClient.Builder builder, ChatMemory memory) {
        return builder
                .defaultAdvisors(
                        MessageChatMemoryAdvisor.builder(memory).build(),
                        SimpleLoggerAdvisor.builder().build())
                .build();
    }
}
