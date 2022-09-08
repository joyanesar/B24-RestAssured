package com.cybertek.tests.day06_deserialization;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
import com.cybertek.tests.SpartanTestBase;
public class SpartanJsonToCollectionsTest extends SpartanTestBase {
    /**
     * /**
     *      * given accept type is json
     *      * when I send get request to /api/spartans/24
     *      * Then status code is 200
     *      * And content type is json
     *      * And id is 24, name is Julio, gender is Male, phone is 1321321321
     *      */
    @Test
    public void singleSpartanMapTest(){
        Response response = given().accept(ContentType.JSON)
                .when().get("api/spartans/24");
        //Now we have converted the json to hashmap object
        Map<String,Object> spartanMap  = response.as(Map.class);
        System.out.println("spartanMap = " + spartanMap);
        //Why we use map because we want key and value
        //converting json response to Map object. key+value
        System.out.println("id =  " + spartanMap.get("id") );
        assertEquals(24,spartanMap.get("id"));
        System.out.println("name = " + spartanMap.get("name"));
        assertEquals("Julio",spartanMap.get("name"));
        System.out.println("gender= " + spartanMap.get("gender"));
        assertEquals("Male",spartanMap.get("gender"));
        System.out.println("phone = " + spartanMap.get("phone"));
        assertEquals(9393139934L ,spartanMap.get("phone"));
        //create  new map with expected values and compare to maps
        Map<String,Object> expected = new HashMap<>();
        expected.put("id",24);
        expected.put("name","Julio");
        expected.put("gender","Male");
        expected.put("phone",9393139934L);
        assertEquals(expected,spartanMap);
    }
    @Test
    public void allSpartansToMapListTest(){
        Response response = given().accept(ContentType.JSON)
                .when().get("api/spartans");
        List<Map<String,Object>> spartansList = response.as(List.class);// print all spartan list

        System.out.println("list = " + spartansList);
        System.out.println("First spartan info " + spartansList.get(0));
        System.out.println("Second spartan info = " + spartansList.get(1));
        System.out.println("Third spartan id = " + spartansList.get(2).get("id"));
        System.out.println("Third spartan name = " + spartansList.get(2));

        Map<String,Object> firstSpartan = spartansList.get(0);
        System.out.println("firstSpartan = " + firstSpartan);
        for(Map<String,Object> eachSpartan : spartansList){
            System.out.println(eachSpartan);  // we search by id , name phone
            System.out.println(eachSpartan.get("-1"));
            System.out.println(eachSpartan.get("id"));
            System.out.println(eachSpartan.get("name"));
            System.out.println(eachSpartan.size());
            //loop in lambada type
           // spartansList.forEach(eachSp -> System.out.println(eachSp.get("name")));

        }


    }

}
