package com.cydeo.day06;

import com.cydeo.pojos.Search;
import com.cydeo.pojos.Spartan;
import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;
public class TestSpartanPojo extends SpartanTestBase {

    @Test
    public void test() {
        Spartan spartan15 = given().accept(ContentType.JSON).pathParam("id", 15)
                .when().get("/api/spartans/{id}")
                .then().statusCode(200)
                .contentType("application/json")
                .extract().response().as(Spartan.class);

        System.out.println(spartan15);
        System.out.println(spartan15.getName());


    }

    @Test
    public void test2() {

        JsonPath jsonPath = given().accept(ContentType.JSON)
                .and().queryParams("nameContains", "a", "gender", "Male")
                .when().get("/api/spartans/search")
                .then().statusCode(200)
                .and().contentType("application/json")
                .extract().jsonPath();
        Spartan spartan = jsonPath.getObject("content[1]", Spartan.class);
        System.out.println(spartan);

    }


    @Test
    public void test3() {

        Search listOfContent = given().accept(ContentType.JSON)
                .and().queryParams("nameContains", "a", "gender", "Male")
                .when().get("/api/spartans/search")
                .then().statusCode(200)
                .and().contentType("application/json")
                .extract().response().as(Search.class);

        System.out.println(listOfContent.getContent().get(1));


    }

    @Test
    public void test4() {
        List<Spartan> list = given().accept(ContentType.JSON)
                .and().queryParams("nameContains", "a", "gender", "Male")
                .when().get("/api/spartans/search")
                .then().statusCode(200)
                .and().contentType("application/json")
                .extract().jsonPath().getList("content", Spartan.class);


        System.out.println(list.get(1));

    }







}
