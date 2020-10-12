package com.hibernate.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hibernate.model.Address;
import com.hibernate.model.Employee;
import com.hibernate.services.EmployeeService;

@RestController
@RequestMapping(value = "/employee")
public class EmployeeController {

	//private static final Logger logger = LogManager.getLogger(EmployeeController.class);

	@Autowired
	EmployeeService employeeService;

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Boolean> createEmployee(@RequestBody Employee employee) {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.set("Content-Type", "application/json");
		if (employeeService.createEmployeeRecord(employee)) {
			//logger.info("Employee Record created successfully for employee ID " + employee.getEmployeeId());
			return new ResponseEntity<Boolean>(Boolean.TRUE, HttpStatus.OK);
		} else {
			return new ResponseEntity<Boolean>(Boolean.TRUE, HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/getEmployeeByEmployeeId", method = RequestMethod.POST)
	public Employee getEmployeeByEmployeeId(@RequestBody Employee employee) {
		Employee result = employeeService.getEmployeeRecord(employee);
		return result;
	}

	@RequestMapping(value = "/getEmployeeByAddressId", method = RequestMethod.POST)
	public Employee getEmployeeByAddressId(@RequestBody Address address) {
		Employee result = employeeService.getEmployeeAddressRecord(address);
		return result;
	}

	@RequestMapping(value = "/deleteAddressByAddressId", method = RequestMethod.POST)
	public Boolean deleteAddressByAddressId(@RequestBody Employee employee) {
		Boolean result = employeeService.deleteAddressByAddressId(employee);
		return result;
	}

	@RequestMapping(value = "/getAllEmployee", method = RequestMethod.GET)
	public List<Employee> getAllEmployee() {
		List<Employee> result = employeeService.getAllEmployee();
		return result;
	}

	@RequestMapping(value = "/getEmployeeByName", method = RequestMethod.POST)
	public List<Employee> getEmployeeByName(@RequestBody Employee employee) {
		List<Employee> result = employeeService.getEmployeeByName(employee);
		return result;
	}

	@RequestMapping(value = "/updateEmployeeById", method = RequestMethod.POST)
	public Boolean updateEmployeeById(@RequestBody Employee employee) {
		Boolean result = employeeService.updateEmployeeById(employee);
		return result;
	}

}
