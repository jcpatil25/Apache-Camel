package com.example.apache.camel.client;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.cxf.common.message.CxfConstants;
import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.binding.soap.interceptor.AbstractSoapInterceptor;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.phase.Phase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jitus.students.GetStudentDetailsResponse;
import com.jitus.students.StudentDetails;

//@Component
public class InvokeCXFSoapServicesRoutBuilder extends RouteBuilder {

	@Override
	public void configure() throws Exception {

		from("file:files/input")
		.log("file-route-4 ${body}")
		.convertBodyTo(String.class)
		.split(body())
		.log("file route-4 ${body}")
//		.bean(new StudentResponse())
		.log("file route-4 ${body}");
		
	

		from("timer:post-timer?period=500s")
		.setBody(constant(12))
		.bean(StudentBuilder.class)
		.setHeader(CxfConstants.OPERATION_NAME, constant("GetStudentDetails"))
		.setHeader(CxfConstants.OPERATION_NAMESPACE, constant("http://jitus.com/students"))
		.setHeader("Accept-Encoding", constant(""))
		.to("cxf://http://localhost:8080/ws" + "?serviceClass=com.jitus.students.StudentPort"
				+ "&wsdlURL=/wsdl/students.wsdl")
		.bean(new StudentResponse())
		.log("cxf route-1 ${body}")
		.multicast()
		.to("direct:route-2", "direct:route-3");
		
		from("direct:route-2")
		.log("route-2 ${body}")
		.bean(new StudentResponse())
		.log("route-2 ${body}");
		
		from("direct:route-3")
		.log("route-3 ${body}")
		.bean(new StudentResponse())
		.log("route-3 ${body}");
		
		
		}

}

@Component
class StudentResponse {
	Logger logger = LoggerFactory.getLogger(StudentResponse.class);

	public StudentDetails getStudentDetailsResponse(GetStudentDetailsResponse detailsResponse) {
		logger.info("********* StudentResponse ************* : {}", detailsResponse.getStudentDetails());
		return detailsResponse.getStudentDetails();

	}
}
