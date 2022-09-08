package com.cybertek.tests.day09_post_put;

import com.cybertek.tests.Pojo.Spartan;
import com.cybertek.tests.SpartanTestBase;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import com.cybertek.tests.SpartanTestBase;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class SpartanPostRequestTest extends SpartanTestBase {
    /**
     * given accept is json
     * and content type is json
     * when I send POST request to /api/spartans
     * with {
     *   "gender": "Male",
     *   "name": "Teacher Request",
     *   "phone": 2112562345
     * }
     * Then status code is 201
     * And "spartan is born" message should be displayed
     */
    @Test
    public void postSpartanTest(){
        String requestJson = "{\"gender\": \"Male\",\n" +
                "\"name\": \"RestAssuredPost\",\n" +
                "\"phone\": 2115552345}";
       Response response = given().accept(ContentType.JSON) // here i am send json
                .and().contentType(ContentType.JSON)  // I accept json type
               .and().body(requestJson)
                .when().post("/api/spartans");
        System.out.println("status cod = " + response.statusCode());
        assertThat(response.statusCode(), is(201));  // this hamcrest class method
        assertEquals(201,response.statusCode()); // verify header
        assertThat(response.contentType(), is("application/json"));
        //And "A"spartan is born" message should be displayed
        System.out.println("Message = " + response.path("success"));
        assertThat(response.path("success"),is(equalTo("A Spartan is Born!")));
        response.prettyPrint();
    }
    @Test
    public void  postSpartanWithMapTest(){
        Map<String,Object> requestMap = new LinkedHashMap<>();
        requestMap.put("name","TestMapPost");
        requestMap.put("gender","Female");
        requestMap.put("phone",3211231234l);
        Response response = given().accept(ContentType.JSON) // hey api I only accept json response
                .and().contentType(ContentType.JSON)        // hey api I am sending you json value
                .and().body(requestMap).log().all()
                .when().post("/api/spartans");
        System.out.println("status code = " +response.statusCode());

    /**
     *  /**
     *          * Response verifications
     *          * status code is 201
     *          * content type is json
     *          * name is TestMapPost
     *          * gender is Female
     *          * phone is 3211231234L
     *          * id is more than 0
     *          */
           assertThat(response.statusCode(), is(201));
        assertThat(response.contentType(),is("application/json"));
        JsonPath json = response.jsonPath();
        assertThat(json.getString("success"),is("A Spartan is Born!"));
        assertThat(json.getString("data.name"),is("TestMapPost"));
        assertThat(json.getString("data.gender"),is("Female"));
        assertThat(json.getString("data.phone"),is(3211231234L));
        assertThat(json.getInt("data.id"),greaterThan(0));
     }
     @Test
    public void postSpartanAndVerifyWithMapTest(){
        Map<String , Object> requestMap = new LinkedHashMap<>();
        requestMap.put("name","WoodenSpoon");
        requestMap.put("gender","Male");
        requestMap.put("phone",3211231234L);
        Response response = given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().body(requestMap)
                .when().post("/api/spartans");
         System.out.println(response.statusCode());
         assertThat(response.statusCode(),is(201));
         Map<String,Object> responseMap = response.jsonPath().getMap("data");
         System.out.println("responseMap = " +  responseMap);

         assertThat(responseMap.get("name"),equalTo(requestMap.get("name")));
         assertThat(responseMap.get("gender"),equalTo(requestMap.get("gender")));
         assertThat(responseMap.get("phone"),is(equalTo(requestMap.get("phone"))));

     }
        @Test
        public void postSpartanWithPojoTest(){
        //creat object of spartan and set values
            Spartan reqSpartan = new Spartan();
            reqSpartan.setName("POJOpost");
            reqSpartan.setGender("Female");
            reqSpartan.setPhone(9877891234L);
            //serialization : java object to json
            Response response = given().accept(ContentType.JSON)
                    .and().contentType(ContentType.JSON)
                    .and().body(reqSpartan).log().all() //automatically convert spartan object to json
                    .when().post("/api/spartans");

            System.out.println("Response status code = " + response.statusCode());
            assertThat(response.statusCode(),is(201));
            assertThat(response.contentType(), is("application/json"));
            //verify json body
            //assign response spartan info another spartan object
            //the compare 2 spartan values
            //de-serialization = jason to java object
            Spartan resSpartan = response.jsonPath().getObject("data", Spartan.class);

            //compare assert values
            assertThat(reqSpartan.getName(), equalTo(reqSpartan.getName()));
            assertThat(resSpartan.getGender(),equalTo(reqSpartan.getGender()));
            assertThat(resSpartan.getPhone(),equalTo(reqSpartan.getPhone()));
        }
}
