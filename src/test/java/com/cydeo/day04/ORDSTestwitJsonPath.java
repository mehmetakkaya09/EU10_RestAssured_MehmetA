package com.cydeo.day04;

import com.cydeo.utilities.HRTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
public class ORDSTestwitJsonPath extends HRTestBase {

    @Test
    public void test() {

        Response response = get("/countries");
        JsonPath jsonPath = response.jsonPath();
        String name1 = jsonPath.getString("items[1].country_name");
        System.out.println(name1);

        List<String> IDs = jsonPath.getList("items.country_id");
        Set<String> uniqueIDs = new TreeSet<>();
        uniqueIDs.addAll(IDs);
        System.out.println(uniqueIDs);
        List<Object> names = jsonPath.getList("items.findAll {it.region_id==2}.country_name");
        System.out.println(names);

    }

    @Test
    public void test2() {
        Response response = given().accept(ContentType.JSON).and().queryParam("limit",107).get("/employees");

        JsonPath jsonPath = response.jsonPath();
        List<String> emails = jsonPath.getList("items.findAll {it.job_id.equals(\"IT_PROG\")}.email");
        System.out.println(emails);

        List<String> salaries = jsonPath.getList("items.findAll {it.salary>=10000}.first_name");
        System.out.println(salaries.size() + " " +salaries);

        //json path alırken eğer array varsa [] index numarası ile yazmak zorundayız yoksa list hatası verir
        //json path alırken eğer array yoksa normal nokta . ile parent to child şeklinde path alınır




    }
}
