package com.cybertek.tests.HomeWorks;
import com.cybertek.tests.Pojo.ORDSTestBase;
import com.cybertek.tests.Pojo.PojoTests;
import com.cybertek.tests.Pojo.Spartan;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import io.restassured.http.ContentType;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
import com.cybertek.tests.SpartanTestBase;
import java.util.List;
import java.util.Map;
public class pojo extends ORDSTestBase {
    @Test
    public void pojoTest(){
        Response response = given().accept(ContentType.JSON)
                .when().get("countries/CA");
        response.statusCode();
        PojoTests pg = response.body().as(PojoTests.class);
        System.out.println(pg.getCountryName());


    }

}
