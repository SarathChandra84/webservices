package com.commercetools.rest.api.commercetools.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.commercetools.api.client.ProjectApiRoot;
import com.commercetools.api.models.subscription.GoogleCloudPubSubDestinationBuilder;
import com.commercetools.api.models.subscription.MessageSubscriptionBuilder;
import com.commercetools.api.models.subscription.SubscriptionDraftBuilder;
import com.commercetools.rest.api.commercetools.CommerceToolClient;



@RestController
public class SubscriptionController {

	@Autowired
	private CommerceToolClient commerceToolClient;
	
	
	@GetMapping("/createSubscription")
	public String createProductType(@PathVariable String subscriptionKey, 
									@PathVariable String projectId, 
									@PathVariable String subscriptionTopic,
									@PathVariable String resourcetype,
									@PathVariable String resourceMessagetype)
	{
		try {
			ProjectApiRoot apiRoot = commerceToolClient.createApiClient();
			apiRoot
	                        .subscriptions()
	                        .post(
	                                SubscriptionDraftBuilder.of()
	                                        .key(subscriptionKey)
	                                        .destination(
	                                                //for GCP Pub/Sub topic
	                                                GoogleCloudPubSubDestinationBuilder.of()
	                                                        .projectId(projectId)
	                                                        .topic(subscriptionTopic)
	                                                        .build()
	                                                //for AWS SQS Queue
//	                                                SqsDestinationBuilder.of()
//	                                                        .queueUrl("https://sqs.eu-central-1.amazonaws.com/923270384842/training-customer_change_queue")
//	                                                        .region("eu-central-1")
//	                                                        .accessKey("AKIAJLJRDGBNBIPY2ZHQ")
//	                                                        .accessSecret("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")
//	                                                        .build()
	                                        )
	                                        .messages(
	                                                MessageSubscriptionBuilder.of()
	                                                        .resourceTypeId(com.commercetools.api.models.subscription.MessageSubscriptionResourceTypeId.ORDER) // https://docs.commercetools.com/api/types#referencetype
	                                                        .types(resourceMessagetype) // https://docs.commercetools.com/api/message-types
	                                                        .build()
	                                        )
	                                        .build()
	                        )
	                        .execute()
	                        .toCompletableFuture().get()
	                        .getBody();
			return "Success";
		}
		catch(Exception e) {
			return "Exception" + e.getMessage();
		}

	}
}
