package com.helloworld.microservices.helloworldrestapi;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RestController;


import com.helloworld.microservices.helloworldrestapi.bean.HelloWorld;



@RestController
public class HelloWorldController {

	@GetMapping("/hello")
	public HelloWorld sayHello() {
		return new HelloWorld("Hello");
	}
}
