package com.ideas2it.employeeManagement.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ideas2it.sessionFactory.DataBaseConnection;
import com.ideas2it.employeeManagement.dao.Dao;
import com.ideas2it.employeeManagement.model.Employee;
import com.ideas2it.employeeManagement.model.Address;

/**
 * DaoImplementation get Details from user,
 * for doing CRUD operation.
 *
 * @version  1.0 29-03-2021.
 * @author   Nandhakumar.
 */
public class EmployeeDaoImpl implements Dao {
    private DataBaseConnection dataBaseConnection
            = DataBaseConnection.getInstance();

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean insertEmployeeDetails(Employee employee) {
        boolean isEmployeeAdded = true;
        Session session = null;
        try {
		    SessionFactory sessionFactory = dataBaseConnection.getSessionFactory();
            session = sessionFactory.openSession();
		    session.beginTransaction();
            session.save(employee);
		    session.getTransaction().commit();
        } catch(HibernateException e) {
            session.getTransaction().rollback();
            isEmployeeAdded = false;
        } finally {
            if(session != null) {
                session.close();
            }
        }
        return isEmployeeAdded;		
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean updateEmployee(Employee employee) {
        boolean isUpdated = true;
        Session session = null;
        try {
		    SessionFactory sessionFactory = dataBaseConnection.getSessionFactory();
            session = sessionFactory.openSession();
		    session.beginTransaction();
            session.update(employee);
		    session.getTransaction().commit();
        } catch(HibernateException e) {
            session.getTransaction().rollback();
            isUpdated = false;
        } finally {
            if(session != null) {
                session.close();
            }
        }
        return isUpdated;		
    }		
	
    /**
     * {@inheritDoc}
     */
   @Override
    public Employee getEmployeeDetails(int employeeId) {
        Session session = null;
        Employee employee = null;
        List<Address> addresses = null;
		try {
            SessionFactory sessionFactory = dataBaseConnection.getSessionFactory();
            session = sessionFactory.openSession();
            employee = (Employee) session.get(Employee.class, employeeId);
        } catch(HibernateException e) { 
            employee = null;
        } finally {
            if(session != null) {
                session.close();
            }
        }			
        return employee;
    }
	
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean addNewAddress(Employee employee) {
        boolean isAddressAdded = true;
        Session session = null;
        try {
		    SessionFactory sessionFactory = dataBaseConnection.getSessionFactory();
            session = sessionFactory.openSession();
		    session.beginTransaction();
            session.saveOrUpdate(employee);
		    session.getTransaction().commit();
        } catch(HibernateException e) {
            session.getTransaction().rollback();
            isAddressAdded = false;
        } finally {
            if(session != null) {
                session.close();
            }
        }
        return isAddressAdded;
    }
	 
    /**
     * {@inheritDoc}
     */ 
    @Override
    public List getAllEmployeeDetails() {
        Session session = null;
        List<Employee> employees = null;
		try {
            SessionFactory sessionFactory = dataBaseConnection.getSessionFactory();
            session = sessionFactory.openSession();
            Criteria criteria = session.createCriteria(Employee.class);
		    criteria.add(Restrictions.eq("isDeleted", false));
            employees = criteria.list();
        } catch(HibernateException e) { 
            employees = null;
        } finally {
            if(session != null) {
                session.close();
            }
        }			
        return employees;
    }
	  
    /**
     * {@inheritDoc}
     */ 
    @Override  
    public boolean checkEmployeeIdForCreate(int employeeId) {
        Session session = null;
        boolean idExists = false;
		try {
            SessionFactory sessionFactory = dataBaseConnection.getSessionFactory();
            session = sessionFactory.openSession();
            Employee employee = (Employee) session.get(Employee.class, employeeId);
            if (null == employee) {
                idExists = true;
            }
        } catch(HibernateException e) { 
            idExists = false;
        } finally {
            if(session != null) {
                session.close();
            }
        }			
        return idExists;
    }
 
    /**
     * {@inheritDoc}
     */
    @Override	  
    public boolean checkEmployeeIdExists(int employeeId) {
        Session session = null;
        boolean idExists = false;
		try {
            SessionFactory sessionFactory = dataBaseConnection.getSessionFactory();
            session = sessionFactory.openSession();
            Criteria criteria = session.createCriteria(Employee.class);
		    criteria.add(Restrictions.eq("isDeleted", false));
            criteria.add(Restrictions.eq("id", employeeId));
            List<Employee> employee = criteria.list();
            if (0 == employee.size()) {
                idExists = true;
            }
        } catch(HibernateException e) { 
            idExists = true;
        } finally {
            if(session != null) {
                session.close();
            }
        }			
        return idExists;
    }
}
	  
















 