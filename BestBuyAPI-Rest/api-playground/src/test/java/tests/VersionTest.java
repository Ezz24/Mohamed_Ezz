package tests;

import config.TestBase;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class VersionTest extends TestBase {

    @Test
    public void getVersionTest() {
        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/version")
                .then()
                .statusCode(200)
                .body("version", equalTo("1.1.0"));
    }
}

