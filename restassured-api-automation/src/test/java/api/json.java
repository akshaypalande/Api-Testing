package api;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class json {

	@Test(priority = 1)
	public void getTest() {
		Response response = RestAssured.get("http://localhost:3000/posts");

		System.out.println("Response code: " + response.getStatusCode());
		response.prettyPrint();
		int statusCode = response.statusCode();
		Assert.assertEquals(statusCode, 200);
	}

	@Test(priority = 2)
	public void postTest() {
		RequestSpecification requestSpecification = RestAssured.given();
		requestSpecification.header("Content-Type", "application/json");
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("id", 5);
		jsonObject.put("title", "Bridgelab");
		jsonObject.put("author", "author");
		requestSpecification.body(jsonObject.toJSONString());
		Response response = requestSpecification.post("http://localhost:3000/posts");
		System.out.println("responseCode: " + response.getStatusCode());
		response.prettyPrint();

	}

	@Test(priority = 3)
	public void putTest() {
		RequestSpecification requestSpecification = RestAssured.given();
		requestSpecification.header("Content-Type", "application/json");
		JSONObject jsonObject = new JSONObject();
		// jsonObject.put("id", 5);
		jsonObject.put("title", "Mumbai");
		jsonObject.put("author", "city");
		requestSpecification.body(jsonObject.toJSONString());
		Response response = requestSpecification.put("http://localhost:3000/posts/5");
		System.out.println("responseCode: " + response.getStatusCode());
		response.prettyPrint();

	}

	@Test(priority = 4)
	public void deleteTest() {
		RequestSpecification requestSpecification = RestAssured.given();
		requestSpecification.header("Content-Type", "application/json");
		Response response = requestSpecification.delete("http://localhost:3000/posts/5");
		System.out.println("responseCode: " + response.getStatusCode());
		response.prettyPrint();
	}

	// ----------------------------------Comment--------------------------------------
	// //

	@Test(priority = 5)
	public void getComment() {
		Response response = RestAssured.get("http://localhost:3000/comments");

		System.out.println("Response code: " + response.getStatusCode());
		response.prettyPrint();
		int statusCode = response.statusCode();
		Assert.assertEquals(statusCode, 200);
	}

	@Test(priority = 6)
	public void postComment() {
		RequestSpecification requestSpecification = RestAssured.given();
		requestSpecification.header("Content-Type", "application/json");
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("id", 12);
		jsonObject.put("body", "comment");
		jsonObject.put("postId", 12);
		requestSpecification.body(jsonObject.toJSONString());
		Response response = requestSpecification.post("http://localhost:3000/comments");
		System.out.println("responseCode: " + response.getStatusCode());
		response.prettyPrint();

	}

	@Test(priority = 7)
	public void putComment() {
		RequestSpecification requestSpecification = RestAssured.given();
		requestSpecification.header("Content-Type", "application/json");
		JSONObject jsonObject = new JSONObject();
		// jsonObject.put("id", 12);
		jsonObject.put("body", "comment");
		jsonObject.put("postId", 12);
		requestSpecification.body(jsonObject.toJSONString());
		Response response = requestSpecification.put("http://localhost:3000/comments/12");
		System.out.println("responseCode: " + response.getStatusCode());
		response.prettyPrint();

	}

	@Test(priority = 8)
	public void deleteComment() {
		RequestSpecification requestSpecification = RestAssured.given();
		requestSpecification.header("Content-Type", "application/json");
		Response response = requestSpecification.delete("http://localhost:3000/comments/12");
		System.out.println("responseCode: " + response.getStatusCode());
		response.prettyPrint();
	}
}