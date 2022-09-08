package com.cybertek.tests.OfficeHours;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
public class ZipCodeHomeWorkTest {
    @BeforeAll
    public static void setUp(){
        baseURI = "http://api.zippopotam.us";
    }

    /**
     * Given Accept application/json
     * And path zipcode is 22031
     * When I send a GET request to /us endpoint
     * Then status code must be 200
     * And content type must be application/json
     * And Server header is cloudflare
     * And Report-To header exists
     * And body should contains following information
     *     post code is 22031
     *     country  is United States
     *     country abbreviation is US
     *     place name is Fairfax
     *     state is Virginia
     *     latitude is 38.8604
     */
    @Test
    public void zipCodTest(){
        Response response = given().accept(ContentType.JSON)
                .and().pathParam("postal-code",22031)
                .when().get("us/{postal-code}");
        System.out.println("status code = " +response.statusCode());
        assertEquals(200, response.statusCode());
        assertEquals("application/json", response.contentType());
        System.out.println("server = " + response.getHeader("Server"));
        assertEquals("cloudflare" , response.getHeader("Server"));
        System.out.println("report to exists? = " + response.getHeaders().hasHeaderWithName("Report-To"));
        assertTrue(response.getHeaders().hasHeaderWithName("Report-To"));


        //Json body verifications
        JsonPath jsonPath = response.jsonPath();
        //postal-code
        System.out.println("post code = " + jsonPath.getString("\"post code\""));
        assertEquals("22031" , jsonPath.getString("\"post code\""));
        System.out.println("country = " + jsonPath.getString("country"));
        assertEquals("United States",jsonPath.getString("country"));

        //Country abbreviation is Us
        System.out.println("country abbreviation =" + jsonPath.getString("'country abbreviation'"));
        assertEquals("US",jsonPath.getString("'country abbreviation'"));

        System.out.println("place name = " + jsonPath.getString("places[0].'place name'"));
        assertEquals("Fairfax",jsonPath.getString("places[0].'place name'"));
        System.out.println("latitude " + jsonPath.getString("places[0].latitude"));
      //  assertEquals("latitude", jsonPath.getString("places[0].latitude"));

    }
    @Test
    public void zipcodeJsonToMapTest(){
        Response response = given().accept(ContentType.JSON)
                .and().pathParam("postal-code",22031)
                .when().get("us/{postal-code}");
        //Deserialization Json > Map / Java collection
        Map<String,Object> jsonMap = response.as(Map.class);
        System.out.println("jsonMap = " + jsonMap);
        System.out.println("post code = " + jsonMap.get("post code"));
        assertEquals("22031",jsonMap.get("post code"));

        System.out.println("country = " + jsonMap.get("country"));
        assertEquals("United States",jsonMap.get("country"));
        System.out.println("country abbreviation = " + jsonMap.get("country abbreviation"));
        assertEquals("US" , jsonMap.get("country abbreviation"));

        List<Map<String,Object>> placesMapList = (List<Map<String, Object>>) jsonMap.get("places");
        System.out.println(placesMapList.get(0)); // print map object

        //print placename
        System.out.println("places info = " + placesMapList.get(0).get("place name"));
        //assign the map inside the list into a separate map
        Map<String,Object> placeMap = placesMapList.get(0);
        System.out.println("place name = " + placeMap.get("place name"));
        System.out.println("state = " + placeMap.get("state"));
        System.out.println("latitude = " + placeMap.get("latitude"));

        assertEquals("Fairfax",placeMap.get("place name"));
        assertEquals("Virginia", placeMap.get("state"));
        assertEquals("38.8604",placeMap.get("latitude"));


    }
}
