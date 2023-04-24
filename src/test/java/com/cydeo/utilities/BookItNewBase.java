package com.cydeo.utilities;

import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;

public class BookItNewBase {

    private RequestSpecification requestSpecification;
    private ResponseSpecification responseSpecification;
    private String token;

    public RequestSpecification reqsSpec(String role){
        String email = "";
        String password = "";
        switch (role){
            case "teacher" :
            email = ConfigurationReader.getProperty("teacher_email");
            password = ConfigurationReader.getProperty("teacher_password");
            break;
            case "team-member" :
                email = ConfigurationReader.getProperty("team_member_email");
                password = ConfigurationReader.getProperty("team_member_password");
                break;
            case "team-leader" :
                email = ConfigurationReader.getProperty("team_leader_email");
                password = ConfigurationReader.getProperty("team_leader_password");
                break;
            default:
                throw new IllegalArgumentException("Unknown role : " + role);
        }
        token = given().accept(ContentType.JSON).queryParams("email", email, "password", password).get("/sign").then().statusCode(200).time(both(greaterThanOrEqualTo(300l)).and(lessThanOrEqualTo(3000l))).extract().jsonPath().getString("accessToken");

        requestSpecification = given().accept(ContentType.JSON).header("Authorization",token);
        return requestSpecification;
    }

    public ResponseSpecification respSpec(int status){
        responseSpecification = expect().statusCode(status).contentType(ContentType.JSON).time(both(greaterThanOrEqualTo(200l)).and(lessThanOrEqualTo(3000l))).logDetail(LogDetail.ALL);
        return responseSpecification;
    }



    @BeforeAll
    public static void setup() {
        baseURI = "http://api.qa2.bookit.cydeo.com";
    }
    @AfterAll
    public static void teardown() {
        reset();
    }




}
