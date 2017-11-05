package com.todo.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.QueryParam;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/")
public class SampleRestController {
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String defaultGet() {
		return "welcome";
	}

	@RequestMapping(value = "/post", method = RequestMethod.POST)
	public String defaultPost(@QueryParam("username") String username, @QueryParam("name") String name) {
		System.out.println("Username " + username);
		System.out.println("Name " + name);

		return "[" + username + "," + name + "] captured";
	}

	@RequestMapping(value = "/postdata", method = RequestMethod.POST)
	@Consumes("application/json")
	public String dataport(@RequestBody Sample sample) {
		return sample.toString() + " Catptured";
	}
}
