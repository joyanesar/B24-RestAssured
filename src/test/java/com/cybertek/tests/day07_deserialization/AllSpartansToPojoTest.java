package com.cybertek.tests.day07_deserialization;
import com.cybertek.tests.Pojo.Spartan;
import com.cybertek.tests.Pojo.SpartanSearch;
import com.cybertek.tests.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import java.util.List;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class AllSpartansToPojoTest  extends SpartanTestBase {
    @Test
    public void getAllSpartansTest(){
        Response response = given().accept(ContentType.JSON)
                .when().get("api/spartans");
        System.out.println(response.statusCode());
       // AllSpartans allSpartans = response.as(AllSpartans.class);
        List<Spartan> allSpartans = response.jsonPath().getList("",Spartan.class); // here we converted list of json to pojo object
        System.out.println(allSpartans.get(0));
        System.out.println("Total spartans count = " + allSpartans.size());

        //Loop through the list and print each spartan info in separate list
        for(Spartan eachSpartan: allSpartans){
            if(eachSpartan.getGender().equals("Male")){
                System.out.println(eachSpartan.toString()); // toString to convert to string and also describe to object
            }
            System.out.println(eachSpartan);
        }
    }
    @Test
    public void searchSpartansToPojoTest(){
        Response response = given().accept(ContentType.JSON)
                .and().queryParam("nameContains","J")
                .and().queryParam("gender","Female")
                .when().get("api/spartans/search");
        System.out.println(response.statusCode());
        //deserialization .json body  >> converting spartanSearch, spartanSearch is pojo class
        SpartanSearch spartanSearch = response.as(SpartanSearch.class);
        System.out.println("total spartan = " + spartanSearch .getTotalElement());
        System.out.println("spartan info content = " + spartanSearch.getContent());
        System.out.println("spartan count " + spartanSearch.getContent().size());
       //store Jaimie info into separate spartan variable
        Spartan first = spartanSearch.getContent().get(0);
        System.out.println(first);
        System.out.println("id" + first.getId());
        System.out.println("name " + first.getName());
        System.out.println("gender " + first.getGender());
        System.out.println("phone " + first.getPhone());
        assertEquals(13,first.getId());
        assertEquals("Jaimie",first.getName());
        assertEquals("Female",first.getGender());
        assertEquals(7842554879L,first.getPhone());

    }
}
