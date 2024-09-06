import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.endsWith;

public class UserListTests {

    private static RequestSpecification requestSpec;
    private static ResponseSpecification responseSpec;

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "https://reqres.in/api";

        requestSpec = new RequestSpecBuilder()
                .build();

        responseSpec = new ResponseSpecBuilder()
                .expectContentType(ContentType.JSON)
                .build();
    }

    @Test
    public void testGetUserListAndCheckEmail() {
        given(requestSpec)
                .when()
                .get("/users?page=2")
                .then()
                .spec(responseSpec)
                .statusCode(200)
                .body("data.email", everyItem(endsWith("@reqres.in")));
    }
}
