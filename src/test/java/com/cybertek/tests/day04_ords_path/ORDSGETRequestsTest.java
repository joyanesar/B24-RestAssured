package com.cybertek.tests.day04_ords_path;

import com.cybertek.utilities.ConfigurationReader;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
public class ORDSGETRequestsTest {
    @BeforeAll
    public static void setUp(){
        baseURI = ConfigurationReader.getProperty("ords.url"); //ords.url = http://54.205.239.177:1000/ords/hr
/**
 * Given accept type is json
 * When user send get request to /ords/hr/regions
 * Status code should be 200
 * Content type should be "application/json"
 * And body should contain "Europe"
 */
    }
    @Test
    public void getAllRegionsTest(){
        Response response = given().accept(ContentType.JSON).when().get("/regions");
        response.statusCode();
        assertEquals(200,response.statusCode());
        System.out.println("application/json = " + response.contentType() );
        assertTrue(response.asString().contains("Europe"));
        System.out.println(response.contentType());
        response.prettyPrint();
    }
    @Test
    public void getAllJob_idsTest(){
        Response response = given().accept(ContentType.JSON).when().get("/countries");

        response.statusCode();
        assertEquals(200,response.statusCode());
        assertTrue(response.asString().contains("Canada"));
        System.out.println(response.contentType());
    }

    /**
     * Given accept type is json
     * And Path param value is 1
     * When user send get request to /ords/hr/regions/{region_id}
     * Status code should be 200
     * Content type should be "application/json"
     * And body should contain "Europe"
     */
    @DisplayName("Get Request to /ords/hr/regions/1")
    @Test
    public void getSingleRegionsWithParam(){
            Response response = given().accept(ContentType.JSON).and().pathParam("region_id", 3).when().get("regions/{region_id}");
            response.statusCode();
        System.out.println(response.statusCode());
        assertTrue(response.asString().contains("Asia"));
        System.out.println(response.contentType());

    }
    @DisplayName("Get Request to ords/regions/region_name")
    /**
     * Given accept type is json
     * And query param q="{"region_name": "Americas"}"
     * When user send get request to /ords/hr/regions
     * Status code should be 200
     * Content type should be "application/json"
     * And region name should be "Americas"
     * And region id should be "2"
     */

    @Test
    public void getQueryParamTest(){
        Response response = given().accept(ContentType.JSON).and().queryParam("q","{\"region_name\":\"Americas\"}").when().get("regions");
        response.statusCode();
        assertEquals(200,response.statusCode());
        System.out.println(response.statusCode());
        assertTrue(response.asString().contains("Americas"));
        assertTrue(response.asString().contains("2"));
        System.out.println(response.asString().contains("Americas"));
        System.out.println(response.getContentType());






     }
}
