package com.tuanla.springserver.util;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import com.sun.jersey.api.json.JSONConfiguration;
import org.json.simple.JSONObject;

import java.io.IOException;

public class ClientFactory {
	public static String START_PROCESS_URL = "runtime/process-instances";
	static String username = "";
	static String password = "";
	static String base_url = "";
	static {
		GetPropertyValues prop = new GetPropertyValues();
		try {
			base_url = prop.getPropValues("host");
			username = prop.getPropValues("username");
			password = prop.getPropValues("password");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static <T> T postData(Object postObject, String url, Class<T> c) throws Exception {
		ClientConfig clientConfig = new DefaultClientConfig();
		clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
		Client client = Client.create(clientConfig);
		client.addFilter(new HTTPBasicAuthFilter(username, password));
		WebResource webResource = client.resource(base_url + url);
		ClientResponse response = webResource.type("application/json").post(ClientResponse.class, postObject);
		if (response.getStatus() != 200 && response.getStatus() != 201) {
			String strResponse = response.getEntity(String.class);
			throw new RuntimeException(
					"Failed : HTTP error code : " + response.getStatus() + ". Raw response:" + strResponse);
		}
		return response.getEntity(c);
	}

	public static <T> T getData(String url, Class<T> c) throws Exception {
		ClientConfig clientConfig = new DefaultClientConfig();
		clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
		Client client = Client.create(clientConfig);
		client.addFilter(new HTTPBasicAuthFilter(username, password));
		WebResource webResource = client.resource(base_url + url);

		ClientResponse response = webResource.type("application/json").get(ClientResponse.class);
		if (response.getStatus() != 201 && response.getStatus() != 200) {
			String strResponse = response.getEntity(String.class);
			throw new RuntimeException(
					"Failed : HTTP error code : " + response.getStatus() + ". Raw response:" + strResponse);
		}
		return response.getEntity(c);
	}

	public static JSONObject getProcessefinitions() throws Exception {
		String URL = "repository/process-definitions";
		return getData(URL, JSONObject.class);
	}
}
