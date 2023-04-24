package com.cydeo.day10;
import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
public class TestF1XML {

    @Test
    public void test() {
        baseURI = "http://ergast.com/api/f1/drivers";
        XmlPath xmlPath = given().accept(ContentType.XML)
                .get().then().statusCode(200)
                .extract().xmlPath();
        String abate = xmlPath.getString("MRData.DriverTable.Driver[0].@driverId");
        System.out.println(abate);


    }




}
