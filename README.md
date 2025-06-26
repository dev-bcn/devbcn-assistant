# DevBcn Social Assistant

A conversational AI assistant for the DevBcn conference that helps with creating
social media content, newsletters, and providing information about speakers and
sessions.

## Project Description

The DevBcn Social Assistant is a Spring Boot application that leverages AI
models (either OpenAI or Ollama) to provide information about the DevBcn
conference, generate social media posts, and create newsletter content. It
features a chat interface built with Vaadin where users can interact with the
assistant.

The assistant can:

- Provide information about the conference (dates, venue, tracks, etc.)
- Generate social media posts for different platforms (Twitter, Instagram,
  LinkedIn, Bluesky)
- Create Mailchimp newsletter content
- Retrieve and present information about speakers and sessions

## Technologies Used

- **Java 21**: The application is built using Java 21
- **Spring Boot 3.5.3**: Framework for building the application
- **Spring AI 1.0.0**: Integration with AI models
    - Support for OpenAI models (default)
    - Support for Ollama models (alternative)
- **Vaadin 24.8.0**: Web framework for building the user interface
- **Docker Compose**: For running Ollama locally
- **TestContainers**: For testing with containerized dependencies

## Project Structure

- `src/main/java/com/devbcn/socialassistant/`
    - `SocialAssistantApplication.java`: Main application class
    - `config/`: Configuration classes
    - `dto/`: Data transfer objects for sessions, speakers, etc.
    - `service/`: Service classes for business logic
        - `ChatService.java`: Handles interactions with AI models
        - `SessionizeService.java`: Provides tools for the AI to access
          conference data
        - `SpeakerService.java`: Manages speaker information
        - `SessionService.java`: Manages session information
    - `views/`: Vaadin UI components
        - `HomeView.java`: Main chat interface
- `src/main/resources/`
    - `application.properties`: Application configuration
    - `system_message.md`: Instructions for the AI model

## Setup and Installation

### Prerequisites

- Java 21 JDK
- Maven
- Docker (for Ollama profile)

### Configuration

1. Create a `.env.local` file in the project root with your OpenAI API key:
   ```
   API_KEY=your_openai_api_key
   ```

2. Choose your AI provider profile:
    - OpenAI (default): Uses OpenAI models (requires API key)
    - Ollama: Uses local Ollama models (requires Docker)

### Building and Running

#### Using Maven

```bash
# Build the project
mvn clean install

# Run with OpenAI profile (default)
mvn spring-boot:run

# Run with Ollama profile
mvn spring-boot:run -Pollama
```

#### Using Docker Compose (for Ollama profile)

```bash
# Start Ollama container
docker-compose up -d

# Run the application with Ollama profile
mvn spring-boot:run -Pollama
```

## Usage

1. Access the application at http://localhost:8080
2. Use the chat interface to interact with the assistant
3. Ask questions about the DevBcn conference
4. Request social media posts or newsletter content

## Examples

- "Tell me about the DevBcn conference"
- "Create a Twitter post announcing speaker John Doe"
- "Generate a Mailchimp newsletter about early bird tickets"
- "What sessions are available in the Java track?"

## Development

### Running Tests

```bash
mvn test
```

### Profiles

- `production`: Optimizes the application for production
- `openai`: Uses OpenAI models (default)
- `ollama`: Uses Ollama models with Docker Compose integration

## License

[MIT](LICENSE)

## Contributors

* [Anyul Rivas](https://www.github.com/anyulled)
