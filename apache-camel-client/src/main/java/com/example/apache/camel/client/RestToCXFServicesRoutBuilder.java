package com.example.apache.camel.client;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.cxf.common.message.CxfConstants;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;

import com.jitus.students.StudentDetails;

@Component
public class RestToCXFServicesRoutBuilder extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		
		JacksonDataFormat jsonDataFormat = new JacksonDataFormat(StudentDetails.class);
		
		restConfiguration().component("servlet").host("localhost").port(9090).bindingMode(RestBindingMode.auto);
		
		rest()
		.path("/student")
		.consumes("application/json")
        .produces("application/json")
		.post()
		.type(StudentDetails.class)
		.outType(StudentDetails.class)
		.to("direct:post-cxf");
		
		from("direct:post-cxf")
		.log("cxf route-1 ${body}")
		.setBody(constant(12))
		.bean(StudentBuilder.class)
		.log("cxf route-1 ${body}")
		.setHeader(CxfConstants.OPERATION_NAME, constant("GetStudentDetails"))
		.setHeader(CxfConstants.OPERATION_NAMESPACE, constant("http://jitus.com/students"))
		.setHeader("Accept-Encoding", constant(""))
		.to("cxf://http://localhost:8080/ws" + "?serviceClass=com.jitus.students.StudentPort"
				+ "&wsdlURL=/wsdl/students.wsdl")
		.bean(new StudentResponse())
		.log("cxf route-1 ${body}")
		.marshal(jsonDataFormat);
		
		}

}

