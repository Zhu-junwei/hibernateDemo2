package com.zhujunwei.demo2;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import com.zhujunwei.domain2.Course;
import com.zhujunwei.domain2.Student;
import com.zhujunwei.utils.HibernateUtils;

/**
 * Hibernate的多对多的映射
 *
 */
public class HibernateDemo {

	@Test
	/**
	 * 保存多条记录：保存多个学生和课程
	 */
	public void demo1(){
		Session session = HibernateUtils.getCurrentSession();
		Transaction tx = session.beginTransaction();
		
		//创建2个学生
		Student student1 = new Student();
		student1.setS_name("王二");
		Student student2 = new Student();
		student2.setS_name("王小二");
		
		//创建3门课程
		Course course1 = new Course();
		course1.setC_name("Java开发");
		Course course2 = new Course();
		course2.setC_name("JSP开发");
		Course course3 = new Course();
		course3.setC_name("js开发");
		
		//设置双向的关联关系
		student1.getCourses().add(course1);
		student1.getCourses().add(course2);
		student2.getCourses().add(course2);
		student2.getCourses().add(course3);
		course1.getStudents().add(student1);
		course2.getStudents().add(student1);
		course2.getStudents().add(student2);
		course3.getStudents().add(student2);
		
		//保存
		session.save(student1);
		session.save(student2);
		session.save(course1);
		session.save(course2);
		session.save(course3);
		
		tx.commit();
	}
	
}
