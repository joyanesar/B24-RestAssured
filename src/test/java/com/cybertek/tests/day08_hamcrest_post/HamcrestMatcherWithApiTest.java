package com.cybertek.tests.day08_hamcrest_post;
import com.cybertek.tests.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
public class HamcrestMatcherWithApiTest  extends SpartanTestBase {
    @Test
    public void helloWorldApiTest(){
      given().accept(ContentType.JSON)
              .when().get("https://sandbox.api.service.nhs.uk/hello-world/hello/world")
              .then().assertThat().statusCode(200)
              .and().contentType("application/json")
              //.and().body("message" ,is(equalTo("Hello World!"));
              .and().body("message" ,equalTo("Hello World!"));

    }
    @Test
    public void reqResAllUsersTest() {
        given().accept(ContentType.JSON)
                .when().get("https://reqres.in/api/users")
                .then().assertThat().statusCode(200)
                .and().contentType("application/json")
                .and().body("total", equalTo(12),"total_pages",is(2))
                .and().body("data[0].first_name", is(equalTo("George")),"data[0].email",is(equalTo("george.bluth@reqres.in"))); // it will read firstName of first person

        //using items matchers
        given().accept(ContentType.JSON)
                .when().get("https://reqres.in/api/users")
                .then().assertThat().body("data.id",hasSize(6),"data.id",hasItems(1,2,3,4,5,6),"data.first_name",hasItems("George","Eve","Emma"),"data.last_name",hasItems("Ramos","Weaver"));
    }
    @Test
    public void singleSpartanTest(){
        given().accept(ContentType.JSON)
                .when().get("api/spartans/24")
                .then().assertThat().statusCode(200)
                .and().contentType("application/json")
                .and().body("id",is(24),"name",is("Julio"),"gender",is("Male"),"phone",is(9393139934L))
                .log().all();
    }
    @Test
    /**
     * in below example
     * send get request
     * verify status code and header
     * then convert json body to map Object and return
     * Deserialization
     */
    public void getMapTest(){
       Map<String,Object> spartanMap = given().accept(ContentType.JSON)
                .when().get("api/spartans/24")
                .then().assertThat().statusCode(200)
                .and().contentType("application/json").log().all()
                .and().extract().body().as(Map.class); // convert json body to Map and return
                // it can help retrieve values from response

        System.out.println("spartan = " + spartanMap);
        assertThat(spartanMap.get("id"),equalTo(24));
        //check the keys of json response
        assertThat(spartanMap.keySet(),containsInAnyOrder("id","name","gender","phone"));
        //check all values of json
        assertThat(spartanMap.values(),hasItems(24,"Julio","Male",9393139934L));
    }
    /**
     * given accept type json
     * get request to /api/spartans
     * then status code is 200
     * and content type is json
     * Then extract names of spartans into a List<String>
     */
    @Test
    public void getSpartanNamesTest() {
        List<String> names = given().accept(ContentType.JSON)
                .when().get("api/spartans")
                .then().assertThat().statusCode(200)
                .and().contentType(ContentType.JSON)
                .and().extract().body().jsonPath().getList("name");

        System.out.println("names = " + names);
        assertThat(names,hasSize(146));
        assertThat(names,hasItems("Wilek","Tucky","Jennica"));
        
        for(String eachNames: names ){
            System.out.println("FirstName  = " + eachNames);
        }
    }
    /**
     * given accept type json
     * name contains "v"
     * gender is "Male'
     * get request to /api/spartans/search
     * then status code is 200
     * and content type is json
     * and return totalElement value as an int
     */
    @Test
    public void getTotalElementTest(){
        int totalElement = given().accept(ContentType.JSON)
                .and().queryParam("nameContains","v")
                .and().queryParam("gender","Male")
                .when().get("api/spartans/search")
                .then().assertThat().statusCode(200)
                .and().contentType(ContentType.JSON)
                .and().extract().path("totalElement");
                //.and.extract().body.jsonpath().getInt("totalElement");
        System.out.println("total Element " + totalElement);
        assertThat(totalElement,is(equalTo(8)));
    }
    @Test
    public void invalidSpartanTest(){
        given().accept(ContentType.JSON)
                .when().get("api/spartans/2400")
                .then().assertThat().statusCode(404)
                .and().body("error",equalTo("Not Found"));
    }
}
