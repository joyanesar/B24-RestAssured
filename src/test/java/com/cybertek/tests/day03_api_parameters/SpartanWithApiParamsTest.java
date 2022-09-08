package com.cybertek.tests.day03_api_parameters;
import com.cybertek.utilities.ConfigurationReader;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;  //should copy
import static org.junit.jupiter.api.Assertions.*; //copy to every page

public class SpartanWithApiParamsTest {
    @BeforeAll //RUN ONCE BEFORE ALL TESTS
    public static void setUp() {
        System.out.println("Set up method: assigning value to baseURI variable");
        baseURI = ConfigurationReader.getProperty("spartan.url");
    }
    /**
     Given Accept type is json
     And path parameter id is 24
     When I send request to /api/spartans/24
     ----
     Then status code should be 200
     And content type should be "application/json"
     and json body should contain "Correy"
     */
    @Test
    public void getSpartanPathParamTest() {
        Response response = given().accept(ContentType.JSON)
                .when().get("/api/spartans/24");

        System.out.println("status code = " + response.statusCode());
        assertEquals(200, response.statusCode());

        System.out.println("content type = " + response.contentType());
        assertEquals("application/json", response.contentType());

        response.prettyPrint();
        assertTrue(response.asString().contains("Correy"));
    }
    /**
     * **
     *      * Given Accept type is json
     *      * And path parameter id is 1000
     *      * When I send request to /api/spartans/1000
     *      * -----------------
     *      * Then status code should be 404
     *      * And content type should be "application/json"
     *      * and json body should contain "Not Found"
     *      */
    @Test
    public void getSpartanPathParamNegativeTest() {
        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id",1000)
                .when().get("/api/spartans/{id}");

        System.out.println("status line = " + response.statusLine());
        assertEquals(404, response.statusCode());

        System.out.println("content type = " + response.contentType());
        assertEquals("application/json", response.contentType());

        response.prettyPrint();
        assertTrue(response.asString().contains("Not Found"));

    }
    @Test
    public void getSpartanPathPositiveParam(){
        Response response = given().accept(ContentType.JSON)
                .when().get("api/spartans/37");
        assertEquals(200,response.statusCode());
        System.out.println(response.statusCode());
        assertTrue(response.asString().contains("Grannie"));
        System.out.println(response.prettyPrint().contains("Grannie"));

    }
    @Test
    public void getSpartansPositives(){
        Response response = given().accept(ContentType.JSON).when().get("api/spartans/32");
        response.asPrettyString();
        response.statusCode();
        assertEquals(200,response.statusCode());
        System.out.println(response.prettyPrint());
        assertTrue(response.asString().contains("BruceWayne"));
        assertTrue(response.asString().contains("Male"));
        assertTrue(response.asString().contains("8811111111"));
        assertTrue(response.asString().contains("32"));
        System.out.println("content type =" + response.contentType());
    }
    @Test
    public void getSpartanNegativeTest(){
        Response response = given().accept(ContentType.JSON).when().get("api/spartans/35");

        response.statusCode();
        assertEquals(404,response.statusCode());
        assertTrue(response.asString().contains("Not Found"));
        System.out.println(response.prettyPrint());
        assertEquals("application/json",response.contentType());
    }
    @Test
    public void getNegativeParam(){
        Response response = given().accept(ContentType.JSON).and().pathParam("id",100).when().get("api/spartans/{id}");

        response.statusCode();
        assertEquals(404,response.getStatusCode());
        System.out.println(response.asPrettyString());
        assertTrue(response.asString().contains("Not Found"));
        System.out.println("status line ="  +response.statusLine());
    }

}
