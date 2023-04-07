package com.cydeo.utilities;


import org.junit.jupiter.api.BeforeAll;
import static io.restassured.RestAssured.baseURI;
public abstract class CydeoApiTestBase {
    @BeforeAll
    public static void setup() {
        baseURI = ConfigurationReader.getProperty("cydeo");
    }
}
