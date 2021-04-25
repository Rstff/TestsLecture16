package tests.pets.store;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import pet.store.Category;
import pet.store.Pet;

public class Delete {
    @Test
    public void deletePetFromStoreReturn200() {
        int id = 33333;
        Pet myPet = new Pet();
        myPet.setId(id);
        myPet.setName("Yasha");
        Category category = new Category();
        category.setName("Red Cat");
        category.setId(555);
        myPet.setCategory(category);
        myPet.setStatus("pending");
        RestAssured.given()
                .contentType(ContentType.JSON)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .delete("https://petstore.swagger.io/v2/pet/{id}", id);
        RestAssured.given().contentType(ContentType.JSON)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .body(myPet).post("https://petstore.swagger.io/v2/pet")
                .then()
                .statusCode(200);
        RestAssured.given()
                .contentType(ContentType.JSON)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .get("https://petstore.swagger.io/v2/pet/{id}", id)
                .then()
                .statusCode(200);
        RestAssured.given()
                .contentType(ContentType.JSON)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .delete("https://petstore.swagger.io/v2/pet/{id}", id);
        RestAssured.given()
                .contentType(ContentType.JSON)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .get("https://petstore.swagger.io/v2/pet/{id}", id)
                .then()
                .statusCode(404);
    }
    @Test
    public void deletePetFromStoreReturn404() {
        int id = 44444;
        Pet myPet = new Pet();
        myPet.setId(id);
        myPet.setName("Yasha");
        Category category = new Category();
        category.setName("Red Cat");
        category.setId(555);
        myPet.setCategory(category);
        myPet.setStatus("pending");
        RestAssured.given()
                .contentType(ContentType.JSON)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .delete("https://petstore.swagger.io/v2/pet/{id}", id);
        RestAssured.given()
                .contentType(ContentType.JSON)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .delete("https://petstore.swagger.io/v2/pet/{id}", id)
                .then()
                .statusCode(404);
    }
}