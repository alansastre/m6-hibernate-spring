package com.example.demo.dao;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

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


	/**
	 * CRUD
	 * 
	 * Retrieve: búsquedas
	 * 
	 * 1. find
	 * 2. HQL
	 * 3. API Criteria: hacer SQL desde Java POO
	 */
	@Override
	public List<Employee> findAllByFullName(String fullName) {

		// 1 - Criteria Query

		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Employee> criteria = builder.createQuery(Employee.class);
		
		// from
		Root<Employee> root = criteria.from(Employee.class);
		
		criteria.select(root);
		
		// filtros: contiene un texto
		criteria.where(builder.like(root.get("fullName"), "%" + fullName + "%"));
		
		// 2 - Query normal que recibe los criterios: obtener resultados
		List<Employee> employees = session.createQuery(criteria).list();
		
		return employees;
	}


	@Override
	public List<Employee> findAllByAgeBetween(Integer age1, Integer age2) {

		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Employee> criteria = builder.createQuery(Employee.class);
		
		// from
		Root<Employee> root = criteria.from(Employee.class);
		
		criteria.select(root);
		
		// filtros: contiene un texto
		if (age2 < age1) 
			criteria.where(builder.between(root.get("age"), age2, age1));
		else
			criteria.where(builder.between(root.get("age"), age1, age2));
		
		// 2 - Query normal que recibe los criterios: obtener resultados
		List<Employee> employees = session.createQuery(criteria).list();
		
		return employees;
	}


	@Override
	public List<Employee> findAllByAgeAndMarried(Integer age, Boolean married) {
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Employee> criteria = builder.createQuery(Employee.class);
		
		// from
		Root<Employee> root = criteria.from(Employee.class);
		
		criteria.select(root);
		
		// filtros
		Predicate pred1 = builder.gt(root.get("age"), 18);
		
		Predicate pred2 = null;
		if (married) 
			pred2 = builder.isTrue(root.get("married"));
		else
			pred2 = builder.isFalse(root.get("married"));
		
		criteria.where(builder.and(pred1, pred2));
		
		// 2 - Query normal que recibe los criterios: obtener resultados
		List<Employee> employees = session.createQuery(criteria).list();
		
		return employees;
	}

	
	
	
	
	
	
	
	
	
	
	
	
}
