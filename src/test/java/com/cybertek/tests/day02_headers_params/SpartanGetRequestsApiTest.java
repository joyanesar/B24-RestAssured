package com.cybertek.tests.day02_headers_params;
import com.cybertek.utilities.ConfigurationReader;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
public class SpartanGetRequestsApiTest {
    String baseUrl = ConfigurationReader.getProperty("spartan.url");
    @Test
    public void getAllSpartansTest(){
        Response response =when(). get(baseUrl + "/api/spartans");
        System.out.println("status code " + response.statusCode());
        assertEquals(200,response.getStatusCode());
        response.asPrettyString();
        assertTrue(response.asString().contains("Correy"));

        System.out.println("response.asPrettyString() = " + response.asPrettyString());

    }

    /**
     * Given Accept type is "application/json"
     * When I send a GET request to
     * spartan_base_url/api/spartans
     * Then Response STATUS CODE must be 200
     * And content type should be "application/json"
     */
    @Test
    public void allSpartansHeadersTest() {

       Response response = given().accept(ContentType.JSON).when().get(baseUrl + "/api/spartans");
        System.out.println("response = " + response.statusCode());
        assertEquals(200,response.getStatusCode());
        System.out.println(response.contentType()); //Read Content Type response header
        assertEquals("application/json", response.contentType());
        //print more response headers
        System.out.println("response.getHeader(\"Date\") = " + response.getHeader("Date"));
        System.out.println(response.getHeader("Transfer-Encoding"));
        //Verify if the header is there
        assertTrue(response.getHeaders().hasHeaderWithName("Date"));
    }
}
