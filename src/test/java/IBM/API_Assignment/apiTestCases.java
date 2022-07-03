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
		userdata[0][2] = "Doe";
		userdata[0][3] = "test@example.com";
		userdata[0][4] = "john1234";
		userdata[0][5] = 1234567890;
		userdata[0][6] = 0;
		
		return userdata;
		
	}
	
	@Test(enabled = true, dataProvider = "testdata")
	public void A1createuser(String uname, String fname, String lname, String email, String pass, int pno, int status)
	{
		RestAssured.baseURI = "https://petstore.swagger.io/v2";
		JSONObject obj = new JSONObject();
		
		obj.put("username", uname);
		obj.put("firstName", fname);
		obj.put("lastName", lname);
		obj.put("email", email);
		obj.put("password", pass);
		obj.put("phone", pno);
		obj.put("userStatus", status);
		
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
	public void A2getuser()
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
	public void A3loginuser()
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
	
	@Test(enabled = true)
	public void A4modify()
	{
		RestAssured.baseURI = "https://petstore.swagger.io/v2";
		
		JSONObject obj = new JSONObject();
		
		obj.put("firstName", "John1");
		obj.put("lastName", "Doe");
		obj.put("email", "test@example.com");
		obj.put("password", "john1234");
		obj.put("phone", "1234567890");
		obj.put("userStatus", 0);
		
		given()
		.header("content-type", "application/json")
		.body(obj.toJSONString())
		.when()
		.put("/user/john1234")
		.then()
		.statusCode(200)
		.log().all();
		
	}
	
	@Test(enabled = true)
	public void A5delete()
	{ 
		RestAssured.baseURI = "https://petstore.swagger.io/v2";
		
		given()
		.when()
		.delete("/user/john1234")
		.then()
		.statusCode(200)
		.log().all();
	}
	

}
