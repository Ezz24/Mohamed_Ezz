package tests;

import config.TestBase;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;

public class ProductsTest extends TestBase {

    @Test
    public void getProductsTest() {
        int initialTotal = given()
                .contentType(ContentType.JSON)
                .when()
                .get("/products")
                .then()
                .statusCode(200)
                .extract()
                .path("total");

        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/products")
                .then()
                .statusCode(200)
                .body("total", equalTo(initialTotal))
                .body("data", hasSize(10))
                .body("data[0].name", not(empty()));
    }

    @Test
    public void getProductByIdTest() {
        int productId = 48530;

        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/products/" + productId)
                .then()
                .statusCode(200)
                .body("id", equalTo(productId))
                .body("name", equalTo("Duracell - AA 1.5V CopperTop Batteries (4-Pack)"));
    }

    @Test
    public void getProductsByCategoryTest() {
        String categoryId = "abcat0208002";

        given()
                .contentType(ContentType.JSON)
                .queryParam("category.id", categoryId)
                .when()
                .get("/products")
                .then()
                .statusCode(200)
                .body("data", everyItem(hasKey("categories")))
                .body("data.categories.id", everyItem(hasItem(categoryId)));
    }

//    @Test
//    public void searchProductsTest() {
//        String searchTerm = "Duracell";
//
//        given()
//                .contentType(ContentType.JSON)
//                .queryParam("search", searchTerm)
//                .when()
//                .get("/products")
//                .then()
//                .statusCode(200)
//                .body("data", everyItem(hasKey("name")))
//                .body("data.name", everyItem(containsString(searchTerm)));
//    }

    @Test
    public void validateProductSchemaTest() {
        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/products/48530")
                .then()
                .statusCode(200)
                .assertThat()
                .body(matchesJsonSchemaInClasspath("product-schema.json"));
    }

    @Test
    public void createAndDeleteProductTest() {
        int initialTotal = given()
                .contentType(ContentType.JSON)
                .when()
                .get("/products")
                .then()
                .statusCode(200)
                .extract()
                .path("total");

        Map<String, Object> product = new HashMap<>();
        product.put("name", "Test Product");
        product.put("type", "HardGood");
        product.put("price", 99.99);
        product.put("shipping", 0);
        product.put("upc", "123456789012");
        product.put("description", "This is a test product.");
        product.put("manufacturer", "Test Manufacturer");
        product.put("model", "TestModel123");
        product.put("url", "http://www.example.com");
        product.put("image", "http://img.bbystatic.com/BestBuy_US/images/products/1234/123456_sa.jpg");

        int productId = given()
                .contentType(ContentType.JSON)
                .body(product)
                .when()
                .post("/products")
                .then()
                .statusCode(201)
                .body("id", notNullValue())
                .extract().path("id");

        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/products")
                .then()
                .statusCode(200)
                .body("total", equalTo(initialTotal + 1));

        given()
                .contentType(ContentType.JSON)
                .when()
                .delete("/products/" + productId)
                .then()
                .statusCode(200);

        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/products")
                .then()
                .statusCode(200)
                .body("total", equalTo(initialTotal));
    }
}
