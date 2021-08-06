package com.example.demo.dao;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Employee;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO{


	@PersistenceContext
	private EntityManager entityManager; // JPA
	
	@Autowired
	private Session session; // Hibernate
	
	
	@Override
	public Optional<Employee> findById(Long id) {
		
		try {
			
			Employee employee = session.find(Employee.class, id);
			return Optional.of(employee);
			
		} catch (NoResultException e) {
			e.printStackTrace();
		}
		
		return Optional.empty();
	}


	@Override
	public Employee save(Employee employee) {
		
		try {
			//session.beginTransaction();
			session.save(employee);
			//session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			//session.getTransaction().rollback();
		}
		
		
		return employee;
	}

	
	
	
	
	
	
}
