package com.cybertek.tests.day08_hamcrest_post;

import com.cybertek.tests.Pojo.Country;
import com.cybertek.tests.Pojo.ORDSTestBase;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
public class ORDSCountryHamcrestTest extends ORDSTestBase {
    @Test
    public void  getCountryToPojoTest(){
        given().accept(ContentType.JSON)

                .when().get("/countries/ZW")
                .then().assertThat().statusCode(200)
                .and().body("country_id",equalTo("ZW"))
                .and().contentType(ContentType.JSON)
                .and().extract().body().as(Country.class);

    }


}
