# MCP Weather Server

This project is an MCP (Model Context Protocol) server implemented in Java using Spring Boot. It provides weather and real-time stock price data.

## Architecture

- **Web MVC Server Configuration**: The main branch uses Spring Web MVC for handling HTTP requests.
- **WebFlux MVC Configuration**: The `webflux-transport` branch uses Spring WebFlux for reactive, non-blocking request handling.
  - Both configurations are maintained separately for flexibility and performance testing.

## Features
- Weather information retrieval
- Real-time stock price fetching using Ninja API
- SSE (Server-Sent Events) support for streaming data
- localhost:8081/sse endpoint for SSE connections

## Technologies Used
- Java
- Spring Boot (Web MVC & WebFlux)
- Gradle

## Stock Price API
Stock prices are fetched in real time using the [API Ninja](https://api-ninjas.com/api/stock-price) service. You must provide an API key for this service.

### Setting the API Key
Set your Ninja API key in one of the following ways:

- As an environment variable:
  ```bash
  export API_NINJA_KEY=your_api_key_here
  ```
- Or in `src/main/resources/application.properties`:
  ```properties
  api.ninja.key=your_api_key_here
  ```

## How to Build
```bash
./gradlew build
```

## How to Run
```bash
./gradlew bootRun
```

## Branches
- `main`: Web MVC configuration
- `webflux-transport`: WebFlux configuration

## MCP Config json for VSCode

# config for webflux-transport Branch
```
{
"servers": {
"my-mcp-server-488ad34d": {
"url": "http://localhost:8081/sse",
"type": "sse"
}
},
"inputs": []
}
```
# config for main Branch
```
{
"servers": {
"my-mcp-server-488ad34d": {
"url": "http://localhost:8081/sse",
"type": "http"
}
},
"inputs": []
}
```


