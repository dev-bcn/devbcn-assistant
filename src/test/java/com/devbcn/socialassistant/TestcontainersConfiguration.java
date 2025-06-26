package com.devbcn.socialassistant;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.test.context.ActiveProfiles;

@TestConfiguration(proxyBeanMethods = false)
@ActiveProfiles("ollama")
class TestcontainersConfiguration {

    //TODO create a conditional test
//    @Bean
//    @ServiceConnection
//    OllamaContainer ollamaContainer() {
//        return new OllamaContainer(DockerImageName.parse("ollama/ollama:latest"));
//    }

}
