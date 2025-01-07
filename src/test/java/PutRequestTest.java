import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class PutRequestTest {

    @Test
    public void testPutRequest() {
        String requestBody = "This is expected to be sent back as part of response body.";

        given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .put("https://postman-echo.com/put")
                .then()
                .statusCode(200)
                .assertThat()
                .body("data", equalTo(requestBody));
    }
}
