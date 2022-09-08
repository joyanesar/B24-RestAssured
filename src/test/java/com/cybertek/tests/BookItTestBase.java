package com.cybertek.tests;
import com.cybertek.utilities.ConfigurationReader;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import static io.restassured.RestAssured.*;
public abstract class BookItTestBase {
    @BeforeAll
    public static void setUp() {
        baseURI = ConfigurationReader.getProperty("bookit.base.url");
    }
    public String getAccessToken(String email, String password) {
        String accessToken = given().accept(ContentType.JSON)
                .and().queryParams("email", email,
                        "password",password)
                .when().get("/sign")
                .then().statusCode(200)
                .and().extract().body().path("accessToken");
        return "Bearer " + accessToken;
    }

}
