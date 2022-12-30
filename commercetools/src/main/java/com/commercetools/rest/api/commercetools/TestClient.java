package com.commercetools.rest.api.commercetools;

import com.commercetools.api.client.ProjectApiRoot;
import com.commercetools.api.models.customer.Customer;
import com.commercetools.api.models.customer.CustomerDraft;

public class TestClient {

	public static void main(String[] args) {
		// Create a CustomerDraft with the required fields (email address and password)
		ProjectApiRoot apiRoot = Client.createApiClient();


		CustomerDraft newCustomerDetails = CustomerDraft
		  .builder()
		  .email("java-sdk@example.com")
		  .password("password")
		  .build();

		// Post the CustomerDraft and get the new Customer
		Customer customer = apiRoot
		  .customers()
		  .post(newCustomerDetails)
		  .executeBlocking()
		  .getBody()
		  .getCustomer();

		// Output the Customer ID
		String customerID = customer.getId();
		System.out.println(customerID);

	}

}
