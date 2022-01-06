package com.example.apache.camel.client;

import java.util.HashMap;
import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.transports.http.configuration.HTTPClientPolicy;

public class WebServiceTimeout {
	public void setWebServiceTimeout(Exchange exchange) {
	      Map<String, Object> requestContext = new HashMap<String, Object>();
	      HTTPClientPolicy clientPolicy = new HTTPClientPolicy();
	      clientPolicy.setReceiveTimeout(10000);
	      clientPolicy.setConnectionTimeout(10000);
	      requestContext.put(HTTPClientPolicy.class.getName(), clientPolicy);
	      exchange.getIn().setHeader(Client.REQUEST_CONTEXT , requestContext);
	}
}
