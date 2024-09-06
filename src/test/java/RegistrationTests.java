import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class RegistrationTests {

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
    public void testSuccessfulRegistration() {
        given(requestSpec)
                .body("{\"email\": \"eve.holt@reqres.in\", \"password\": \"pistol\"}")
                .when()
                .post("/register")
                .then()
                .spec(responseSpec)
                .statusCode(200)
                .body("id", equalTo(4))
                .body("token", equalTo("QpwL5tke4Pnpja7X4"));
    }

    @Test
    public void testRegistrationWithoutPassword() {
        given(requestSpec)
                .body("{\"email\": \"eve.holt@reqres.in\"}")
                .when()
                .post("/register")
                .then()
                .spec(responseSpec)
                .statusCode(400)
                .body("error", equalTo("Missing password"));
    }
}
