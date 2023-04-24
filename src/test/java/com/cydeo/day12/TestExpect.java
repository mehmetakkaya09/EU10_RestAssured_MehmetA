package com.cydeo.day12;

import com.cydeo.utilities.BookItNewBase;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;

public class TestExpect extends BookItNewBase {

    @Test
    public void test() {
        given().spec(reqsSpec("teacher"))
                .get("/api/users/me")
                .then().spec(respSpec(200));
    }



}
