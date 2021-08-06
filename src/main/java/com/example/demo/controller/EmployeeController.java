package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	
	@GetMapping("/employees")
	public List<Employee> findAll(){
		return this.employeeService.findAll();
	}
	
	@GetMapping("/employees/fullname/{fullName}")
	public List<Employee> findAllByFullName(@PathVariable String fullName){
		return this.employeeService.findAllByFullName(fullName);
	}
	
	@GetMapping("/employees/age1/{age1}/age2/{age2}")
	public List<Employee> findAllByAgeBetween(@PathVariable Integer age1, @PathVariable Integer age2){
		return this.employeeService.findAllByAgeBetween(age1, age2);
	}
	
	@GetMapping("/employees/age/{age}/married/{married}")
	public List<Employee> findAllByAgeAndMarried(@PathVariable Integer age, @PathVariable Boolean married){
		return this.employeeService.findAllByAgeAndMarried(age, married);
	}
	
	
	@GetMapping("/employees/{id}")
	public ResponseEntity<Employee> findById(@PathVariable Long id) {

		Optional<Employee> employeeOpt = this.employeeService.findById(id);
		
		if (employeeOpt.isPresent()) 
			return ResponseEntity.ok(employeeOpt.get());
		
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping("/employees")
	public ResponseEntity<Employee> create(@RequestBody Employee employee){
		
		if (employee.getId() != null) {
			log.warn("Trying to create a new car with existent id");
			return ResponseEntity.badRequest().build();
		}

		Employee result = this.employeeService.save(employee);
		
		if (result != null && result.getId() != null ) 
			return ResponseEntity.ok(result); // si bien
		
		
		return ResponseEntity.internalServerError().build(); // si mal
	}
	
	// update 
	@PutMapping("/employees")
	public ResponseEntity<Employee> update(@RequestBody Employee employee) {

		if (employee.getId() == null) {
			return ResponseEntity.badRequest().build();
		}
		
		return ResponseEntity.ok(this.employeeService.update(employee));
	}
	
	@DeleteMapping("/employees/{id}")
	public ResponseEntity<Employee> delete(@PathVariable Long id){
		
//		if(this.employeeService.deleteById(id))
//			return ResponseEntity.noContent().build();
//		
//		return ResponseEntity.internalServerError().build(); // si mal
		
		return this.employeeService.deleteById(id) ? 
				ResponseEntity.noContent().build() : ResponseEntity.internalServerError().build();
		
	}
	
	
	
	
	
	
}
