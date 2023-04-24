package com.cydeo.day07;

import com.cydeo.pojos.Spartan;
import com.cydeo.utilities.SpartanTestBase;
import com.cydeo.utilities.UtilOfSpartan;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestPutAndPatchRequestSpartan extends SpartanTestBase {

    @Test
    public void testPutAndPatch() {

        Spartan spartan = new Spartan();
        spartan.setName(UtilOfSpartan.fakeSpartan().get("name").toString());
        spartan.setPhone(Long.parseLong(UtilOfSpartan.fakeSpartan().get("phone").toString()));
        spartan.setGender(UtilOfSpartan.fakeSpartan().get("gender").toString());

        int spartanId = given().accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(spartan).post("/api/spartans")
                .then().contentType("application/json")
                .and().statusCode(201)
                .extract().jsonPath().getInt("data.id");
        spartan.setId(spartanId);
        Spartan spartanTestPost = given().pathParam("id", spartanId).get("/api/spartans/{id}").as(Spartan.class);

        assertThat(spartan.getName(), is(spartanTestPost.getName()));
        assertThat(spartan.getGender(), is(spartanTestPost.getGender()));
        assertThat(spartan.getPhone(), is(spartanTestPost.getPhone()));
        assertThat(spartan.getId(), is(spartanTestPost.getId()));

        Spartan spartanPut = new Spartan();

        spartanPut.setName(UtilOfSpartan.fakeSpartan().get("name").toString());
        spartanPut.setPhone(Long.parseLong(UtilOfSpartan.fakeSpartan().get("phone").toString()));
        spartanPut.setGender(UtilOfSpartan.fakeSpartan().get("gender").toString());
        spartanPut.setId(spartanId);
        given()
                .contentType(ContentType.JSON)
                .body(spartanPut)
                .pathParam("id", spartanId)
                .put("/api/spartans/{id}")
                .then().statusCode(204);
        Spartan spartanTestPut = given().pathParam("id", spartanId).get("/api/spartans/{id}").as(Spartan.class);
        assertThat(spartanPut.getId(), equalTo(spartanTestPut.getId()));
        assertThat(spartanPut.getName(), equalTo(spartanTestPut.getName()));
        assertThat(spartanPut.getPhone(), equalTo(spartanTestPut.getPhone()));
        assertThat(spartanPut.getGender(), equalTo(spartanTestPut.getGender()));

        String patchBody = "{\"name\": \"ComoLokko\"}";
        given()
                .contentType(ContentType.JSON)
                .body(patchBody)
                .pathParam("id", spartanId)
                .patch("/api/spartans/{id}")
                .then().statusCode(204);
        Spartan spartanTestPatch = given().pathParam("id", spartanId).get("/api/spartans/{id}").as(Spartan.class);
        assertThat(spartanTestPatch.getName(), is("ComoLokko"));
        assertThat(spartanTestPatch.getId(),is(spartanPut.getId()));
        assertThat(spartanTestPatch.getPhone(),is(spartanPut.getPhone()));
        assertThat(spartanTestPatch.getGender(),is(spartanPut.getGender()));
        System.out.println("spartanId = " + spartanId);
        delete("/api/spartans/"+spartanId);


    }


}
