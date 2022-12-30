package com.commercetools.rest.api.commercetools.controllers;

import java.awt.print.Book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import com.commercetools.api.client.ProjectApiRoot;
import com.commercetools.api.models.project.Project;
import com.commercetools.rest.api.commercetools.CommerceToolClient;

@Controller
public class GraphQLController {

	@Autowired
	private CommerceToolClient commerceToolClient;
	
	@QueryMapping
	public String getProjectName()
	{
		// Create the apiRoot with your Client
		ProjectApiRoot apiRoot = commerceToolClient.createApiClient();

		// Make a get call to the Project
		Project myProject = apiRoot
		  .get()
		  .executeBlocking()
		  .getBody();
		
		return myProject.getName();

	}
	@SchemaMapping
    public Project author(Book book) {
		// Create the apiRoot with your Client
				ProjectApiRoot apiRoot = commerceToolClient.createApiClient();

				// Make a get call to the Project
				Project myProject = apiRoot
				  .get()
				  .executeBlocking()
				  .getBody();
		return myProject;
    }
}
