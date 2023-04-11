package com.cydeo.day07;

import com.cydeo.pojos.Spartan;
import com.cydeo.utilities.SpartanTestBase;
import com.github.javafaker.Faker;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;
public class TestPostRequest extends SpartanTestBase {


    @Test
    public void testPostRequestWithString() {
       /*
    Given accept type and Content type is JSON
    And request json body is:
    {
      "gender":"Male",
      "name":"Severus",
      "phone":8877445596
   }
    When user sends POST request to '/api/spartans'
    Then status code 201
    And content type should be application/json
    And json payload/response/body should contain:
    "A Spartan is Born!" message
    and same data what is posted
 */
        String body = "{\n" +
                "  \"gender\": \"Male\",\n" +
                "  \"name\": \"NewSpartan\",\n" +
                "  \"phone\": 9876543210\n" +
                "}";
        Response response = given().accept(ContentType.JSON).contentType(ContentType.JSON).body(body)
                .post("api/spartans");


        assertThat(response.statusCode(), is(201));
        assertThat(response.path("success"), is("A Spartan is Born!"));
        assertThat(response.path("data.name"), is("NewSpartan"));
        assertThat(response.path("data.gender"), is("Male"));
        assertThat(response.path("data.phone"), is(9876543210l));
        String deleted = "/api/spartans/"+response.path("data.id");
        System.out.println("deleted = " + deleted);
        delete(deleted);

    }

    @Test
    public void serializationPostRequest() {

        //serialization java biçimini json biçimine otomatik olarak çevirmenin adıdır. PUT, PATCH ve POST requestlerde BODY kısmına MAP olarak yazıldığında bu MAPi json file haline getirir.
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("name","NewSpartan");
        body.put("gender","Male");
        body.put("phone",9876543210l);
        Response response = given().accept(ContentType.JSON).contentType(ContentType.JSON).body(body)
                .post("api/spartans");


        assertThat(response.statusCode(), is(201));
        assertThat(response.path("success"), is("A Spartan is Born!"));
        assertThat(response.path("data.name"), is("NewSpartan"));
        assertThat(response.path("data.gender"), is("Male"));
        assertThat(response.path("data.phone"), is(9876543210l));
        String deleted = "/api/spartans/"+response.path("data.id");
        System.out.println("deleted = " + deleted);
        delete(deleted);

    }

    @Test
    public void serializationWithPojo() {
        Spartan spartan = new Spartan();
        Faker faker = new Faker();
        String name = faker.name().firstName();
        String gender = faker.options().option("Male", "Female");
        long phone = Long.parseLong(faker.numerify("##########"));
        spartan.setGender(gender);
        spartan.setName(name);
        spartan.setPhone(phone);
        Response response = given().accept(ContentType.JSON).contentType(ContentType.JSON).body(spartan)
                .post("api/spartans");

        assertThat(response.statusCode(), is(201));
        assertThat(response.path("success"), is("A Spartan is Born!"));
        assertThat(response.path("data.name"), is(name));
        assertThat(response.path("data.gender"), is(gender));
        assertThat(response.path("data.phone"), is(phone));
        String deleted = "/api/spartans/"+response.path("data.id");
        System.out.println("deleted = " + deleted);
        delete(deleted);

    }






}
