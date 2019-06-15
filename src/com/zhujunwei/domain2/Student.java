package com.zhujunwei.domain2;

import java.util.HashSet;
import java.util.Set;

public class Student {
	
	private Long s_id; //学生id
	private String s_name ;//学生姓名
	private Set<Course> courses= new HashSet<>() ; //学生所选课程
	
	
	@Override
	public String toString() {
		return "Student [s_id=" + s_id + ", s_name=" + s_name + ", courses=" + courses + "]";
	}

	public Student() {
		super();
	}

	public Student(Long s_id, String s_name, Set<Course> courses) {
		super();
		this.s_id = s_id;
		this.s_name = s_name;
		this.courses = courses;
	}
	
	public Long getS_id() {
		return s_id;
	}
	public void setS_id(Long s_id) {
		this.s_id = s_id;
	}
	public String getS_name() {
		return s_name;
	}
	public void setS_name(String s_name) {
		this.s_name = s_name;
	}
	public Set<Course> getCourses() {
		return courses;
	}
	public void setCourses(Set<Course> courses) {
		this.courses = courses;
	}
	
	
}
