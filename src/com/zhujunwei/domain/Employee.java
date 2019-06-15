package com.zhujunwei.domain;

public class Employee {
	
	private Long e_id ; //员工id
	private String e_name ;//员工名字
	private Department department ;//员工所属部门
	
	
	
	public Employee(Long e_id, String e_name, Department department) {
		super();
		this.e_id = e_id;
		this.e_name = e_name;
		this.department = department;
	}
	public Employee() {
	}

	
	public Long getE_id() {
		return e_id;
	}
	public void setE_id(Long e_id) {
		this.e_id = e_id;
	}
	public String getE_name() {
		return e_name;
	}
	public void setE_name(String e_name) {
		this.e_name = e_name;
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	@Override
	public String toString() {
		return "Employee [e_id=" + e_id + ", e_name=" + e_name + ", department=" + department + "]";
	}
	
	
}
