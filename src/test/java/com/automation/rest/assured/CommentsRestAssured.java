package com.automation.rest.assured;

import static io.restassured.RestAssured.given;

public class CommentsRestAssured extends BaseRestAssured{
	
	public void getComments(int postId) {
		given().params("postId", postId).when().get(getBaseURI()+getResource()).body().print();
	}
}
