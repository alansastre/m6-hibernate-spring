package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.domain.Employee;

public interface EmployeeService {

	Optional<Employee> findById(Long id);
	
	List<Employee> findAll();
	
	Employee save(Employee employee);
	
	Employee update(Employee employee);
	
	Boolean deleteById(Long id);
	
}
