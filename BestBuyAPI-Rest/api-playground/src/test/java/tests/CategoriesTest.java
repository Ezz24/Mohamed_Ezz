package tests;

import config.TestBase;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class CategoriesTest extends TestBase {

    @Test
    public void getAllCategoriesTest() {
        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/categories")
                .then()
                .statusCode(200)
                .body("total", equalTo(4306))
                .body("data", hasSize(10))
                .body("data[0].name", not(empty()));
    }

    @Test
    public void getCategoryByIdTest() {
        String categoryId = "abcat0020001";

        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/categories/" + categoryId)
                .then()
                .statusCode(200)
                .body("id", equalTo(categoryId))
                .body("name", not(empty()));
    }

    @Test
    public void getCategoriesByNameTest() {
        String categoryName = "DVD Games";

        given()
                .contentType(ContentType.JSON)
                .queryParam("name", categoryName)
                .when()
                .get("/categories")
                .then()
                .statusCode(200)
                .body("data", everyItem(hasKey("name")))
                .body("data.name", everyItem(equalTo(categoryName)));
    }

//    @Test
//    public void searchCategoriesTest() {
//        String searchTerm = "TVs";
//
//        given()
//                .contentType(ContentType.JSON)
//                .queryParam("search", searchTerm)
//                .when()
//                .get("/categories")
//                .then()
//                .statusCode(200)
//                .body("data", everyItem(hasKey("name")))
//                .body("data.name", everyItem(containsString(searchTerm)));
//    }

    @Test
    public void createAndDeleteCategoryTest() {
        Map<String, Object> category = new HashMap<>();
        category.put("id", "test-category");
        category.put("name", "Test Category");

        String categoryId = given()
                .contentType(ContentType.JSON)
                .body(category)
                .when()
                .post("/categories")
                .then()
                .statusCode(201)
                .body("id", equalTo("test-category"))
                .extract().path("id");

        given()
                .contentType(ContentType.JSON)
                .when()
                .delete("/categories/" + categoryId)
                .then()
                .statusCode(200);

        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/categories/" + categoryId)
                .then()
                .statusCode(404);
    }
}
