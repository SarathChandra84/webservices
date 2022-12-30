package com.commercetools.rest.api.commercetools;

import com.commercetools.api.client.ProjectApiRoot;
import com.commercetools.api.defaultconfig.ApiRootBuilder;
import com.commercetools.api.defaultconfig.ServiceRegion;
import io.vrap.rmf.base.client.oauth2.*;

public class Client {

  public static ProjectApiRoot createApiClient() {
	  final ProjectApiRoot apiRoot = ApiRootBuilder.of()
			     .defaultClient(ClientCredentials.of()
			             .withClientId("N26fqBEwjLaneF7L8kGt2nQO")
			             .withClientSecret("MyF2eYE-FBJSTz2qMGfu7RkKhzn8TsJn")
			             .build(),
			     ServiceRegion.GCP_EUROPE_WEST1)
			     .build("demo-project-sarath");

			     return apiRoot;
    }
}