package com.cybertek.tests.HomeWorks;
import com.cybertek.utilities.ConfigurationReader;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.core.StringStartsWith;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
import com.cybertek.utilities.ConfigurationReader;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static io.restassured.RestAssured.baseURI;

public class Q1_Q2_3 {
    @BeforeAll
    public static void setUp() {
        baseURI = ConfigurationReader.getProperty("ords.url");
    }

    /**
     * Q1:
     * - Given accept type is Json
     * - Path param value- US
     * - When users sends request to /countries
     * - Then status code is 200
     * - And Content - Type is Json
     * - And country_id is US
     * - And Country_name is United States of America
     * - And Region_id is
     */
    @Test
    public void getUSAInJsonFormat(){
        Response response = given().accept(ContentType.JSON).and().queryParam("q","{\"country_id\":\"US\"}").when().get("countries");
        System.out.println(response.statusCode());
        System.out.println(response.contentType());
        System.out.println(response.asString().contains("country_id"));
        System.out.println(response.prettyPrint());
        System.out.println(response.asString().contains("id"));
    }

    /**
     * Q2:
     * - Given accept type is Json
     * - Query param value - q={"department_id":80}
     * - When users send request to /employees
     * - Then status code is 200
     * - And Content - Type is Json
     * - And all job_ids start with 'SA'
     * - And all department_ids are 80
     * - Count is 25
     */
    @Test
    public void getDepartmentId(){
        Response response = given().accept(ContentType.JSON).and().queryParam("q","{\"department_id\":80}")
                .with().get("employees");
        System.out.println(response.statusCode());
        assertEquals(200,response.statusCode());
        System.out.println("Content type = " + response.contentType());
        assertTrue(response.asString().contains("department_id"));
        System.out.println(response.getContentType());
        JsonPath jsonPath = response.jsonPath();
        List<String>allJobsIdName = jsonPath.getList("items.findAll{it.job_id=='SA_MAN'}.job_id");
        System.out.println("allJobsIdName = " + allJobsIdName);
       List<Integer> departmentIds = jsonPath.getList("items.findAll{it.department_id==80}.department_id");
       System.out.println("departmentI = " + departmentIds);
        String count = jsonPath.getString("count");
        System.out.println("count = " + count);
    }

    /**
     * Q3:
     * - Given accept type is Json
     * -Query param value q= region_id 3
     * - When users send request to /countries
     * - Then status code is 200
     * - And all regions_id is 3
     * - And count is 6
     * - And hasMore is false
     * - And Country_name are;
     * Australia,China,India,Japan,Malaysia,Singapore
     */
    @Test
    public void getRegionId(){
        Response response = given().accept(ContentType.JSON).queryParam("q","{\"region_id\":3}\"")
                .when().get("countries");

        JsonPath jsonPath = response.jsonPath();
        System.out.println("response.statusCode() = " + response.statusCode());
        List<Integer> countOfRegionIds =jsonPath.getList("items.findAll{it.region_id ==3}.region_id");
        System.out.println("countOfRegionIds = " + countOfRegionIds);
        String regionId = jsonPath.getString("region_id");
        System.out.println("regionId = " + regionId);
        



    }

}