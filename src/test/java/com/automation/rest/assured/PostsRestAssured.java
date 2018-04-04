package com.automation.rest.assured;

import static io.restassured.RestAssured.given;

import org.hamcrest.Matchers;

import io.restassured.response.Response;

public class PostsRestAssured extends BaseRestAssured {
	private Integer userId;
	private Integer id;
	private String title;
	private String body;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public PostsRestAssured() {
		super();
	}
	
	public void getById(int id) {
		Response body = getRequest().when().get(getResource()+"/"+id);
		body.then().assertThat().statusCode(200)
			.body("id", Matchers.is(id))
			.and()
			.body("userId", Matchers.is(getUserId()))
			.and()
			.body("title", Matchers.is(getTitle()))
			.and()
			.body("body", Matchers.is(getBody()));
	}
	
	public void getPosts(int userdId) {
		given().params("userId", userdId).when().get(getBaseURI()+getResource()).body().print();
	}
}
