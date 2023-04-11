package com.cydeo.day06;
import com.cydeo.pojos.Region;
import com.cydeo.pojos.Regions;
import com.cydeo.pojos.Links;
import com.cydeo.utilities.HRTestBase;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static io.restassured.RestAssured.*;
public class TestRegions extends HRTestBase {

    @Test
    public void testRegions() {
        Region first = given().accept(ContentType.JSON)
                .when().get("/regions")
                .then().statusCode(200)
                .and().contentType("application/json")
                .extract().jsonPath().getObject("items[0]", Region.class);
        System.out.println(first);

        System.out.println(first.getLinks().get(0).getHref());
        Links links = first.getLinks().get(0);
        System.out.println(links.getHref());
    }

    @Test
    public void test2() {

        Regions regions = given().accept(ContentType.JSON)
                .when().get("regions")
                .then().statusCode(200)
                .and().contentType("application/json")
                .extract().response().as(Regions.class);

        System.out.println(regions.getItems().get(0));
        System.out.println(regions.getCount());

        System.out.println(regions.getItems().get(2).getRegion_name());
        System.out.println(regions.getItems().get(1).getRId());


    }



}
