package com.devbcn.socialassistant.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.io.TempDir;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(MockitoExtension.class)
class FileUtilsServiceTest {

    @InjectMocks
    private FileUtilsService fileUtilsService;

    @Test
    void getSystemMessage_shouldReturnFileContent_whenFileExists() throws IOException {

        String result = fileUtilsService.getSystemMessage();

        assertThat(result).isNotNull();
        assertThat(result).isNotEmpty();
    }

    @Test
    void getSystemMessage_withMockedFile(@TempDir Path tempDir) throws IOException {
        String testContent = "Test system message";
        Path tempFile = tempDir.resolve("system_message.md");
        Files.write(tempFile, testContent.getBytes());

        FileUtilsService testFileUtilsService = new FileUtilsService() {
            @Override
            public String getSystemMessage() throws IOException {
                return Files.readString(tempFile);
            }
        };

        String result = testFileUtilsService.getSystemMessage();

        assertThat(testContent).isEqualTo(result);
    }

    @Test
    void getSystemMessage_shouldThrowIOException_whenFileDoesNotExist() {
        FileUtilsService testFileUtilsService = new FileUtilsService() {
            @Override
            public String getSystemMessage() throws IOException {
                return Files.readString(Paths.get("non_existent_file.md"));
            }
        };

        assertThatExceptionOfType(IOException.class).isThrownBy(testFileUtilsService::getSystemMessage);
    }
}
