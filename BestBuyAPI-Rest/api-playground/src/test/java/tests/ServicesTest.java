package tests;

import config.TestBase;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ServicesTest extends TestBase {

    @Test
    public void getAllServicesTest() {
        int totalServices = given()
                .contentType(ContentType.JSON)
                .when()
                .get("/services")
                .then()
                .statusCode(200)
                .extract()
                .path("total");

        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/services")
                .then()
                .statusCode(200)
                .body("total", equalTo(totalServices))
                .body("data", hasSize(10))
                .body("data[0].name", equalTo("Best Buy Mobile"));
    }

    @Test
    public void getServiceByIdTest() {
        int serviceId = 2;

        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/services/" + serviceId)
                .then()
                .statusCode(200)
                .body("id", equalTo(serviceId))
                .body("name", equalTo("Best Buy Mobile"));
    }

    @Test
    public void getServiceNameTest() {
        int serviceId = 3;

        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/services/" + serviceId)
                .then()
                .statusCode(200)
                .body("name", equalTo("Best Buy For Business"));
    }

//    @Test
//    public void searchServicesTest() {
//        String searchTerm = "Mobile";
//
//        given()
//                .contentType(ContentType.JSON)
//                .queryParam("search", searchTerm)
//                .when()
//                .get("/services")
//                .then()
//                .statusCode(200)
//                .body("data", everyItem(hasKey("name")))
//                .body("data.name", everyItem(containsString(searchTerm)));
//    }

    @Test
    public void validateCreatedAtAndUpdatedAtTest() {
        int serviceId = 5;

        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/services/" + serviceId)
                .then()
                .statusCode(200)
                .body("createdAt", notNullValue())
                .body("updatedAt", notNullValue());
    }
}
