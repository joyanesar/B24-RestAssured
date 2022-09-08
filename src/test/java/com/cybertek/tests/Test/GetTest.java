package com.cybertek.tests.Test;
import com.cybertek.utilities.ConfigurationReader;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import io.restassured.http.ContentType;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
public class GetTest {
    String url = "https://jsonplaceholder.typicode.com/todos";
    @Test
    public void getTest(){
        Response response = RestAssured. given().accept(ContentType.JSON)
                .when().get(url);
        assertEquals(200,response.statusCode());
        assertEquals(response.contentType(),"application/json; charset=utf-8");
        System.out.println(response.body().prettyPrint());
        System.out.println(response.statusCode());
    }
    @Test
    public void postTest(){
        Response response = RestAssured.given().accept(ContentType.JSON)
                .when().post(url);
        //assertEquals(response.statusCode(),201);
        System.out.println(response.statusCode());
    }
    @Test
    public void putTest(){
        Response response = RestAssured.given().accept(ContentType.JSON)
                .when().put(url);

        assertEquals(200,response.statusCode());
    }

}
