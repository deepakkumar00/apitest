package IBM.API_Assignment;

import org.json.simple.JSONObject;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

import io.restassured.RestAssured;

public class apiTestCases {
	
	@DataProvider(name="testdata")
	public Object[][] data()
	{
		Object[][] userdata = new Object[1][7];
		userdata[0][0]= "john1234";
		userdata[0][1]= "John";
		userdata[0][2] = 
		userdata[0][3] =
		userdata[0][4] =
		userdata[0][5] = 
		
		return studentsdata;
		
	}
	
	@Test(enabled = false)
	public void createuser()
	{
		RestAssured.baseURI = "https://petstore.swagger.io/v2";
		JSONObject obj = new JSONObject();
		
		obj.put("username", "john1234");
		obj.put("firstName", "John");
		obj.put("lastName", "Doe");
		obj.put("email", "test@example.com");
		obj.put("password", "john1234");
		obj.put("phone", "1234567890");
		obj.put("userStatus", 0);
		
		given()
		.header("content-type", "application/json")
		.body(obj.toJSONString())
		.when()
		.post("/user")
		.then()
		.statusCode(200)
		.log()
		.all();	
	}
	
	@Test(enabled = true)
	public void getuser()
	{
		RestAssured.baseURI = "https://petstore.swagger.io/v2";
		
		given()
		.when()
		.get("/user/john1234")
		.then()
		.statusCode(200)
		.log()
		.all();
	}
	
	@Test(enabled = true)
	public void loginuser()
	{
		RestAssured.baseURI = "https://petstore.swagger.io/v2";
		
		given()
		.queryParam("username", "john1234")
		.queryParam("password", "john1234").log().all().
		when()
			.get("/user/login").
		then()
			.statusCode(200)
			.log().all();
		
	}
	
	@Test(enabled = false)
	public void modify()
	{
		
	}
	
	@Test(enabled = false)
	public void delete()
	{
		
	}
	

}
