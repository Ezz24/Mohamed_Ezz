package tests;

import config.TestBase;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class StoresTest extends TestBase {

    @Test
    public void getAllStoresTest() {
        int initialTotal = given()
                .contentType(ContentType.JSON)
                .when()
                .get("/stores")
                .then()
                .statusCode(200)
                .extract()
                .path("total");

        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/stores")
                .then()
                .statusCode(200)
                .body("total", equalTo(initialTotal))
                .body("data", hasSize(10));
//                .body("data[0].name", equalTo("Minnetonka"));
    }

    @Test
    public void getStoreByIdTest() {
        int storeId = 6;

        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/stores/" + storeId)
                .then()
                .statusCode(200)
                .body("id", equalTo(storeId))
                .body("name", equalTo("Inver Grove Heights"))
                .body("address", equalTo("1350 50th Street E"));
    }

    @Test
    public void getStoreServicesTest() {
        int storeId = 6;

        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/stores/" + storeId)
                .then()
                .statusCode(200)
                .body("services", not(empty()));
    }

    @Test
    public void getStoreLocationTest() {
        int storeId = 11;

        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/stores/" + storeId)
                .then()
                .statusCode(200)
                .body("city", equalTo("Blaine"))
                .body("state", equalTo("MN"))
                .body("zip", equalTo("55434"));
    }

//    @Test
//    public void searchStoresTest() {
//        String searchTerm = "Inver Grove Heights";
//
//        given()
//                .contentType(ContentType.JSON)
//                .queryParam("search", searchTerm)
//                .when()
//                .get("/stores")
//                .then()
//                .statusCode(200)
//                .body("data", everyItem(hasKey("name")))
//                .body("data.name", everyItem(containsString(searchTerm)));
//    }

    @Test
    public void createAndDeleteStoreTest() {
        int initialTotal = given()
                .contentType(ContentType.JSON)
                .when()
                .get("/stores")
                .then()
                .statusCode(200)
                .extract()
                .path("total");

        Map<String, Object> store = new HashMap<>();
        store.put("name", "Test Store");
        store.put("type", "BigBox");
        store.put("address", "123 Test St");
        store.put("city", "Test City");
        store.put("state", "TC");
        store.put("zip", "12345");

        int storeId = given()
                .contentType(ContentType.JSON)
                .body(store)
                .when()
                .post("/stores")
                .then()
                .statusCode(201)
                .body("id", notNullValue())
                .extract().path("id");

        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/stores")
                .then()
                .statusCode(200)
                .body("total", equalTo(initialTotal + 1));

        given()
                .contentType(ContentType.JSON)
                .when()
                .delete("/stores/" + storeId)
                .then()
                .statusCode(200);

        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/stores")
                .then()
                .statusCode(200)
                .body("total", equalTo(initialTotal));
    }
}
