package com.cybertek.tests.day05_jsonpath;
import com.cybertek.tests.Pojo.ORDSTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
public class ORDSGetRequestWithJsonPathTest extends ORDSTestBase {
    /**
     Given accept is json
     when I send get request to ords/hr/employees/103
     Then status code is 200
     and content type header is json
     and employee_id is 103
     and first_name is Alexander
     and last_name is Hunold
     and salary is 9000
     */
    @DisplayName("GET ords/hr/employees/103 and jsonPath")
    @Test
    public void jsonPathSingleEmpInfoTest() {
        Response response = given().accept(ContentType.JSON).and().get("employees/103");
           //assign json response body to JsonPath object
        JsonPath json = response.jsonPath();

        System.out.println("Status code " + response.statusCode());
        assertEquals(200,response.statusCode());
        //read values from jasonPath object
        int empID = json.getInt("employee_id");
        String firstName = json.getString("first_name");
        String lastName = json.getString("last_name");
        int salary = json.getInt("salary");
        System.out.println("empID = " + empID);
        System.out.println("firstName = " + firstName);
        System.out.println("lastName = " + lastName);
        System.out.println("salary = " + salary);

        assertEquals(103,empID);
        assertEquals(firstName,"Alexander");
        assertEquals(lastName,"Hunold");
        assertEquals(9000,salary);
//        System.out.println("contain type " + response.contentType());
//        System.out.println("employee_id = " + json.getString("employee_id"));
//        System.out.println("employee _ First name = " + json.getString("first_name"));
//        System.out.println("employee Last name = " + json.getString("last_name"));
//        System.out.println("employee salary = " + json.getString("salary"));
        
    }
    @DisplayName("GET ords/hr/employees and using jsonPath filters")
    @Test
    public void jsonPathFilterTest() {
        Response response = given().accept(ContentType.JSON)
                .and().queryParam("limit",500)
                .when().get("/employees/"); // it print all employees

        System.out.println("response status code " + response.statusCode());
        response.prettyPrint();
        JsonPath json = response.jsonPath();
        //employees that work in department 90
        List<String> firstNames = json.getList("items.findAll{it.department_id==90}.first_name"); // it does filter
        System.out.println("Emp first name = " + firstNames);
        //name of emp who are "IT_PROG"
        List<String> itProgsList = json.getList("items.findAll{it.job_id=='IT_PROG'}.first_name");
        System.out.println("itProgsList = " + itProgsList);
        //em ids of employees whose salary is more than 5000
        List<Integer> empIds = json.getList("items.findAll{it.salary>500}.employee_id");
        System.out.println("empIds = " + empIds);
        System.out.println("empIds.size() = " + empIds.size());

        //find the person firstName with maximum salary
        String firstNameMaxSalary = json.getString("items.max{it.salary}.first_name");
        System.out.println("firstNameMaxSalary = " + firstNameMaxSalary);
        //find the person firstName with minimum salary
        String firstNameMinSalary = json.getString("items.min{it.salary}.first_name");
        System.out.println("firstNameMinSalary = " + firstNameMinSalary);


    }
}
