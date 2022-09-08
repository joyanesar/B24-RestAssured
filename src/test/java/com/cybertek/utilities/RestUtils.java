package com.cybertek.utilities;

import com.cybertek.tests.Pojo.Spartan;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;
public class RestUtils {
    /**
     * accecpt spartan ID and returns pojo object containing spartan info
     * @param spartanId
     * @return
     */
    public static Spartan getSpartan(int spartanId){
        Spartan spartan = given().accept(ContentType.JSON)
                .and().pathParam("id", spartanId)
                .when().get(ConfigurationReader.getProperty("spartan.url") + "/api/spartans/{id}")
                .then().assertThat().statusCode(200)
                .and().extract().body().as(Spartan.class);

        return spartan;

    }
}
