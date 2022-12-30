package com.demo.rest.microservices.demorestmicroservice;

import java.net.InetAddress;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

import org.springframework.web.client.RestTemplate;
import com.demo.rest.microservices.demorestmicroservice.bean.HelloWorld;

@RestController
public class HelloWorldRestController {

	@Autowired
	private Environment environment;
	
	@GetMapping("/hello")
	public Object sayHello() {
		try {
			//return new HelloWorld("Hello Demo Application",InetAddress.getLocalHost().getHostAddress());
			ResponseEntity<Object> responseEntity = new RestTemplate().getForEntity("http://35.223.226.224:8080/hello", Object.class);
			return responseEntity.getBody();
		}
		catch(Exception e)
		{
			return new HelloWorld("Hello Demo Application","Not found");
		}
		
	}
}
