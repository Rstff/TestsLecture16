package tests.pets.store;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pet.store.Category;
import pet.store.Pet;

public class Get {

    @Test
    public void testGetPetFromStoreReturn200() {
        int id = 11111;
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
        Pet restAssuredPet = RestAssured.given()
                .contentType(ContentType.JSON)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .get("https://petstore.swagger.io/v2/pet/{id}", id)
                .then()
                .statusCode(200)
                .extract()
                .as(Pet.class);
        Assertions.assertEquals(myPet, restAssuredPet);
    }
    @Test
    public void testGetPetFromStoreReturn404() {
        int id = 22222;
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
                .get("https://petstore.swagger.io/v2/pet/{id}", id)
                .then()
                .statusCode(404);
    }
}
