package com.cybertek.tests.day11_put_request;
import com.cybertek.tests.Pojo.Spartan;
import com.cybertek.tests.SpartanTestBase;
import com.cybertek.utilities.RestUtils;
import lombok.With;
import org.junit.jupiter.api.Test;
import com.cybertek.tests.Pojo.ORDSTestBase;
import com.cybertek.tests.Pojo.Region;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;
import javax.annotation.meta.When;
import java.util.LinkedHashMap;
import java.util.Map;

public class UpdateSpartanTest extends SpartanTestBase {
    @Test
    public void updateSpartanTest() {
        Map<String,Object> spartansMap = new LinkedHashMap<>();
        spartansMap.put("gender","Female");
        spartansMap.put("name","Sdet");
        spartansMap.put("phone",1234567892l);

        given().contentType(ContentType.JSON)
                .and().body(spartansMap)
                .when().put("/api/spartans/587")
                .then().assertThat().statusCode(is(204));
    }
    @Test
    public void patchSpartansTest(){
        Map<String,Object> spartanMap = new LinkedHashMap<>();
        spartanMap.put("phone",7778844552l);

        given().contentType(ContentType.JSON)
                .and().pathParam("id",587)
                .and().body(spartanMap)
                .when().patch("/api/spartans/{id}")
                .then().assertThat().statusCode(204);
        Spartan spartan = RestUtils.getSpartan(587);
        assertThat(spartan.getPhone(),is(spartanMap.get("phone")));
    }
}