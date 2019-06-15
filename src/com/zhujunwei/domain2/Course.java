package com.zhujunwei.domain2;

import java.util.HashSet;
import java.util.Set;

public class Course {
	
	private Long c_id ; //课程id
	private String c_name ;//课程名字
	private Set<Student> students = new HashSet<>();//所选课程的学生

	public Course() {
		super();
	}

	public Course(Long c_id, String c_name, Set<Student> students) {
		super();
		this.c_id = c_id;
		this.c_name = c_name;
		this.students = students;
	}
	
	public Long getC_id() {
		return c_id;
	}
	public void setC_id(Long c_id) {
		this.c_id = c_id;
	}
	public String getC_name() {
		return c_name;
	}
	public void setC_name(String c_name) {
		this.c_name = c_name;
	}
	public Set<Student> getStudents() {
		return students;
	}
	public void setStudents(Set<Student> students) {
		this.students = students;
	}



	@Override
	public String toString() {
		return "Course [c_id=" + c_id + ", c_name=" + c_name + ", students=" + students + "]";
	}
	
	
}
