package com.example.demo.controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Employee;
import com.example.demo.service.EmployeeService;


@RestController
@RequestMapping("/api")
public class EmployeeController {

	private final Logger log = LoggerFactory.getLogger(EmployeeController.class);
	
	private EmployeeService employeeService;
	
	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	
	
	@GetMapping("/employees/{id}")
	public ResponseEntity<Employee> findById(@PathVariable Long id) {

		Optional<Employee> employeeOpt = this.employeeService.findById(id);
		
		if (employeeOpt.isPresent()) 
			return ResponseEntity.ok(employeeOpt.get());
		
		return ResponseEntity.notFound().build();
	}
	
	
	
	
	
	
	
	
	
	
}
