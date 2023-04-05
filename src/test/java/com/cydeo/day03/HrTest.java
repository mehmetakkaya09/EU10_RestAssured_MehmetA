package com.cydeo.day03;

import com.cydeo.utilities.HRTestBase;
import static io.restassured.RestAssured.*;

import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

public class HrTest extends HRTestBase {

    @Test
    public void test() {
        Response response = given().get("/employees");
        response.prettyPrint();
    }


}
