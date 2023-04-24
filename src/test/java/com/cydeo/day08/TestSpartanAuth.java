package com.cydeo.day08;

import com.cydeo.utilities.AuthSpartanTestBase;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
public class TestSpartanAuth extends AuthSpartanTestBase {

    @Test
    public void test() {
           get("/api/spartans").then().statusCode(401).log().all();
    }

    @Test
    public void test2() {
        given()
                .auth().basic("admin", "admin").accept(ContentType.JSON)
                .get("/api/spartans")
                .then().statusCode(200).log().all();

    }

    @Test
    public void test3() {
        given()
                .auth()
                .basic("editor", "editor").accept(ContentType.JSON)
                .pathParam("id",9)
                .delete("/api/spartans/{id}")
                .then().statusCode(403).log().all();
    }



}
