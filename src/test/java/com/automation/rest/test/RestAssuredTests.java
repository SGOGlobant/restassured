package com.automation.rest.test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.automation.rest.assured.BaseRestAssured;
import com.automation.rest.assured.CommentsRestAssured;
import com.automation.rest.assured.PostsRestAssured;

import io.restassured.response.ValidatableResponse;

public class RestAssuredTests {

	@Test
	public void statusCodeValidation() {
		int statusCode;// Variable que contiene el código de respuesta
		// Obtnemos el resoruce de posts
		PostsRestAssured posts = new PostsRestAssured();
		posts.setResource("/posts");
		statusCode = posts.statusCode(); // Recuperamos el código de estatus
		assertEquals(statusCode, 200); // validamos con TestNG que sea 200
		System.out.println(String.format("%s\t%d", "Status Code Posts:", statusCode));
		// Obtnemos el resource de comments
		CommentsRestAssured comments = new CommentsRestAssured();
		comments.setResource("/comments");
		statusCode = comments.statusCode();
		assertEquals(statusCode, 200);
		System.out.println(String.format("%s\t%d", "Status Code Comments:", statusCode));
		// Obtenemos el resource de Albums
		BaseRestAssured albums = new BaseRestAssured();
		albums.setResource("/albums");
		statusCode = albums.statusCode();
		assertEquals(statusCode, 200);
		System.out.println(String.format("%s\t%d", "Status Code Albums:", statusCode));
		// Obtenemos el resoruce de photos
		BaseRestAssured photos = new BaseRestAssured();
		photos.setResource("/photos");
		statusCode = photos.statusCode();
		assertEquals(statusCode, 200);
		System.out.println(String.format("%s\t%d", "Status Code Photos:", statusCode));
		// Obtenemos el resoruce de todos
		BaseRestAssured todos = new BaseRestAssured();
		todos.setResource("/todos");
		statusCode = todos.statusCode();
		assertEquals(statusCode, 200);
		System.out.println(String.format("%s\t%d", "Status Code Todos:", statusCode));
		// Obtenemos el resoruce de users
		BaseRestAssured users = new BaseRestAssured();
		users.setResource("/users");
		statusCode = users.statusCode();
		assertEquals(statusCode, 200);
		System.out.println(String.format("%s\t%d", "Status Code Users:", statusCode));
	}

	@Test(dataProvider = "getSchemas")
	public void schemaValidationTest(String pathResource, String schema) {
		BaseRestAssured resource = new BaseRestAssured();
		resource.setResource(pathResource);
		ValidatableResponse response = resource.validateSchema(schema);
		assertNotNull(response, "El esquema es valido");
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
		posts.getPosts(1);
		CommentsRestAssured comments = new CommentsRestAssured();
		comments.setResource("/comments");
		comments.getComments(1);
	}

	/**
	 * Contiene la direcion de los recuros y la direccion de los esquemas que
	 * validan el json
	 * 
	 * @return
	 */
	@DataProvider(name = "getSchemas")
	public Object[][] getSchemas() {
		return new Object[][] { { "/posts", "posts-schema.json" }, { "/comments", "comments-schema.json" },
				{ "/albums", "albums-schema.json" }, { "/photos", "photos-schema.json" },
				{ "/todos", "todos-schema.json" }, { "/users", "users-schema.json" } };
	}

	/**
	 * Contiene la informacion de los nodos 20, 50 y 100 de posts
	 * 
	 * @return
	 */
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
