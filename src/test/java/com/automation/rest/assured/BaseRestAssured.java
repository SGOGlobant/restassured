package com.automation.rest.assured;

import static io.restassured.RestAssured.given;
import static com.jayway.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

public class BaseRestAssured {

	private final String baseURI = "https://jsonplaceholder.typicode.com";
	private RequestSpecification request;
	private String resource;

	public RequestSpecification getRequest() {
		return request;
	}

	public void setRequest(RequestSpecification request) {
		this.request = request;
	}

	public String getResource() {
		return resource;
	}

	public void setResource(String resource) {
		this.resource = resource;
	}

	public String getBaseURI() {
		return baseURI;
	}

	public BaseRestAssured() {
		request = given().baseUri(baseURI).and().queryParam("format", "json");
	}

	/**
	 * Funcion que obtien el C�digo de Estatus del recurso JSON
	 * 
	 * @return
	 */
	public int statusCode() {
		Response response = request.when().get(getResource());
		request.when().get(getResource()).then().log().all();
		return response.getStatusCode();
	}

	public ValidatableResponse validateSchema(String urlSchema) {
		// Comparamos que el squema que estamos cargando actualmente corresponda al
		// esquema del recurso que estamso cargando
		System.out.println(this.getClass().getResource("/").getPath());  
		return request.when().get(getResource()).then().assertThat().body(matchesJsonSchemaInClasspath(urlSchema));
	}
}
