package com.cybertek.tests.day05_jsonpath;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
public class TypicodeInterviewTest {
    @BeforeAll
    public static void setUp() {  // why its static because it runs one time
        baseURI = "https://jsonplaceholder.typicode.com";
    }
    @Test
    public void getUserTest() {
        Response response = given().accept(ContentType.JSON)
                .when().get("users/1");
        System.out.println("response.statusCode() = " + response.statusCode());
        assertEquals(200,response.statusCode());
       // System.out.println(response.prettyPrint());
        //Convert response body to JsonPath
        JsonPath  json = response.jsonPath();
        String name = json.getString("name");
        String city = json.getString("city");
        System.out.println("city = " + city);
        System.out.println("Name = " + name);
        System.out.println("city name = " + json.getString("address.city"));
        System.out.println("zipcode " + json.getString("address.zipcode"));
        System.out.println("company name = " + json.getString("company.name"));
        System.out.println("lng  = " + json.getString("address.geo.lng"));
        System.out.println("lat = " + json.getString("address.geo.lat"));

    }

}
