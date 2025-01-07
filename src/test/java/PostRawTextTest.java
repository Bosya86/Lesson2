import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PostmanEchoTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void testPostRawText() throws IOException, InterruptedException {

        HttpClient httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .connectTimeout(Duration.ofSeconds(10))
                .build();


        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("test", "value");

        log("Request body: " + toJson(requestBody));

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://postman-echo.com/post"))
                .timeout(Duration.ofSeconds(30))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(toJson(requestBody)))
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        log("Full response body: " + response.body());

        assertEquals(200, response.statusCode(), "Статус-код не равен 200");

        JsonNode responseData = objectMapper.readTree(response.body()).get("data");

        assertEquals(
                objectMapper.writeValueAsString(requestBody),
                objectMapper.writeValueAsString(responseData),
                "Тело ответа не совпадает с телом запроса"
        );
    }

    private String toJson(Map<String, Object> map) {
        try {
            return objectMapper.writeValueAsString(map);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void log(String message) {
        System.out.println(message);
    }
}