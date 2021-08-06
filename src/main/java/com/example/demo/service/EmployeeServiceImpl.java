package com.example.demo.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.dao.EmployeeDAO;
import com.example.demo.domain.Employee;
import com.example.demo.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private final EmployeeRepository employeeRepository;
	private final EmployeeDAO employeeDAO;
	

	public EmployeeServiceImpl(EmployeeRepository employeeRepository, EmployeeDAO employeeDAO) {
		this.employeeRepository = employeeRepository;
		this.employeeDAO = employeeDAO;
	}


	@Override
	public Optional<Employee> findById(Long id) {
		if(id == null || id == 0) 
			return Optional.empty();
		
		return this.employeeDAO.findById(id);
	}

}
