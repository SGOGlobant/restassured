package com.automation.rest.assured;

import static io.restassured.RestAssured.given;

public class CommentsRestAssured extends BaseRestAssured{
	
	public void getComments() {
		given().params("postId", 1).when().get(getBaseURI()+getResource()).body().print();
	}
}
