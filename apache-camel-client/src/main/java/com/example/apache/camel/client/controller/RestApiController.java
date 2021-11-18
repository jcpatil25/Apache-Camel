package com.example.apache.camel.client.controller;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.ExchangeBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.apache.camel.client.model.Employee;
import com.jitus.students.GetStudentDetailsResponse;
import com.jitus.students.StudentDetails;

//@RestController
public class RestApiController {

	@Autowired
	private ProducerTemplate producer;
	@Autowired
	private CamelContext camelContext;
	
	Logger logger = LoggerFactory.getLogger(RestApiController.class);
	
	@PostMapping("/student")
	public StudentDetails getStudents(@RequestBody StudentDetails studentDetails) {
		
		Exchange exchangeRequest = ExchangeBuilder.anExchange(camelContext)
				.withBody(studentDetails)
				.build();
		logger.info("Request to route");
		Exchange exchangeResponse = producer.send("direct:post-cxf", exchangeRequest);
		logger.info("Response from route : {} ", exchangeResponse.getIn().getBody());
		return ((GetStudentDetailsResponse) exchangeResponse.getIn().getBody()).getStudentDetails();
	}

	@GetMapping("/employee")
	public Employee getEmployee() {
		
		Exchange exchangeRequest = ExchangeBuilder.anExchange(camelContext)
				.withBody("Hello World!")
				.build();
		logger.info("Request to route");
		Exchange exchangeResponse = producer.send("direct:post-cxf", exchangeRequest);
		logger.info("Response from route : {} ", exchangeResponse);
		
		Employee emp = new Employee();
		emp.setName("camel-employee");
		emp.setDesignation("camel-manager");
		emp.setEmpId(222);
		emp.setSalary(20000);
		return emp;
	}
	

}
