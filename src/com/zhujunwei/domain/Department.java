package com.zhujunwei.domain;

import java.util.HashSet;
import java.util.Set;

public class Department {
	
	private Long d_id ; //部门id
	private String d_name ;//部门名字
	private Set<Employee> employees = new HashSet<>();//部门里的员工
	
	
	
	public Department(Long d_id, String d_name, Set<Employee> employees) {
		super();
		this.d_id = d_id;
		this.d_name = d_name;
		this.employees = employees;
	}
	
	public Department() {
	}

	
	public void setD_id(Long d_id) {
		this.d_id = d_id;
	}

	public String getD_name() {
		return d_name;
	}
	public void setD_name(String d_name) {
		this.d_name = d_name;
	}
	public Set<Employee> getEmployees() {
		return employees;
	}
	public void setEmployees(Set<Employee> employees) {
		this.employees = employees;
	}
	public Long getD_id() {
		return d_id;
	}

	@Override
	public String toString() {
		return "Department [d_id=" + d_id + ", d_name=" + d_name + ", employees=" + employees + "]";
	}
	
}
