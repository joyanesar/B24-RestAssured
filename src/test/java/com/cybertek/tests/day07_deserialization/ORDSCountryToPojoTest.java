package com.cybertek.tests.day07_deserialization;
import com.cybertek.tests.Pojo.Country;
import com.cybertek.tests.Pojo.ORDSTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
public class ORDSCountryToPojoTest extends ORDSTestBase {
    @Test
    public void countryToPojoTest(){
        Response response = given().accept(ContentType.JSON)
                .when().get("countries/US");
         System.out.println("response = " + response.statusCode());
        assertEquals(HttpStatus.SC_OK,response.statusCode()); // instead of writing assertEqual(200,//resps.statusecod)

        Country country = response.body().as(Country.class);
        System.out.println(country.getCountryName());
        System.out.println(country.getCountryId());
        System.out.println(country.getRegionId());


    }
}
