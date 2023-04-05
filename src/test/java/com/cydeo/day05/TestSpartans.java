package com.cydeo.day05;
import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;
public class TestSpartans extends SpartanTestBase {

    @Test
    public void test() {

        List<String> names = given().accept(ContentType.JSON)
                .and().queryParams("nameContains", "j", "gender", "Male")
                .when().get("/api/spartans/search")
                .then().statusCode(200)
                .contentType("application/json")
                .and()
                .body("totalElement", is(3)).extract().response().jsonPath().getList("content.name");
        System.out.println(names);


    }




}
