package com.jitus.soap.web.service;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.jitus.students.GetStudentDetailsRequest;
import com.jitus.students.GetStudentDetailsResponse;
import com.jitus.students.StudentDetails;

@Endpoint
public class StudentDetailsEndpoint {

	@PayloadRoot(namespace = "http://jitus.com/students", localPart = "GetStudentDetailsRequest")
	@ResponsePayload
	public GetStudentDetailsResponse processCourseDetailsRequest(@RequestPayload GetStudentDetailsRequest request) {
		GetStudentDetailsResponse response = new GetStudentDetailsResponse();
		
		StudentDetails studentDetails = new StudentDetails();
		studentDetails.setId(request.getId());
		studentDetails.setName("Jitendra");
		studentDetails.setPassportNumber("J1234567");
		
		response.setStudentDetails(studentDetails);
		
		return response;
	}

}