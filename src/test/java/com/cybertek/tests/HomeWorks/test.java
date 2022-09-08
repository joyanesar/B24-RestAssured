package com.cybertek.tests.HomeWorks;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
import com.cybertek.tests.SpartanTestBase;
public class test  extends SpartanTestBase{

    @Test
    public void getEachSpartansNameInfo(){

        Response response = given().accept(ContentType.JSON)
                .when().get("api/spartans/27");
        response.statusCode();

        Map<String,Object> spartanMap = response.as(Map.class);
        System.out.println(spartanMap);
        System.out.println(spartanMap.get("name"));
        assertEquals("Jeanelle",spartanMap.get("name"));
        System.out.println(spartanMap.get("id"));
        assertEquals(27,spartanMap.get("id"));
        System.out.println(spartanMap.get("phone"));
        assertEquals(6662999903L,spartanMap.get("phone"));

    }
    @Test
    public void printAllSpartansInfo(){
        Response response = given().accept(ContentType.JSON)
                .when().get("api/spartans");
        response.statusCode();
        //print all spartans info
        
        List<Map<String,Object>> allSpartans = response.as(List.class);
        System.out.println("allSpartans = " + allSpartans);
        System.out.println("first " + allSpartans.get(0));
        System.out.println(" firs spartan id = " + allSpartans.get(0).get("id"));
        System.out.println("first spartan phone " + allSpartans.get(0).get("phone"));
        System.out.println("Fifth spartan name " + allSpartans.get(4).get("name"));
       // System.out.println("second " + allSpartans.get(1));
        for(Map<String,Object> eachMap: allSpartans){

            //System.out.println(eachMap.get("id"));
           // System.out.println(eachMap.get("name"));
            System.out.println(eachMap.size());
            //print in lambada style
            allSpartans.forEach(eachSp -> System.out.println(eachSp.get("name")));
        }


    }

}
