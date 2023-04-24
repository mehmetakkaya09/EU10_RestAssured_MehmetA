package com.cydeo.day07;

import com.cydeo.pojos.Spartan;
import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
public class TestPutAndPostAndPatch extends SpartanTestBase {

    @Test
    public void testPutAndPostAndPatch() {

        String bodySpartan = "    {\n" +
                "        \"name\": \"NewSpartan\",\n" +
                "        \"gender\": \"Male\",\n" +
                "        \"phone\": 9876543210\n" +
                "    }";

        Map<String, Object> spartanMap = new LinkedHashMap<>();
        spartanMap.put("name", "NewSpartan");
        spartanMap.put("gender", "Female");
        spartanMap.put("phone", 9876543210l);

        Spartan spartanPojo = new Spartan();
        spartanPojo.setName("NewForPut");
        spartanPojo.setGender("Female");
        spartanPojo.setPhone(9876543210l);

        int spartanID = given().accept(ContentType.JSON).contentType(ContentType.JSON)
                .body(spartanPojo)
                .when().post("/api/spartans")
                .then()
                .body("data.name", is("NewForPut"))
                .statusCode(201).extract().jsonPath().getInt("data.id");
        System.out.println("spartanID = " + spartanID);

        Spartan spartan = given().pathParam("id", spartanID).get("/api/spartans/{id}").as(Spartan.class);
        assertThat(spartanPojo.getName(), equalTo(spartan.getName()));

        Spartan spartanPut = new Spartan();
        spartanPut.setName("Put");
        spartanPut.setGender("Male");
        spartanPut.setPhone(9876543210l);

       given().contentType(ContentType.JSON)
                .pathParam("id", spartanID)
                .body(spartanPut)
                .put("/api/spartans/{id}")
                .then()
                .statusCode(204);

       Spartan spartanResultPut = given().pathParam("id", spartanID).get("/api/spartans/{id}").as(Spartan.class);

        assertThat(spartanPut.getName(), equalTo(spartanResultPut.getName()));

        Map<String, Object> patchSpartan = new LinkedHashMap<>();
        patchSpartan.put("phone", 1234567890l);

        given().contentType(ContentType.JSON)
                .pathParam("id", spartanID)
                .body(patchSpartan)
                .patch("/api/spartans/{id}")
                .then().statusCode(204);
        Spartan spartanResultPatch = given().pathParam("id", spartanID).get("/api/spartans/{id}").as(Spartan.class);

        assertThat(patchSpartan.get("phone"), equalTo(spartanResultPatch.getPhone()));

        Response delete = delete("/api/spartans/" + spartanID).then().statusCode(204).extract().response();



        Response response = given().pathParam("id", spartanID).get("/api/spartans/{id}");
        assertThat(response.statusCode(), equalTo(404));


    }



}
