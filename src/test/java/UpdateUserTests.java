import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.matchesPattern;

public class UpdateUserTests {

    private static RequestSpecification requestSpec;
    private static ResponseSpecification responseSpec;

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "https://reqres.in/api";

        requestSpec = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .build();

        responseSpec = new ResponseSpecBuilder()
                .expectContentType(ContentType.JSON)
                .build();
    }

    @Test
    public void testUpdateUserAndCheckUpdatedDate() {
        String currentDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")); // для проверки

        given(requestSpec)
                .body("{\"name\": \"morpheus\", \"job\": \"zion resident\"}")
                .when()
                .patch("/users/2")
                .then()
                .spec(responseSpec)
                .statusCode(200)
                .body("updatedAt", matchesPattern(currentDate + "T\\d{2}:\\d{2}:\\d{2}\\.\\d{3}Z"));
    }
}