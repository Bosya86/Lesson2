import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PostmanEchoFormDataTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void testPostFormData() throws IOException, InterruptedException {

        HttpClient httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .connectTimeout(Duration.ofSeconds(10))
                .build();

        var formData = List.of(
                "foo1=bar1",
                "&foo2=bar2"
        );

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://postman-echo.com/post"))
                .timeout(Duration.ofSeconds(30))
                .header("Content-Type", "application/x-www-form-urlencoded")
                .POST(HttpRequest.BodyPublishers.ofString(formData.stream().collect(Collectors.joining())))
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        assertEquals(200, response.statusCode(), "Статус-код не равен 200");

        JsonNode root = objectMapper.readTree(response.body());
        JsonNode jsonNode = root.at("/json");

        assertEquals("bar1", jsonNode.get("foo1").asText(), "Поле foo1 отсутствует или неверное значение");
        assertEquals("bar2", jsonNode.get("foo2").asText(), "Поле foo2 отсутствует или неверное значение");
    }
}