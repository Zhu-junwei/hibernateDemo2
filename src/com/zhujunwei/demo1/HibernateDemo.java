package com.zhujunwei.demo1;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import com.zhujunwei.domain.Customer;
import com.zhujunwei.domain.Department;
import com.zhujunwei.domain.Employee;
import com.zhujunwei.domain.LinkMan;
import com.zhujunwei.utils.HibernateUtils;

/**
 * 一对多的测试类
 * @author jt
 *
 */
public class HibernateDemo {

	@Test
	public void demo1(){

		Session session = HibernateUtils.getCurrentSession();
		Transaction tx = session.beginTransaction();
		
		//创建部门
		Department department = new Department();
		department.setD_name("技术部");
		
		//创建客户
		Employee employee1 = new Employee();
		employee1.setE_name("朱俊伟");
		Employee employee2 = new Employee();
		employee2.setE_name("小猪");
		
		//设置关系
		employee1.setDepartment(department);
		employee2.setDepartment(department);
		department.getEmployees().add(employee1);
		department.getEmployees().add(employee2);
		
		// 保存数据:
//		session.save(department);
		session.save(employee1);
		session.save(employee2);
		
		tx.commit();
	}
	
	@Test
	public void demo2(){
		Session session = HibernateUtils.getCurrentSession();
		Transaction tx = session.beginTransaction();
		
		//删除部门的时候删除员工
//		Department department = session.get(Department.class, 1L);
//		session.delete(department);
		
		//删除员工的时候删除部门（不合理，一般不用）
		Employee employee = session.get(Employee.class, 1L);
		session.delete(employee);
		
		tx.commit();
	}
	
	@Test
	/**
	 *  级联保存或更新操作：
	 *  * 保存客户级联联系人，操作的主体是客户对象，需要在Customer.hbm.xml中进行配置
	 *  * <set name="linkMans" cascade="save-update">
	 */
	public void demo3(){
		Session session = HibernateUtils.getCurrentSession();
		Transaction tx = session.beginTransaction();
		
		Customer customer = new Customer();
		customer.setCust_name("赵洪");

		LinkMan linkMan = new LinkMan();
		linkMan.setLkm_name("如花");
		
		customer.getLinkMans().add(linkMan);
		linkMan.setCustomer(customer);
		
		session.save(customer);
		
		tx.commit();
	}
	
	@Test
	/**
	 *  级联保存或更新操作：
	 *  * 保存联系人级联客户，操作的主体是联系人对象，需要在LinkMan.hbm.xml中进行配置
	 *  * <many-to-one name="customer" cascade="save-update" class="com.itheima.hibernate.domain.Customer" column="lkm_cust_id"/>
	 */
	public void demo4(){
		Session session = HibernateUtils.getCurrentSession();
		Transaction tx = session.beginTransaction();
		
		Customer customer = new Customer();
		customer.setCust_name("李兵");

		LinkMan linkMan = new LinkMan();
		linkMan.setLkm_name("凤姐");
		
		customer.getLinkMans().add(linkMan);
		linkMan.setCustomer(customer);
		
		session.save(linkMan);
		
		tx.commit();
	}
	
	@Test
	/**
	 * 测试对象的导航
	 * * 前提：一对多的双方都设置cascade="save-update"
	 */
	public void demo5(){
		Session session = HibernateUtils.getCurrentSession();
		Transaction tx = session.beginTransaction();
		
		Customer customer = new Customer();
		customer.setCust_name("李兵");

		LinkMan linkMan1 = new LinkMan();
		linkMan1.setLkm_name("凤姐");
		LinkMan linkMan2 = new LinkMan();
		linkMan2.setLkm_name("如花");
		LinkMan linkMan3 = new LinkMan();
		linkMan3.setLkm_name("芙蓉");
		
		linkMan1.setCustomer(customer);
		customer.getLinkMans().add(linkMan2);
		customer.getLinkMans().add(linkMan3);
		
		// 双方都设置了cascade
		session.save(linkMan1); // 发送几条insert语句  4条
//		session.save(customer); // 发送几条insert语句  3条
//		session.save(linkMan2); // 发送几条insert语句  1条
		
		tx.commit();
	}
	
	@Test
	/**
	 * 级联删除：
	 * * 删除客户级联删除联系人，删除的主体是客户，需要在Customer.hbm.xml中配置
	 * * <set name="linkMans" cascade="delete">
	 */
	public void demo6(){
		Session session = HibernateUtils.getCurrentSession();
		Transaction tx = session.beginTransaction();
		
		// 没有设置级联删除，默认情况:修改了联系人的外键，删除客户
		/*Customer customer = session.get(Customer.class, 1l);
		session.delete(customer);*/
		
		// 删除客户，同时删除联系人
		Customer customer = session.get(Customer.class, 1l);
		session.delete(customer);
		
		tx.commit();
	}
	
	@Test
	/**
	 * 级联删除：
	 * * 删除联系人级联删除客户，删除的主体是联系人，需要在LinkMan.hbm.xml中配置
	 * * <many-to-one name="customer" cascade="delete">
	 */
	public void demo7(){
		Session session = HibernateUtils.getCurrentSession();
		Transaction tx = session.beginTransaction();
		
		// 删除客户，同时删除联系人
		LinkMan linkMan = session.get(LinkMan.class, 3l);
		session.delete(linkMan);
		
		tx.commit();
	}
	
	@Test
	/**
	 * 将2号联系人原来归1号客户，现在改为2号客户
	 */
	public void demo8(){
		Session session = HibernateUtils.getCurrentSession();
		Transaction tx = session.beginTransaction();
		
		// 查询2号联系人
		LinkMan linkMan = session.get(LinkMan.class, 2l);
		// 查询2号客户
		Customer customer = session.get(Customer.class, 2l);
		// 双向的关联
		linkMan.setCustomer(customer);
		customer.getLinkMans().add(linkMan);
		
		tx.commit();
	}
	
	@Test
	/**
	 * 区分cascade和inverse的区别
	 */
	public void demo9(){
		Session session = HibernateUtils.getCurrentSession();
		Transaction tx = session.beginTransaction();
		
		Customer customer = new Customer();
		customer.setCust_name("李兵");
		
		LinkMan linkMan = new LinkMan();
		linkMan.setLkm_name("凤姐");
		
		customer.getLinkMans().add(linkMan);
		
		// 条件在Customer.hbm.xml上的set中配置了cascade="save-update" inverse="true"
		session.save(customer); // 客户会插入到数据库，联系人也会插入到数据库，但是外键为null
		
		tx.commit();
	}
}
