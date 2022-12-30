package com.commercetools.rest.api.commercetools.controllers;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.json.Json;
import javax.json.JsonObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.commercetools.api.client.ProjectApiRoot;
import com.commercetools.api.models.common.LocalizedStringBuilder;
import com.commercetools.api.models.custom_object.CustomObjectDraftBuilder;
import com.commercetools.api.models.type.CustomFieldBooleanType;
import com.commercetools.api.models.type.CustomFieldStringType;
import com.commercetools.api.models.type.FieldDefinition;
import com.commercetools.api.models.type.FieldDefinitionBuilder;
import com.commercetools.api.models.type.TypeTextInputHint;
import com.commercetools.rest.api.commercetools.CommerceToolClient;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class CustomObjectsController {

	@Autowired
	private CommerceToolClient commerceToolClient;
	
	
	@GetMapping("/createCustomType")
	public String CreateCustomType() {
		// Create a CustomerDraft with the required fields (email address and password)
		ProjectApiRoot apiRoot = commerceToolClient.createApiClient();

		Map<String, String> labelsForFieldCheck = new HashMap<String, String>() {
            {
                put("EN", "Allowed to place orders");
            }
        };
        Map<String, String> labelsForFieldComments = new HashMap<String, String>() {
            {
                put("EN", "comments");
            }
        };

        // Which fields will be used?
        List<FieldDefinition> definitions = Arrays.asList(
                FieldDefinitionBuilder.of()
                        .name("allowed-to-place-orders")
                        .required(false)
                        .label(LocalizedStringBuilder.of()
                                .values(labelsForFieldCheck)
                                .build()
                        )
                        .type(CustomFieldBooleanType.of())
                        .build()
                ,
                FieldDefinitionBuilder.of()
                        .name("Comments")
                        .required(false)
                        .label(LocalizedStringBuilder.of()
                                .values(labelsForFieldComments)
                                .build()
                        )
                        .type(CustomFieldStringType.of())
                        .inputHint(TypeTextInputHint.MULTI_LINE)            // shown as single line????
                        .build()
        );

        return "Success";
	}
	@PostMapping("/createCustomObject")
	public String CreateCustomObject() {
		// Create a CustomerDraft with the required fields (email address and password)
		try {
			ProjectApiRoot apiRoot = commerceToolClient.createApiClient();
	        JsonObject tulipObject = Json.createObjectBuilder()
	                .add("incompatibleProducts", "basil-seed-product")
	                .add("leafletID", "leaflet_1234")

	                .add("instructions",
	                        Json.createObjectBuilder()
	                                .add("title", "Flower Handling")
	                                .add("distance_in_m", "2")
	                                .add("watering", "heavy")
	                                .build()
	                )
	                .build();
	     String id=   apiRoot
	        .customObjects()
	        .post(
	                CustomObjectDraftBuilder.of()
	                        .container("plants-compatibility-info")
	                        .key("tulip-seed-product")
	                        .value(
	                                new ObjectMapper()
	                                        .readTree(tulipObject.toString()))
	                        .build()
	        )
	        .execute()
	        .toCompletableFuture().get()
	        .getBody().getId();
	     return id;
		}
		catch(Exception e) {
			return "exception" + e.getMessage();
		}

	}
}
