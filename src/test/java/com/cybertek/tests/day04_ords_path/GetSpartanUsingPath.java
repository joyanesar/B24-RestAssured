package com.cybertek.tests.day04_ords_path;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
import com.cybertek.tests.SpartanTestBase;
import java.util.List;
/**
 * Given accept is json
 * And path param id is 13
 * When I send get request to /api/spartans/{id}
 * Then status code is 200
 * And content type is json
 * And id value is 13
 * And name is "Jaimie"
 * And gender is "Female"
 * And phone is "7842554879"
 */
public class GetSpartanUsingPath extends SpartanTestBase {
    @Test
    public void readJsonWithPathTest() {
        Response response = given().accept(ContentType.JSON).and().pathParam("id",13)
                .when().get("api/spartans/{id}");
        System.out.println(response.statusCode());
        System.out.println("response.contentType() = " + response.contentType());
        assertEquals(200,response.statusCode());
        System.out.println("id = " + response.path("id"));
        System.out.println("name = " + response.path("name"));
        System.out.println("gender " + response.path("gender"));
        System.out.println("phone  " + response.path("phone"));
        assertTrue(response.asString().contains("Jaimie"));
    }
    @Test
    public void getPathResponse(){
        Response response = given().accept(ContentType.JSON).and().pathParam("id",25)
                .when().get("api/spartans/{id}");
        response.statusCode();
        assertEquals(200,response.statusCode());
        System.out.println("response.contentType() = " + response.contentType());
        System.out.println("id " + response.path("id"));
        //Store
      //  String id = response.path("id");
//        String name = response.path("name");
//        String gender = response.path("gender");
//        String phoneNumber = response.path("phone");
        System.out.println("Name is " + response.path("name"));
        assertTrue(response.asString().contains("Valentin"));
        System.out.println("phone " + response.path("phone"));
        System.out.println("gender " + response.path("gender"));
        //assertion
       /// assertEquals(25,id);
//        assertEquals("Valentin",name);
//        assertEquals("Male",gender);
//        assertEquals(1536037088,phoneNumber);

           }
    /**Given accept is json
     When I send get request to /api/spartans
     Then status code is 200
     And content type is json
     And I can navigate json array object
     */
    @Test
    public void readJsonArrayTest() {
        Response response = given().accept(ContentType.JSON).when().get("api/spartans");
        response.statusCode();
        assertEquals(200,response.statusCode());
            System.out.println("response.contentType() = " + response.contentType());
        System.out.println("id = " +response.path("id")); // all ids
        System.out.println("id of first spartan = " + response.path("[0].id"));

        System.out.println("name of second person = " + response.path("[1].name"));
        System.out.println("Gender of second person = " + response.path("[1].gender"));
        System.out.println("second person phone  = " + response.path("[1].phone"));

        System.out.println("Name of person 50 = " + response.path("[50].name"));
        System.out.println("Gender of person 50 =  " + response.path("[50].gender"));
        System.out.println("Phone number of person 50 = " + response.path("[50].phone"));
        //print first third person id name , gender, phone

        System.out.println("Third person " + response.path("[2].id").toString());
        System.out.println("Third person name = " + response.path("[2].name").toString());
        System.out.println("Third person gender = " + response.path("[2].gender").toString());
        System.out.println("Third person phone =A" + response.path("[2].phone").toString());

        //Person last person info
        System.out.println("Last person info --");
        System.out.println(response.path("[-1].id").toString());
        System.out.println(response.path("[-1].name").toString());
        System.out.println(response.path("[-1].gender").toString());
        System.out.println(response.path("[-1].phone").toString());

        System.out.println("Last person info");
        System.out.println(response.path("id[-1]").toString());
        System.out.println("Last person name " + response.path("name[-1]").toString());
        System.out.println("Last person gender " + response.path("gender[-1]").toString());
        System.out.println("Last person phone " +response.path("phone[-1]").toString());
        //Store all first names into a list

        List<String> allFirstNames = response.path("name");
        System.out.println("name count " + allFirstNames.size());
        for(String eachNames: allFirstNames){
           // System.out.println(eachNames);
            System.out.println(eachNames.toUpperCase());
           assertTrue(response.asString().contains("tester"));
        }
    }
}
