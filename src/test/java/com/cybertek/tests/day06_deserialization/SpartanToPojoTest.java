package com.cybertek.tests.day06_deserialization;
import com.cybertek.tests.Pojo.Spartan;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
import com.cybertek.tests.SpartanTestBase;

import static org.junit.jupiter.api.Assertions.*;
import static io.restassured.RestAssured.given;

public class SpartanToPojoTest extends SpartanTestBase {
    @Test
    public void spartanToPojoTest() {
        Response response = given().accept(ContentType.JSON)
                .when().get("/api/spartans/24");

        Spartan spartan = response.as(Spartan.class);

        System.out.println(spartan.getId());
        System.out.println(spartan.getName());
        System.out.println(spartan.getGender());
        System.out.println(spartan.getPhone());
    }
}