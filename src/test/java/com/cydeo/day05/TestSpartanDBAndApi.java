package com.cydeo.day05;

import com.cydeo.utilities.DBUtils;
import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;
public class TestSpartanDBAndApi extends SpartanTestBase {

    @Test
    public void testSpartan() {
        Map<String, Object> rowMap = DBUtils.getRowMap("SELECT spartan_id, name, gender, phone FROM spartans \n" +
                "where spartan_id = 3");
        Map<String, Object> spartan3 = given().accept(ContentType.JSON)
                .and().pathParam("id", 3)
                .get("/api/spartans/{id}")
                .then().statusCode(200)
                .contentType("application/json")
                .extract().response().as(Map.class);
        assertThat(rowMap.get("SPARTAN_ID").toString(), equalTo(spartan3.get("id").toString()));

    }



}
