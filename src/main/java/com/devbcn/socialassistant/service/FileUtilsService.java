package com.devbcn.socialassistant.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
public class FileUtilsService {
    private static final Logger logger = LoggerFactory.getLogger(FileUtilsService.class);

    public String getSystemMessage() throws IOException {
        logger.info("Getting system message");
        return Files.readString(Paths.get("src/main/resources/system_message.md"));
    }
}
