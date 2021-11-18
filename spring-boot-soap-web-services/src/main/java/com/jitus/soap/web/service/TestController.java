package com.jitus.soap.web.service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/ws")
public class TestController {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@RequestMapping(value = "/employee", method = RequestMethod.GET)
	public Employee firstService(@RequestParam int id) {

		logger.info("Inside employee GET API");
		Employee emp = new Employee();
		emp.setName("emp1");
		emp.setDesignation("manager");
		emp.setEmpId(id);
		emp.setSalary(3000);

		return emp;
	}

	@RequestMapping(value = "/employee", method = RequestMethod.POST)
	public Employee secondService(@RequestBody Employee employee) {

		logger.info("Inside employee POST API");
		Employee emp = new Employee();
		emp.setName(employee.getName());
		emp.setDesignation(employee.getDesignation());
		emp.setEmpId(employee.getEmpId());
		emp.setSalary(employee.getSalary());

		return emp;
	}

}
