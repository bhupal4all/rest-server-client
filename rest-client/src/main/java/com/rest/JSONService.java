package com.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

@Path("/")
public class JSONService {

	@GET
	@Path("/get")
	@Produces(MediaType.APPLICATION_JSON)
	public String getTrackInJSON() {
		String output = "";
		try {
			Client client = Client.create();

			WebResource webResource = client
					.resource("http://localhost:8080/sampleservice/rest/post");

			String input = "{\"username\":\"defaultuser\",\"password\":\"deafaultpassword\"}";

			ClientResponse response = webResource.type("application/json")
					.post(ClientResponse.class, input);

			if (response.getStatus() != 201) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ response.getStatus());
			}

			output = response.getEntity(String.class);
		} catch (Exception e) {
			output = "Exception:: " + e.getMessage();
		}

		return "Output from service: "+output;

	}	
}