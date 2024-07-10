package tests;

import config.TestBase;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class HealthCheckTest extends TestBase {

    @Test
    public void getHealthCheckTest() {
        int totalProducts = given()
                .contentType(ContentType.JSON)
                .when()
                .get("/products")
                .then()
                .statusCode(200)
                .extract()
                .path("total");

        int totalStores = given()
                .contentType(ContentType.JSON)
                .when()
                .get("/stores")
                .then()
                .statusCode(200)
                .extract()
                .path("total");

        int totalCategories = given()
                .contentType(ContentType.JSON)
                .when()
                .get("/categories")
                .then()
                .statusCode(200)
                .extract()
                .path("total");

        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/healthcheck")
                .then()
                .statusCode(200)
                .body("uptime", greaterThan(0.0f))
                .body("readonly", equalTo(false))
                .body("documents.products", equalTo(totalProducts))
                .body("documents.stores", equalTo(totalStores))
                .body("documents.categories", equalTo(totalCategories));
    }
}
