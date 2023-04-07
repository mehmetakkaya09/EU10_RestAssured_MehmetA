package com.cydeo.utilities;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.baseURI;

public abstract class SpartanTestBase {

    @BeforeAll
    public static void init() {
        baseURI = ConfigurationReader.getProperty("spartans");
        //DBUtils.createConnection();
    }
    @AfterAll
    public static void tearDown() {
       // DBUtils.destroy();
    }
}
