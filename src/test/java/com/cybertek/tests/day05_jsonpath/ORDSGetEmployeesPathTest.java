package com.cybertek.tests.day05_jsonpath;
import com.cybertek.tests.Pojo.ORDSTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
public class ORDSGetEmployeesPathTest extends ORDSTestBase {
    @Test
    public void getAllITProgrammersTest() {
        //query parameter with HashMap
        Map<String,String> paramMap = new HashMap<>();
        paramMap.put("q" , "{\"job_id\":\"IT_PROG\"}" );
        Response response = given().accept(ContentType.JSON)
                .and().queryParams(paramMap)
                .when().get("/employees");
        System.out.println(response.statusCode());
        assertEquals(200,response.statusCode());
        response.prettyPrint();
        //print first employee id, first name , and last name , email
        System.out.println("First emp id " + response.path("items[0].employee_id").toString());
        System.out.println("First emp firstName " + response.path("items[0].first_name"));
        System.out.println("First emp LastName " + response.path("items[0].last_name"));
        System.out.println("First emp email " + response.path("items[0].email"));
        System.out.println("First emp phone " + response.path("items[0].phone_number"));
        System.out.println("First emp job id " + response.path("items[0].job_id"));
        //you want to email all IT_PROG, save all emails into List of String
        List<String> allEmails = response.path("items.email");
        System.out.println("allEmails.size() = " + allEmails);
        System.out.println("allEmails.size() = " + allEmails.size());
        //you want to text all it_progs save all phones into List of String
        List<String>allPhones = response.path("items.phone_number");
        System.out.println("allPhones = " + allPhones);
        //Verify that 590.423.4568 is among phone numbers
        assertTrue(allPhones.contains("590.423.4568"));
        JsonPath json = response.jsonPath();
        System.out.println("json.getString(\"employee_id\") = " + json.getString("employee_id"));
        System.out.println("json.getString(\"email\") = " + json.getString("email"));
        System.out.println("json.getString(\"first_name\") = " + json.getString("first_name"));
        System.out.println("json.getString(\"job_title\") = " + json.getString("job_title"));
        System.out.println("json.getInt(\"job_id\") = " + json.getString("job_id"));

    }

}
