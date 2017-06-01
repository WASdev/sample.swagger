package net.wasdev.swagger.sample.fvt.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;

import org.junit.Test;

public class ApplicationIT {
	
	@Test
	public void testContacts(){
		//check if endpoint works, should return empty JSON Array
		System.out.println("***URL***: " + getResponse("contacts"));
		assertEquals("[]", getResponse("contacts"));
	}
	
	@Test
	public void testTasks(){
		//check if endpoint works, should return empty JSON Array
		System.out.println("***URL***: " + getResponse("tasks"));
		assertEquals("[]", getResponse("tasks"));
	}
	
	@Test
	public void verifyDocs() {
		//verify that auto-generated Swagger contains both API's
		String docs = getDocs();
		System.out.println("***URL***: " + docs);
		System.out.println(docs);
		assertNotNull(docs);
		assertTrue(docs.contains("Contacts API"));
		assertTrue(docs.contains("Tasks API"));
	}
	
	private String getResponse(String endpoint){
		Client client = ClientBuilder.newClient();
		String port = System.getProperty("liberty.test.port");
        String url = "http://localhost:" + port + "/swagger-sample/demo/" + endpoint;
        System.out.println("Testing " + url);
        Response getResponse = client.target(url).request().get();
        String responseString = getResponse.readEntity(String.class);
        return responseString;
	}
	
	private String getDocs(){
		Client client = ClientBuilder.newClient();
		String port = System.getProperty("liberty.test.port");
        String url = "http://localhost:" + port + "/swagger-sample/swagger.json";
        System.out.println("Testing " + url);
        Response getResponse = client.target(url).request().get();
        String responseString = getResponse.readEntity(String.class);
        return responseString;
	}
}
