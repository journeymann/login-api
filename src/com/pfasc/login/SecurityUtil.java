package com.pfasc.login;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.client.ClientResponse;
import org.apache.http.client.ClientProtocolException;

public class SecurityUtil {

	public SecurityUtil() {
		super();
	}

	public Login getLogin(String username, String app_name) throws ClientProtocolException{	

		ClientConfig config = new DefaultClientConfig();
 	    Client client = Client.create(config);
		
		String appquery = "http://dev-web01:8080/webservices/resources/apps/2,"+app_name.replace(" ", "%20");
		System.out.println("APP_URL="+appquery);

 	    WebResource resource = client.resource(appquery);
		App app = (App)resource.get(App.class);
		
		String requestquery = "http://dev-web01:8080/webservices/resources/users/"+username+","+app.getId();
		System.out.println("QUERY_URL="+requestquery);
 	    resource = client.resource(requestquery);
		return (Login)resource.get(Login.class);
	}
	
}