import io.restassured.RestAssured;
import org.testng.annotations.BeforeTest;

public class RestAssuredBaseTest {

    @BeforeTest
    public void setUp() {
        RestAssured.baseURI = "https://dummy.restapiexample.com/api/v1";
    }
}
