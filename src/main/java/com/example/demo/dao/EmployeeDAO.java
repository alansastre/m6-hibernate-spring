package com.example.demo.dao;

import java.util.Optional;

import com.example.demo.domain.Employee;

public interface EmployeeDAO {

	// filtros
	Optional<Employee> findById(Long id);
}
