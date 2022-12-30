package com.demo.rest.microservices.demorestmicroservice.bean;

public class HelloWorld {

	private String text;
	private String ip;

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public HelloWorld() {
		super();
		// TODO Auto-generated constructor stub
	}



	public HelloWorld(String text, String ip) {
		super();
		this.text = text;
		this.ip = ip;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
