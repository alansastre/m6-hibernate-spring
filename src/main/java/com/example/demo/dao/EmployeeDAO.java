package com.example.demo.dao;

import java.util.List;
import java.util.Optional;

import com.example.demo.domain.Employee;

public interface EmployeeDAO {

	// filtros
	Optional<Employee> findById(Long id);
	
	List<Employee> findAllFromSession();
	List<Employee> findAllFromEntityManager();

	
	Employee save(Employee employee);
	
	Employee update(Employee employee);
	
	Boolean deleteById(Long id);
}
