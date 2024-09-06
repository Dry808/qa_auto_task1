import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class DeleteUserTests {

    private static RequestSpecification requestSpec;

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "https://reqres.in/api";

        requestSpec = new RequestSpecBuilder()
                .build();
    }

    @Test
    public void testDeleteUser() {
        given(requestSpec)
                .when()
                .delete("/users/2")
                .then()
                .statusCode(204);
    }
}
