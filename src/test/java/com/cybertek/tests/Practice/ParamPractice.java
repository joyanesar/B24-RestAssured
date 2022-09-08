package com.cybertek.tests.Practice;

import com.cybertek.utilities.ConfigurationReader;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.poi.ss.usermodel.ConditionFilterData;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
/**
 *       * Given accept type is Json
 *      *      *         And query parameter values are:
 *      *      *         gender|Female
 *      *      *         nameContains|N
 *      *      *         When user sends GET request to /api/spartans/search
 *      *      *         Then response status code should be 200
 *      *      *         And response content-type: application/json
 *      *      *         And "Female" should be in response body
 *      *      *         And "Janette" should be in response body
 */
public class ParamPractice {
    @BeforeAll
    public static void setUp(){
        baseURI = ConfigurationReader.getProperty("spartan.url");
    }
    @Test
    public void getSearchForSpecificNameInQuery(){
        Response response = given().accept(ContentType.JSON)
                .queryParam("gender","Female")
                .and().queryParam("Contains","N")
                        .when().get("api/spartans/search");
        response.statusCode();
        assertEquals(200,response.statusCode());
        System.out.println(response.statusCode());
        System.out.println(response.asString().contains("Nona"));
        assertTrue(response.asString().contains("Nona"));
        System.out.println(response.contentType());
        assertEquals("application/json",response.contentType());
        assertTrue(response.asString().contains("gender"));
        System.out.println("phone" + response.asString().contains("phone"));
        assertTrue(response.asString().contains("Nona"));
    }
    @Test
    public void getPathParam(){
        Response response = given().accept(ContentType.JSON).and().pathParam("id",30)
                .when().get("api/spartans/{id}");
        response.statusCode();
        assertEquals(200,response.statusCode());
        System.out.println(response.statusCode());
        assertTrue(response.asString().contains("id"));
        System.out.println("response.contentType() = " + response.contentType());
          }
          @Test
    public void getQueryParam(){
        Response response = given().accept(ContentType.JSON).queryParam("gender","Female")
                .and().queryParam("nameContains","L")
                .when().get("api/spartans/search");
        response.statusCode();
              System.out.println(response.statusCode());
              assertEquals(200,response.statusCode());
              System.out.println(response.contentType());
              assertTrue(response.asString().contains("Female"));
              assertTrue(response.asString().contains("phone"));
              assertTrue(response.asString().contains("id"));

    }
}
