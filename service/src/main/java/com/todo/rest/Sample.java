package com.todo.rest;

public class Sample {
	String username;
	String name;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Sample [username=" + username + ", name=" + name + "]";
	}
	
	

}
