package com.cydeo.day06;

import com.cydeo.pojos.Employees;
import com.cydeo.utilities.HRTestBase;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;
public class TestEmployeesPojo extends HRTestBase {

    @Test
    public void test() {
        Employees employees = given().accept(ContentType.JSON)
                .queryParam("limit", 110)
                .when().get("/employees")
                .then().statusCode(200)
                .and().contentType("application/json")
                .extract().jsonPath().getObject("", Employees.class);
        System.out.println(employees.getCount());
        System.out.println(employees.getItems().get(2));
        System.out.println(employees.getItems().get(1).getLinks().get(0).getHref());
        System.out.println(employees.getItems().get(0).getFirstName());
        System.out.println(employees.getLinks().get(2));


    }



}
