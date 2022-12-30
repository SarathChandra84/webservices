package com.helloworld.microservices.helloworldrestapi.bean;

public class HelloWorld {

	private String text;

	public HelloWorld(String text) {
		super();
		this.text = text;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public HelloWorld() {
		super();
		// TODO Auto-generated constructor stub
	}
}
