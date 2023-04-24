package com.cydeo.day10;

import com.cydeo.pojos.Spartan;
import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
public class TestSpartanXML extends SpartanTestBase {

    @Test
    public void test() {

        XmlPath spartanXML = given().accept(ContentType.XML)
                .get("/api/spartans")
                .then().statusCode(200)
                .extract().xmlPath();

        List<Spartan> list = spartanXML.getList("List.item");

        String name = spartanXML.getString("List.item[0].name");
        System.out.println(name);

        System.out.println(list);



    }



}
