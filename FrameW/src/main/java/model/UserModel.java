package model;

import java.util.List; 
import java.util.Iterator; 
 
import org.hibernate.HibernateException; 
import org.hibernate.Session; 
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

//import TestMaven.TestMavenPro.User;


public class UserModel {

	private static SessionFactory factory;
	
	public UserModel()
	{
		
	}
	
	public void cFactory()
	{
		try {
	         factory = new Configuration().configure().buildSessionFactory();
	      } catch (Throwable ex) { 
	         System.err.println("Failed to create sessionFactory object." + ex);
	         throw new ExceptionInInitializerError(ex);
	      }
	}
	
	
	 public Integer addEmployee(String username, String email, String password){
		 

			cFactory();
		 
		 
	      Session session = factory.openSession();
	      Transaction tx = null;
	      Integer employeeID = null;
	      
	      try {
	         tx = session.beginTransaction();
	         User employee = new User(username, email, password);
	         employeeID = (Integer) session.save(employee); 
	         tx.commit();
	         
	         System.out.println("entry added to database");
	         
	      } catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      } finally {
	         session.close(); 
	      }
	      return employeeID;
	   }
	   
	 
	 
	 public void listEmployees( ){
		 
		 cFactory();
	      Session session = factory.openSession();
	      Transaction tx = null;
	      
	      try {
	         tx = session.beginTransaction();
	         List employees = session.createQuery("FROM User").list(); 
	         for (Iterator iterator = employees.iterator(); iterator.hasNext();){
	            User employee = (User) iterator.next(); 
	            System.out.print("First Name: " + employee.getUsername()); 
	            System.out.print("  Last Name: " + employee.getEmail()); 
	            System.out.println("  Salary: " + employee.getPassword()); 
	         }
	         tx.commit();
	      } catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      } finally {
	         session.close(); 
	      }
	   }
	 
	   /* Method to  READ all the employees */
	   public boolean checkEmployees(String username,String password ){
		   
		   cFactory();
		   
	      Session session = factory.openSession();
	      Transaction tx = null;
	      
	      try {
	         tx = session.beginTransaction();
	         List employees = session.createQuery("FROM User").list(); 
	         for (Iterator iterator = employees.iterator(); iterator.hasNext();){
	            User employee = (User) iterator.next(); 
	            System.out.print("username: " + employee.getUsername()); 
	            System.out.print("email: " + employee.getEmail()); 
	            System.out.println("password: " + employee.getPassword()); 
	            
	            if(employee.getUsername().equals(username)&&employee.getPassword().equals(password))
	            {
	            	System.out.println("employee exist");
	            	return true;
	            }
	         }
	         tx.commit();
	         
	         
	      } catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      } finally {
	         session.close(); 
	      }
	      
	      return false;
	      
	   }
	   
	   /* Method to UPDATE salary for an employee */
	   void updateEmployee(Integer EmployeeID, String password ){
	      Session session = factory.openSession();
	      Transaction tx = null;
	      
	      try {
	         tx = session.beginTransaction();
	         User employee = (User)session.get(User.class, EmployeeID); 
	         employee.setPassword(password);
			 session.update(employee); 
	         tx.commit();
	      } catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      } finally {
	         session.close(); 
	      }
	   }
	   
	   /* Method to DELETE an employee from the records */
	   public void deleteEmployee(Integer EmployeeID){
	      Session session = factory.openSession();
	      Transaction tx = null;
	      
	      try {
	         tx = session.beginTransaction();
	         User employee = (User)session.get(User.class, EmployeeID); 
	         session.delete(employee); 
	         tx.commit();
	      } catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      } finally {
	         session.close(); 
	      }
	   }

}
