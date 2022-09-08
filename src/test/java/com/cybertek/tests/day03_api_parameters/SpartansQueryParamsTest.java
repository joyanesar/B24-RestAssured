package com.cybertek.tests.day03_api_parameters;
import com.cybertek.utilities.ConfigurationReader;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import io.restassured.http.ContentType;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
public class SpartansQueryParamsTest {
    @BeforeAll //RUN ONCE BEFORE ALL TESTS
    public static void setUp() {
        System.out.println("Set up method: assigning value to baseURI variable");
        baseURI = ConfigurationReader.getProperty("spartan.url");
    }
    /**
     * /**
     *      * Given accept type is Json
     *      *         And query parameter values are:
     *      *         gender|Female
     *      *         nameContains|e
     *      *         When user sends GET request to /api/spartans/search
     *      *         Then response status code should be 200
     *      *         And response content-type: application/json
     *      *         And "Female" should be in response body
     *      *         And "Janette" should be in response body
     *      */

    @Test
    public void searchForSpartanQueryTest() {
        Response response =  given().accept(ContentType.JSON).queryParam("gender","Female")
                .and().queryParams("nameContains","e")
                .when().get("/api/spartans/search");
        System.out.println("status code = " + response.statusCode());
        assertEquals(200,response.statusCode());
        System.out.println("Content type =" + response.contentType());
        assertEquals("application/json",response.contentType());
        System.out.println(response.asString());
        assertTrue(response.asString().contains("Female"));
        assertTrue(response.asString().contains("Janette"));
    }
    @Test
    public void spartansQuerySearch(){
        Response response = given().accept(ContentType.JSON).when().get("api/spartans/53");
        response = given().accept(ContentType.JSON).queryParam("gender","Female").
                and().queryParam("nameContains","B").when().get("api/spartans/search");
        response.statusCode();
        System.out.println("status code = " + response.statusCode());
        assertEquals(200,response.statusCode());
        System.out.println(response.contentType());
        assertTrue(response.asString().contains("Berna"));
        assertTrue(response.asString().contains("Female"));

    }
    @Test
    public void getQuerySearch(){
        Response response = given().accept(ContentType.JSON).
                queryParam("gender","Male").and().queryParam("nameContains","M").when().get("api/spartans/search");
        System.out.println("response code= " +response.statusCode());
        assertEquals(200,response.statusCode());
        System.out.println("response.statusLine() = " + response.statusLine());
        assertTrue(response.asString().contains("Male"));
        assertTrue(response.asString().contains("Murodil"));
        System.out.println(response.prettyPrint());
        System.out.println(response.contentType());


    }
}