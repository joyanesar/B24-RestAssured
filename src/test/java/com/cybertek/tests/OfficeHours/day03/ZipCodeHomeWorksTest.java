package com.cybertek.tests.OfficeHours.day03;
import com.cybertek.tests.Pojo.Place;
import com.cybertek.tests.Pojo.ZipCode.ZipInfo;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
public class ZipCodeHomeWorksTest {
    @BeforeAll
    public static void setUp() {
        baseURI = "http://api.zippopotam.us"; // pojo plain object java


    }
    //pojo class for zipcode
    @Test
    public void zipCodePojoTest(){
        Response response = given().accept(ContentType.JSON)
                .when().get("/us/12189");
        assertThat(response.statusCode(),is(200));
        ZipInfo zipInfo = response.as(ZipInfo.class);
        //de-serialization of json response body , Jackson json parser libray
        System.out.println("zipInfo = " + zipInfo );

        assertThat(zipInfo.getPostCode(),equalTo("12189"));
        assertThat(zipInfo.getCountry(),is(equalTo("United States")));
        assertThat(zipInfo.getCountryAbbreviation(),is(equalTo("US")));
        System.out.println(zipInfo.getPlaces().get(0).getPlaceName());
        Place place = zipInfo.getPlaces().get(0);
        assertThat(place.getPlaceName(),is(equalTo("Watervliet")));
        assertThat(place.getState(),is(equalTo("New York")));
     assertThat(place.getLatitude(),is(equalTo("42.7298")));
        assertThat(place.getLongitude(),equalTo("-73.7123"));
        assertThat(place.getStateAbbreviation(),equalTo("NY"));

    }
    @Test
    public void zipcodeHamcrestChainingTest() {
         given().accept(ContentType.JSON)
                .when().get("/us/20171")
                .then().assertThat().statusCode(200)
                .and().contentType(ContentType.JSON)
                .and().body("\"post code\"",equalTo("20171"),"country",
                         equalTo("United States"),"'country abbreviation'", equalTo("US"),
                         "places[0].'place name'",equalTo("Herndon"),
                         "places[0].'longitude'",equalTo("-77.3928"),
                         "places[0].'state'", equalTo("Virginia"),
                         "places[0].'state abbreviation'",equalTo("VA"),
                         "places[0].latitude",equalTo("38.9252"));


    }
}