package com.automation.rest.test;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.automation.rest.assured.BaseRestAssured;
import com.automation.rest.assured.CommentsRestAssured;
import com.automation.rest.assured.PostsRestAssured;

public class RestAssuredTests {
	
	@Test
	public void statusCodeValidation() {
		int statusCode;
		PostsRestAssured posts = new PostsRestAssured();
		posts.setResource("/posts");
		statusCode = posts.statusCode();
		assertEquals(statusCode, 200);
		System.out.println(String.format("%s\t%d", "Status Code Posts:", statusCode));

		CommentsRestAssured comments = new CommentsRestAssured();
		comments.setResource("/comments");
		statusCode = comments.statusCode();
		assertEquals(statusCode, 200);
		System.out.println(String.format("%s\t%d", "Status Code Comments:", statusCode));

		BaseRestAssured albums = new BaseRestAssured();
		albums.setResource("/albums");
		statusCode = albums.statusCode();
		assertEquals(statusCode, 200);
		System.out.println(String.format("%s\t%d", "Status Code Albums:", statusCode));

		BaseRestAssured photos = new BaseRestAssured();
		photos.setResource("/albums");
		statusCode = photos.statusCode();
		assertEquals(statusCode, 200);
		System.out.println(String.format("%s\t%d", "Status Code Photos:", statusCode));

		BaseRestAssured todos = new BaseRestAssured();
		todos.setResource("/albums");
		statusCode = todos.statusCode();
		assertEquals(statusCode, 200);
		System.out.println(String.format("%s\t%d", "Status Code Todos:", statusCode));

		BaseRestAssured users = new BaseRestAssured();
		users.setResource("/albums");
		statusCode = users.statusCode();
		assertEquals(statusCode, 200);
		System.out.println(String.format("%s\t%d", "Status Code Users:", statusCode));
	}

	@Test(dataProvider = "getSchemas")
	public void schemaValidationTest(String pathResource, String schema) {
		BaseRestAssured resource = new BaseRestAssured();
		resource.setResource(pathResource);
		resource.validateSchema(schema);
	}

	@Test(dataProvider = "getIds")
	public void postByIdTest(int id, int userId, String title, String body) {
		PostsRestAssured resource = new PostsRestAssured();
		resource.setResource("/posts");
		resource.setUserId(userId);
		resource.setTitle(title);
		resource.setBody(body);
		resource.getById(id);
	}

	@Test
	public void getCommentsTest() {
		PostsRestAssured posts = new PostsRestAssured();
		posts.setResource("/posts");
		posts.getPosts();
		CommentsRestAssured comments = new CommentsRestAssured();
		comments.setResource("/comments");
		comments.getComments();
	}

	@DataProvider(name = "getSchemas")
	public Object[][] getSchemas() {
		return new Object[][] { { "/posts", "posts-schema.json" }, { "/comments", "comments-schema.json" },
				{ "/albums", "albums-schema.json" }, { "/photos", "photos-schema.json" },
				{ "/todos", "todos-schema.json" }, { "/users", "users-schema.json" } };
	}

	@DataProvider(name = "getIds")
	public Object[][] getIds() {
		return new Object[][] { { 20, 2, "doloribus ad provident suscipit at",
				"qui consequuntur ducimus possimus quisquam amet similique\nsuscipit porro ipsam amet\neos veritatis officiis exercitationem vel fugit aut necessitatibus totam\nomnis rerum consequatur expedita quidem cumque explicabo" },
				{ 50, 5, "repellendus qui recusandae incidunt voluptates tenetur qui omnis exercitationem",
						"error suscipit maxime adipisci consequuntur recusandae\nvoluptas eligendi et est et voluptates\nquia distinctio ab amet quaerat molestiae et vitae\nadipisci impedit sequi nesciunt quis consectetur" },
				{ 100, 10, "at nam consequatur ea labore ea harum",
						"cupiditate quo est a modi nesciunt soluta\nipsa voluptas error itaque dicta in\nautem qui minus magnam et distinctio eum\naccusamus ratione error aut" } };
	}
}
