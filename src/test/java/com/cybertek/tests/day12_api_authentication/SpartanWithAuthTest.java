package com.cybertek.tests.day12_api_authentication;

import com.cybertek.tests.SpartanTestBase;
import com.cybertek.utilities.ConfigurationReader;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class SpartanWithAuthTest {
    @BeforeAll
    public static void inti(){
        baseURI = ConfigurationReader.getProperty("spartan.url.auth");
    }
    /**
     * http://54.205.239.177:7000/api/spartans
     *
     * Username: admin
     * Password: admin
     *
     * Given accept type is json
     * and basic auth credentials are "admin" , "admin"
     * When I send get request to "/api/spartans"
     * Then status code is 200
     * and content type is json
     * And json response should have Spartan list
     * Murodil — Today at 7:42 PM
     */
    @Test
    public void AdminGetAllSpartans() {
      List<Integer> allIds =  given().accept(ContentType.JSON)
                .and().auth().basic("admin","admin")
                .when().get("api/spartans")
                .then().assertThat().statusCode(200)
                .and().contentType(ContentType.JSON)
                //.and().body("id",isA(List.class)).log().all(); // checks if the result is "id" path it prints all list
              .and().extract().body().path("id");
        System.out.println("allIds = " + allIds);
        System.out.println("allIds.size() = " + allIds.size());

    }
    @Test
    public void noAuthGetSpartansNegativeTest(){
        /**
         * /**
         *      * Given accept type is json
         *      * and no credentials
         *      * When I send get request to "/api/spartans"
         *      * Then status code is 401
         *      * and content type is json
         *      * And json response should have message: Unauthorized
         *      */

        given().accept(ContentType.JSON)
                .when().get("api/spartans")
                .then().assertThat().statusCode(401)
                .and().contentType(ContentType.JSON)
                .and().body("message",is(equalTo("Unauthorized")));

    }
}
