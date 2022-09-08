package com.cybertek.tests.day09_post_put;
import com.cybertek.tests.Pojo.ORDSTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
public class ORDSCountries_HamcrestTest extends ORDSTestBase {
    /**
     * given accept type is json
     * when I send get request to /countries
     * Then status code is 200
     * and content type is json
     * and count should be 25
     * and country ids should contain "AR,AU,BE,BR,CA"
     * and country names should have "Argentina,Australia,Belgium,Brazil,Canada"
     */
    @Test
    public void getAllCountries(){
        given().accept(ContentType.JSON)
                .when().get("/countries")
                .then().assertThat().statusCode(200)
                .and().contentType(ContentType.JSON)
                .and().body("count", equalTo(25))
                .and().body("limit",equalTo(25))
                .and().body("items.country_id" , hasItems("AR","AU","BE","BR","CA"))
                .and().body("items.country_name", hasItems("Argentina","Australia","Belgium","Brazil","Canada"))
                .log().all();

        //Second way of using hamcrest for Api response verification
        //store response then do assertion
        Response response = given().accept(ContentType.JSON)
                .when().get("/countries");

        assertThat(response.statusCode(), equalTo(200));
        assertThat(response.contentType(), is("application/json"));
        assertThat(response.path("count"), is(25));
        assertThat(response.path("items.country_id"), hasItems("AR", "AU", "BE", "BR", "CA"));
        assertThat(response.path("items.country_name"), hasItems("Argentina", "Australia", "Belgium", "Brazil", "Canada"));

    }
}
