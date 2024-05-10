
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasKey;

public class RestAssuresTest extends RestAssuredBaseTest{

    @Test
    public void updateEmployeeTest(){
        RequestEmployee requestEmployee = RequestEmployee.builder()
                .name("Vasil")
                .salary("438000")
                .age("22")
                .build();

        ResponseEmployee response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(requestEmployee)
                .put("/update/{id}", 1)
                .then()
                .statusCode(200)
                .extract()
                .as(ResponseEmployee.class);

        assertThat(response.getMessage(), equalTo("Successfully! Record has been updated."));

    }

    @Test
    public void getAllTest(){
        Response response = RestAssured.given().log().all().get("/employees");
        response.then().statusCode(200);
        response.prettyPeek();

    }
    @Test
    public void getByIdTest(){
        Response response = RestAssured.given().log().all().get("/employee/{id}", 4);
        response.then().statusCode(200);
        response.prettyPeek();

    }


    @Test
    public void createTest(){
        RequestEmployee requestEmployee = RequestEmployee.builder()
                .name("FFFGGG")
                .age("33")
                .salary("333444")
                .build();

        ResponseEmployee response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(requestEmployee)
                .post("/create")
                .then()
                .statusCode(200)
                .extract()
                .as(ResponseEmployee.class);
        assertThat(response.getMessage(), equalTo("Successfully! Record has been added."));
        assertThat(response.getStatus(), equalTo("success"));
        System.out.println("Name: " + response.getMessage());
        System.out.println("Name: " + response.getStatus());
    }

    @Test
    public void deleteTest(){
        ResponseEmployee response = RestAssured.given()
                .when()
                .delete("/delete/{id}", 4)
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .log().body()
                .extract().as(ResponseEmployee.class);

        assertThat(response.getMessage(), equalTo("Successfully! Record has been deleted"));
        assertThat(response.getStatus(), equalTo("success"));

        System.out.println("Response: " + response);
    }
}

