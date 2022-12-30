package com.commercetools.rest.api.commercetools.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.commercetools.api.client.ProjectApiRoot;
import com.commercetools.api.models.product.Product;
import com.commercetools.api.models.product.ProductDraft;
import com.commercetools.api.models.product.ProductUpdate;
import com.commercetools.api.models.product_type.ProductType;
import com.commercetools.api.models.product_type.ProductTypeDraft;
import com.commercetools.rest.api.commercetools.CommerceToolClient;

@RestController
public class ProductController {
	
	@Autowired
	private CommerceToolClient commerceToolClient;
	
	
	@PostMapping("/createProductType")
	public String createProductType(@RequestParam String productType, @RequestParam String description)
	{
		ProjectApiRoot apiRoot = commerceToolClient.createApiClient();
		// Create ProductType
		ProductTypeDraft newProductTypeDetails = ProductTypeDraft
		  .builder()
		  .name(productType)
		  .description(description)
		  .build();

		// Post the ProductTypeDraft and get the new ProductType
		ProductType productTypeCreated = apiRoot
		  .productTypes()
		  .post(newProductTypeDetails)
		  .executeBlocking()
		  .getBody();

		// Output the ProductType ID
		String productTypeID = productTypeCreated.getId();
		return productTypeID;
	}
	@PostMapping("/createProduct")
	public String createProduct(@RequestParam String productName, @RequestParam String slug, @RequestParam String productTypeId)
	{
		ProjectApiRoot apiRoot = commerceToolClient.createApiClient();
		// Create Product
		ProductDraft newProductDetails = ProductDraft
		  .builder()
		  .name(stringBuilder ->
		    stringBuilder
		      .addValue("en", productName)
		  )
		  .productType(typeBuilder -> typeBuilder.id(productTypeId))
		  .slug(stringBuilder ->
		    stringBuilder
		      .addValue("en", slug)		  )
		  .build();

		// Post the ProductDraft and get the new Product
		Product product = apiRoot
		  .products()
		  .post(newProductDetails)
		  .executeBlocking()
		  .getBody();

		// Output the Product ID
		String productID = product.getId();
		return productID;
	}
	@GetMapping("/getProduct")
	public String getProduct(@PathVariable String productId)
	{
		ProjectApiRoot apiRoot = commerceToolClient.createApiClient();
		// Create Product
		// Query the new Product
		Product queryProduct = apiRoot
		  .products()
		  .withId(productId)
		  .get()
		  .executeBlocking()
		  .getBody();
		
		return ""+queryProduct.getVersion()+"";
	}
	@PostMapping("/addProductKey")
	public String addProductKey(@RequestParam String productId, @RequestParam String productKey)
	{
		ProjectApiRoot apiRoot = commerceToolClient.createApiClient();
		// Query the Product to update
		Product productToUpdate = apiRoot
		  .products()
		  .withId(productId)
		  .get()
		  .executeBlocking()
		  .getBody();

		// Create the ProductUpdate with the current version of the Product and the update actions
		ProductUpdate productUpdate = ProductUpdate
		  .builder()
		  .version(productToUpdate.getVersion())
		  .plusActions(actionBuilder ->
		    actionBuilder.setKeyBuilder().key(productKey)
		  )
		  .build();

		// Post the ProductUpdate and return the updated Product
		Product updatedProduct = apiRoot
		  .products()
		  .withId(productToUpdate.getId())
		  .post(productUpdate)
		  .executeBlocking()
		  .getBody();

		// Output the updated Product's key
		String updatedProductKey = updatedProduct.getKey();
		return updatedProductKey;
	}
	@GetMapping("/getProductByKey")
	public String getProductByKey(@PathVariable String productKey)
	{
		ProjectApiRoot apiRoot = commerceToolClient.createApiClient();
		Product findProductByKey = apiRoot
				  .products()
				  .withKey(productKey)
				  .get()
				  .executeBlocking()
				  .getBody();

				String productID = findProductByKey.getId();
		return productID;
	}
}
