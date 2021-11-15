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

@Component
public class InvokeCXFSoapServicesRoutBuilder extends RouteBuilder {

	@Override
	public void configure() throws Exception {

		from("timer:post-timer?period=10s").setBody(constant(12)).bean(StudentBuilder.class)
				.setHeader(CxfConstants.OPERATION_NAME, constant("GetStudentDetails"))
				.setHeader(CxfConstants.OPERATION_NAMESPACE, constant("http://jitus.com/students"))
				.setHeader("Accept-Encoding", constant(""))
				.to("cxf://http://localhost:8080/ws" + "?serviceClass=com.jitus.students.StudentPort"
						+ "&wsdlURL=/wsdl/students.wsdl")
				.bean(new StudentResponse());
	}

}

@Component
class StudentResponse {
	Logger logger = LoggerFactory.getLogger(StudentResponse.class);

	public void getStudentDetailsResponse(GetStudentDetailsResponse detailsResponse) {
		logger.info("********* StudentResponse ************* : {}", detailsResponse.getStudentDetails());

	}
}
