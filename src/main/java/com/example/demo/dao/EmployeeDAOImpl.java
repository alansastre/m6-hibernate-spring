package com.example.demo.dao;

import java.util.List;
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
			if (employee != null) 
				return Optional.of(employee);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return Optional.empty();
	}


	@Override
	public Employee save(Employee employee) {
		
		try {
			session.beginTransaction();
			session.save(employee);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		
		return employee;
	}


	@Override
	public Employee update(Employee employee) {
		
		try {
			session.beginTransaction();
			session.merge(employee);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		
		return employee;
	}


	@Override
	public Boolean deleteById(Long id) {
		
		Optional<Employee> empOpt = this.findById(id);
		if (empOpt.isEmpty()) 
			return false;
		
		
		try {
			session.beginTransaction();
			session.delete(empOpt.get());
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		
		return false;
	}


	@Override
	public List<Employee> findAllFromSession() {

		String hql = "from Employee";
		
		return  session.createQuery(hql, Employee.class).list();
	}


	@Override
	public List<Employee> findAllFromEntityManager() {
	
		String hql = "from Employee";
		return entityManager.createQuery(hql, Employee.class).getResultList();
	}

	
	
	
	
	
	
}
