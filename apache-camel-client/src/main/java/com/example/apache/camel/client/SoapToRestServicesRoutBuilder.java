package com.example.apache.camel.client;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.cxf.common.message.CxfConstants;
import org.apache.camel.model.rest.RestBindingMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.apache.camel.component.jackson.JacksonDataFormat;
import org.springframework.stereotype.Component;

import com.example.apache.camel.clienr.processor.CreateEmployeeProcessor;
import com.example.apache.camel.clienr.processor.MyProcessor;
import com.example.apache.camel.client.model.Employee;

//@Component
public class SoapToRestServicesRoutBuilder extends RouteBuilder {

//	JacksonDataFormat jsonDataFormat = new JacksonDataFormat(Employee.class);

	@Override
	public void configure() throws Exception {
		
		restConfiguration().host("localhost").port(8090).producerComponent("http");

//		from("timer:restEndpoint2")
//		.setHeader(Exchange.HTTP_METHOD, constant("POST"))
//        .setHeader(Exchange.CONTENT_TYPE, constant("application/json"))
//        .setHeader("Accept",constant("application/json"))
////		.to("rest:post:/employee")
//		.log("route-3 ${body}")
		;
		
		from("timer:post-timer?period=7s").process(new XMLBodyProcessor()).log("log:${body}")
		.setHeader(Exchange.HTTP_METHOD, simple("POST"))
		.setHeader(Exchange.CONTENT_TYPE, constant("text/xml")).log("log:${body}")
		.to("https://www.dataaccess.com/webservicesserver/NumberConversion.wso")
		.to("log:DEBUG?showBody=true&showHeaders=true")
		.process(new MyProcessor());


//		rest()
//        .post("/employee").type(Employee.class).consumes("application/json")
//        .bindingMode(RestBindingMode.json)
//        .produces("application/json")
//        .to("direct:restEndpoint2");
		
		
		from("timer:post-timer?period=300s")
		.setBody(constant(12))
		.bean(StudentBuilder.class)
		.setHeader(CxfConstants.OPERATION_NAME, constant("GetStudentDetails"))
		.setHeader(CxfConstants.OPERATION_NAMESPACE, constant("http://jitus.com/students"))
		.setHeader("Accept-Encoding", constant(""))
		.setHeader("count", constant(2))
		.to("cxf://http://localhost:8080/ws" + "?serviceClass=com.jitus.students.StudentPort"
				+ "&wsdlURL=/wsdl/students.wsdl")
		.bean(new StudentResponse())
//		.to("direct:restEndpoint2")
		;
		
		
		from("direct:restEndpoint1")
		.log("route-2 ${body}")
		.process(new CreateEmployeeProcessor())
		.log("route-2 ${body}")
		.setHeader(Exchange.HTTP_METHOD, simple("POST"))
		.setHeader(Exchange.CONTENT_TYPE, constant("application/json"))
		.log("route-2 ${body}")
//		.to("http://localhost:8090/employee")
//		.log("route-2 ${body}")
		;

//		from("timer:post-timer?period=10s").log("log:${body}").process(new CreateEmployeeProcessor()).log("log:${body}")
////		.marshal(jsonDataFormat)
//		.setHeader(Exchange.HTTP_METHOD, simple("POST"))
//		.setHeader(Exchange.CONTENT_TYPE, constant("application/json")).log("log:${body}")
//		.to("http://localhost:8080/employee").process(new MyProcessor());
		
//		.process(new MyProcessor());
	}
	
	public class XMLBodyProcessor implements Processor {

		Logger logger = LoggerFactory.getLogger(XMLBodyProcessor.class);
		@Override
		public void process(Exchange exchange) throws Exception {
			logger.info("XMLBodyProcessor {}", exchange.getIn().getBody(String.class));
			
			
			String body = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\r\n"
					+ "<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">\r\n"
					+ "  <soap:Body>\r\n"
					+ "    <NumberToWords xmlns=\"http://www.dataaccess.com/webservicesserver/\">\r\n"
					+ "      <ubiNum>1000</ubiNum>\r\n"
					+ "    </NumberToWords>\r\n"
					+ "  </soap:Body>\r\n"
					+ "</soap:Envelope>";
			
			exchange.getIn().setBody(body);
			
//			NumberToWords numberToWords = new NumberToWords();
//			numberToWords.setUbiNum(BigInteger.valueOf(100));
//			
//			exchange.getIn().setBody(numberToWords);
		}

	}
}

