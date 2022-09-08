package com.cybertek.tests.day12_api_authentication;
import static io.restassured.RestAssured.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
public class LibraryApiAccessToken {
    /**
     * Given accept is json
     * and form parameters email and password
     * with values "student30@library", "Sdet2022*"
     * When i send post request to https://library2.cybertekschool.com/rest/v1/login
     * Then status code 200
     * And I can extract the access token
     */

    @Test
    public void getLibraryTokenTest() {
        String token = given().accept(ContentType.JSON)
                .and().formParams("email","student30@library","password","Sdet2022*")
                .when().post("https://library2.cybertekschool.com/rest/v1/login")
                .then().assertThat().statusCode(200)
                .and().extract().body().path("token");
        System.out.println("token = " + token);
    }
    String accessToken = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VyIjp" +
            "7ImlkIjoiMTYwIiwiZnVsbF9uYW1lIjoiVGVzdCBTdHVkZW50IDMwIiwiZW1haWwiOiJzdHVkZW50Mz" +
            "BAbGlicmFyeSIsInVzZXJfZ3JvdXBfaWQiOiIzIn0sImlhdCI6MTY0NDU0Njk4NiwiZXhwIjoxNjQ3MTM4OTg2fQ.uVfDKmeoRjUaB8CrWZV27E8MOo9-i4fbCepJqy4a9Y8";
    @Test
    public void  getBooksForBorrowing(){
        Response response = given().accept(ContentType.JSON)
                .and().header("x-library-token",accessToken)
                .when().get("https://library2.cybertekschool.com/rest/v1/get_book_list_for_borrowing");
        System.out.println("response.statusCode() = " + response.statusCode());
        response.prettyPrint();
                
                

    }
}