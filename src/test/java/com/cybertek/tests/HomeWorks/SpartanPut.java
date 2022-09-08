package com.cybertek.tests.HomeWorks;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
public class SpartanPut {
    @BeforeAll
    public void putRequest(){
        // request body update value , and content type header
        baseURI = "http://54.158.153.204:8000/api/spartans";
    }
    @Test
    public void PutRequest(){
        Map<String,Object> putName = new HashMap<>();
        putName.put("name","Mike");
        putName.put("gender","Female");
        putName.put("phone",1549421318484l);
        given().accept(ContentType.JSON)
                .and().pathParam("id",101)
                .and().body(put())
                .when().get("api/sparatans/{id}")
                .then().assertThat().statusCode(204);
    }
}
