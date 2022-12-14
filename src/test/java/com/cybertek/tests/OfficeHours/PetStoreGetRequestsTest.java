package com.cybertek.tests.OfficeHours;
import io.restassured.path.json.JsonPath;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeAll;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class PetStoreGetRequestsTest {
    @BeforeAll
    public static void setUp() {
       baseURI = "https://petstore.swagger.io/v2";
    }
    /**
     * accept type is json
     * get request to https://petstore.swagger.io/v2/store/inventory
     * Then status code is 200
     * And content type is json
     * And date header should be present
     * and available is more than 500
     */
    @Test
    public void getInventoryTest() {
        Response response = given().accept(ContentType.JSON)
                .when().get("/store/inventory");

        System.out.println("Status code = " + response.statusCode());
        assertEquals(HttpStatus.SC_OK, response.statusCode());

        System.out.println("content type = " + response.contentType());
        System.out.println("date = " + response.getHeader("Date"));

        assertEquals("application/json", response.contentType());
        assertTrue(response.getHeaders().hasHeaderWithName("Date"));

        //path, jsonPath
        JsonPath json = response.jsonPath();

        //print available count
        System.out.println("available = " + json.getInt("available"));

        //available is more than 500
        assertEquals(Boolean.TRUE, json.getInt("available") >= 500);
        assertTrue(json.getInt("available") >= 500);

        response.prettyPrint();
    }

    /**
     * accept type is json
     * order id is 2
     * get request to store/order/2
     * Then status code is 200
     * And content type is json
     * id is 2
     * pet id is 20
     * status is "placed"
     * complete is true
     * */

    @Test
    public void getOrderPathParamTest() {
        Response response = given().accept(ContentType.JSON)
                .and().pathParam("orderId" , 2)
                .when().get("/store/order/{orderId}");
        //.when().get("/store/order/2");

        System.out.println("status code = " + response.statusCode());
        assertEquals(HttpStatus.SC_OK, response.statusCode());

        assertEquals("application/json" , response.contentType());

        JsonPath json = response.jsonPath();

        int orderId = json.getInt("id");
        int petId = json.getInt("petId");
        String status = json.getString("status");
        boolean complete = json.getBoolean("complete");

        System.out.println("orderId = " + orderId);
        System.out.println("petId = " + petId);
        System.out.println("status = " + status);
        System.out.println("complete = " + complete);

        assertEquals(2 , orderId);
        assertEquals(20 , petId);
        assertEquals("placed" , status);
        assertEquals(Boolean.TRUE, complete);
    }

    /**
     * accept type is json
     * query param status is "available"
     * get request to /pet/findByStatus
     * Then status code is 200
     * And content type is json
     * And see all pet names
     * And all status values should be "available"
     * */

    @Test
    public void searchPetsByStatusTest() {

        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("status" , "available");

        Response response = given().accept(ContentType.JSON)
                .and().queryParam("status" , "available")
                .when().get("/pet/findByStatus");
        //.and().queryParams(paramMap)

        System.out.println("status code = " + response.statusCode());
        //HttpStatus.SC_OK ==> 200
        assertEquals(HttpStatus.SC_OK, response.statusCode());

        assertEquals("application/json", response.contentType());
        JsonPath json = response.jsonPath();

        List<String> allNames = json.getList("name");
        System.out.println("allNames = " + allNames);
        System.out.println("count = " + allNames.size());

        List<String> allStatus = json.getList("status");
        System.out.println("allStatus = " + allStatus);

        //verify all are available
        for (String eachStatus : allStatus) {
            assertEquals("available" , eachStatus);
        }

        allStatus.forEach(eachStatus -> assertEquals("available",eachStatus));

    }
}