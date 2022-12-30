package com.graphql.rest.webservices.graphqldemo;

import java.awt.print.Book;

import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

@Controller
public class GraphQLController {

	
	@QueryMapping
	public String getProjectName()
	{
		return "test";

	}
}
