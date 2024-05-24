package API_test;

import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class Verify_status_code_test {

	@Test(priority = 0)
	public void TC003_Verify_status_code_and_title() {
		RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
		String response = given().when().get("/todos/1").then().assertThat().statusCode(200).extract().response()
				.asString();
		JsonPath js = new JsonPath(response);
		String title = js.getString("title");
		Assert.assertEquals(title, "delectus aut autem");

	}

}
