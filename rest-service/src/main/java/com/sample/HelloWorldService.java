package com.sample;
 
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
 
@Path("/")
public class HelloWorldService {
 
	@GET
	@Path("/{param}")
	public Response getMsg(@PathParam("param") String msg) {
 
		String output = "Jersey say : " + msg;
 
		return Response.status(200).entity(output).build();
 
	}
	
	@POST
	@Path("/{param}")
	public Response getPostMsg(@PathParam("param") String msg, 
								@QueryParam("size") Integer size) {
		String output = "Post: " + msg + ", Size: " + size;
 
		return Response.status(200).entity(output).build();
	}
 
 	@GET
	@Path("/get")
	@Produces(MediaType.APPLICATION_JSON)
	public Data getTrackInJSON() {

		Data data = new Data();
		data.setUsername("default");
		data.setPassword("password");
		
		return data;

	}
 
	@POST
	@Path("/post/")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getPostMsg(Data data) {
		String output = "Received: " + data;
 
		return Response.status(201).entity(output).build();
	}
}