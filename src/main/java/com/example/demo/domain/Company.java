package com.example.demo.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="companies")
public class Company implements Serializable{

	private static final long serialVersionUID = 1L;
	
	// atributos propios
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	private String cif;
	
	@Column(name = "start_date")
	private LocalDate startDate;
	
	private Double capital;
	
	// asociaciones
	
	// @JsonIgnoreProperties(value = {"company", "biography"}) // para ignorar determinados atributos de los Employees
	@JsonIgnore // ignora este atributo entero a la hora de transformar a json
	@OneToMany(mappedBy = "company") // no es owner, el mapeo se establece en la otra clase
	// @OneToMany(mappedBy = "company", fetch = FetchType.EAGER) // Por defecto es inicialización Lazy
	private List<Employee> employees = new ArrayList<>(); // Por defecto es inicialización Lazy

	
	public Company() {}

	public Company(Long id, String name, String cif, LocalDate startDate, Double capital) {
		this.id = id;
		this.name = name;
		this.cif = cif;
		this.startDate = startDate;
		this.capital = capital;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getCif() {
		return cif;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public Double getCapital() {
		return capital;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setCif(String cif) {
		this.cif = cif;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public void setCapital(Double capital) {
		this.capital = capital;
	}
	
	

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}


	@Override
	public String toString() {
		return "Company [id=" + id + ", name=" + name + ", cif=" + cif + ", startDate=" + startDate + ", capital="
				+ capital + "]";
	}
	
	
	
	
	
}
