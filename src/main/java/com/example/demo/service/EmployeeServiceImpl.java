package com.example.demo.service;

import java.util.List;
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


	@Override
	public Employee save(Employee employee) {
		if(employee == null) 
			return employee;
		
		return this.employeeDAO.save(employee);
		// return this.employeeRepository.save(employee);
	}


	@Override
	public Employee update(Employee employee) {
		return this.employeeDAO.update(employee);
	}


	@Override
	public Boolean deleteById(Long id) {
		if(id == null || id == 0) 
			return false;
		
		return this.employeeDAO.deleteById(id);
	}


	@Override
	public List<Employee> findAll() {
		//return this.employeeDAO.findAllFromSession();
		 return this.employeeDAO.findAllFromEntityManager();
	}


	@Override
	public List<Employee> findAllByFullName(String fullName) {
		return this.employeeDAO.findAllByFullName(fullName);
	}


	@Override
	public List<Employee> findAllByAgeAndMarried(Integer age, Boolean married) {
		return this.employeeDAO.findAllByAgeAndMarried(age, married);
	}


	@Override
	public List<Employee> findAllByAgeBetween(Integer age1, Integer age2) {
		return this.employeeDAO.findAllByAgeBetween(age1, age2);
	}
	
	

}
