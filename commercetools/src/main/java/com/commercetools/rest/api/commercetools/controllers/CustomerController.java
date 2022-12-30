package com.commercetools.rest.api.commercetools.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.commercetools.api.client.ProjectApiRoot;
import com.commercetools.api.models.customer.Customer;
import com.commercetools.api.models.customer.CustomerDraft;
import com.commercetools.api.models.customer.CustomerPagedQueryResponse;
import com.commercetools.api.models.customer.CustomerUpdate;
import com.commercetools.rest.api.commercetools.CommerceToolClient;

@RestController
public class CustomerController {

	@Autowired
	private CommerceToolClient commerceToolClient;
	
	
	@PostMapping("/createCustomer")
	public String CreateCustomer(@RequestParam String email, @RequestParam String password) {
		// Create a CustomerDraft with the required fields (email address and password)
		ProjectApiRoot apiRoot = commerceToolClient.createApiClient();



		CustomerDraft newCustomerDetails = CustomerDraft.builder().email(email).password(password)
				.build();

		// Post the CustomerDraft and get the new Customer
		Customer customer = apiRoot.customers().post(newCustomerDetails).executeBlocking().getBody().getCustomer();

		// Output the Customer ID
		String customerID = customer.getId();
		return customerID;

	}

	@GetMapping("/getCustomer")
	public String getCustomer(@PathVariable String customerId) {
	
		ProjectApiRoot apiRoot = commerceToolClient.createApiClient();

		Customer queryCustomer = apiRoot.customers().withId(customerId).get()
				.executeBlocking().getBody();

		// Output the Customer's email address
		String customerEmail = queryCustomer.getEmail();
		return customerEmail;

	}

	@PostMapping("/updateCustomer")
	public String updateCustomer(@RequestParam String customerId, @RequestParam String firstName, @RequestParam String lastName) {

		ProjectApiRoot apiRoot = commerceToolClient.createApiClient();

		// Query the Customer to update
		Customer customerToUpdate = apiRoot.customers().withId(customerId).get()
				.executeBlocking().getBody();

		// Create the CustomerUpdate with the current version of the Customer and add
		// the actions
		CustomerUpdate customerUpdate = CustomerUpdate.builder().version(customerToUpdate.getVersion())
				.plusActions(actionBuilder -> actionBuilder.setFirstNameBuilder().firstName(firstName))
				.plusActions(actionBuilder -> actionBuilder.setLastNameBuilder().lastName(lastName)).build();

		// Post the CustomerUpdate and return the updated Customer
		Customer updatedCustomer = apiRoot.customers().withId(customerToUpdate.getId()).post(customerUpdate)
				.executeBlocking().getBody();

		// Output the updated Customer's full name
		String updatedCustomerName = updatedCustomer.getFirstName() + " " + updatedCustomer.getLastName();
		return updatedCustomerName;
	}

	@GetMapping("/findCustomerByEmail")
	public String findCustomerByEmail(@PathVariable String email) {
		ProjectApiRoot apiRoot = commerceToolClient.createApiClient();
		// Search for Customers whose email address matches the predicate variable
		CustomerPagedQueryResponse customerToFind = apiRoot.customers().get()
				.withWhere("email = :customerEmail", "customerEmail", email).executeBlocking()
				.getBody();

		// Output the Customer's details. As email addresses must be unique, the first
		// value of the results (0) should be accurate
		String customerID = customerToFind.getResults().get(0).getId();
		return customerID;
	}

}
