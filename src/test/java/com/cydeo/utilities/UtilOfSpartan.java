package com.cydeo.utilities;

import com.cydeo.pojos.Spartan;
import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class UtilOfSpartan {

    public static Map<String, Object> fakeSpartan(){
        Map<String , Object> spartan = new LinkedHashMap<>();
        Faker faker = new Faker();
        String name = faker.name().firstName();
        String gender = faker.options().option("Male", "Female");
        long phone = Long.parseLong(faker.numerify("##########"));
        spartan.put("name", name);
        spartan.put("gender", gender);
        spartan.put("phone", phone);
        return spartan;
    }


}
