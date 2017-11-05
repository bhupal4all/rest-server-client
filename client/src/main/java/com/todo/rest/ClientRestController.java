package com.todo.rest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.swing.text.html.parser.Entity;
import javax.ws.rs.core.MediaType;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

@RestController
@RequestMapping(value = "/")
public class ClientRestController {
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String defaultGet() {
		return "welcome to service two";
	}

	@RequestMapping(value = "/from/service/", method = RequestMethod.GET)
	public String httpURLConnection() {

		String URLString = "http://localhost:8010/post?username=test";
		StringBuilder finalOutput = new StringBuilder();

		try {
			java.net.URL url = new URL(URLString);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();

			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");

			System.out.println("Code: " + conn.getResponseCode());
			System.out.println("Message: " + conn.getResponseMessage());
			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

			System.out.println("Output from Server:\n");
			String output;
			while ((output = reader.readLine()) != null) {
				System.out.println(output);
				finalOutput.append(output);
			}

			conn.disconnect();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (ProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return "returned from service one: " + finalOutput.toString();
	}

	@RequestMapping(value = "/from/service/2", method = RequestMethod.GET)
	public String httppost() {
		String URLString = "http://localhost:8010/post?username=test";
		StringBuilder finalOutput = new StringBuilder();

		try {
			HttpClient client = HttpClientBuilder.create().build();
			HttpPost post = new HttpPost(URLString);
			HttpResponse response;
			response = client.execute(post);
			BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			String line = "";
			while ((line = rd.readLine()) != null) {
				System.out.println(line);
				finalOutput.append(line);
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return "returned from service one/2: " + finalOutput.toString();
	}

	@RequestMapping(value = "/postdata", method = RequestMethod.GET)
	public String postdata() {

		String URLString = "http://localhost:8010/postdata";
		StringBuilder finalOutput = new StringBuilder();

		try {
			java.net.URL url = new URL(URLString);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();

			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");

			String input = "{\"username\":\"bhup\",\"name\":\"bhupal\"}";

			OutputStream out = conn.getOutputStream();
			out.write(input.getBytes());
			out.flush();

			System.out.println("Code: " + conn.getResponseCode());
			System.out.println("Message: " + conn.getResponseMessage());
			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

			System.out.println("Output from Server:\n");
			String output;
			while ((output = reader.readLine()) != null) {
				System.out.println(output);
				finalOutput.append(output);
			}

			conn.disconnect();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (ProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return "returned from service one: " + finalOutput.toString();
	}

	@RequestMapping(value = "/postdata/2", method = RequestMethod.GET)
	public String postdata2() {
		String URLString = "http://localhost:8010/post";
		StringBuilder finalOutput = new StringBuilder();

		try {
			HttpClient client = HttpClientBuilder.create().build();
			HttpPost post = new HttpPost(URLString);

			List<NameValuePair> params = new ArrayList<>();
			params.add(new BasicNameValuePair("username", "bhu"));
			params.add(new BasicNameValuePair("name", "bhupal"));
			post.setEntity(new UrlEncodedFormEntity(params));

			HttpResponse response = client.execute(post);
			BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			String line = "";
			while ((line = rd.readLine()) != null) {
				System.out.println(line);
				finalOutput.append(line);
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return "returned from service one/2: " + finalOutput.toString();
	}

	@RequestMapping(value = "/postdata/3", method = RequestMethod.GET)
	public String jerseypostdata() {
		String URLString = "http://localhost:8010/postdata";
		StringBuilder finalOutput = new StringBuilder();

		try {
			Client client = Client.create();
			String input = "{\"username\":\"bhup\",\"name\":\"bhupal\"}";

			WebResource ws = client.resource(URLString);
			ClientResponse response = ws.type(MediaType.APPLICATION_JSON).post(ClientResponse.class, input);

			if (response.getStatus() != 200) {
				System.out.println("Error From Server:");
				System.out.println("Error Code: " + response.getStatus());
			} else {
				String output = response.getEntity(String.class);
				System.out.println("Output from Server:\n" + output);
				finalOutput.append(output);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "returned from service one/3: " + finalOutput.toString();
	}
}
