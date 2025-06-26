package com.devbcn.socialassistant;

import org.springframework.boot.SpringApplication;

public class TestSocialAssistantApplication {

    public static void main(String[] args) {
        SpringApplication.from(SocialAssistantApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
