package com.commercetools.rest.api.commercetools.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.commercetools.api.client.ProjectApiRoot;
import com.commercetools.api.models.project.Project;
import com.commercetools.rest.api.commercetools.CommerceToolClient;

@RestController
public class HelloCommerceToolsController {

	@Autowired
	private CommerceToolClient commerceToolClient;
	
	@GetMapping("/projectName")
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

}
