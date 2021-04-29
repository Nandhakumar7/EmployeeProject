package com.ideas2it.projectManagement.dao.impl;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import org.hibernate.criterion.Restrictions;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.query.Query;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ideas2it.projectManagement.dao.Dao;
import com.ideas2it.projectManagement.model.Project;
import com.ideas2it.sessionFactory.DataBaseConnection;

/**
 * DaoImplementation get Details from user,
 * for doing CRUD operation.
 *
 * @version  1.0 29-03-2021.
 * @author   Nandhakumar.
 */
public class ProjectDaoImpl implements Dao {
    private DataBaseConnection dataBaseConnection
            = DataBaseConnection.getInstance();
	
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean insertProjectDetails(Project project) {
        boolean isProjectAdded = true;
        Session session = null;
        try {
		    SessionFactory sessionFactory = dataBaseConnection.getSessionFactory();
            session = sessionFactory.openSession();
		    session.beginTransaction();
            session.save(project);
		    session.getTransaction().commit();
        } catch(HibernateException e) {
            session.getTransaction().rollback();
            isProjectAdded = false;
        } finally {
            if(session != null) {
                session.close();
            }
        }
        return isProjectAdded;		
    }
	
    /**
     * {@inheritDoc}
     */
    @Override
    public Project getProjectDetails(int projectId) {
        Session session = null;
        Project project = null;
		try {
            SessionFactory sessionFactory = dataBaseConnection.getSessionFactory();
            session = sessionFactory.openSession();
            project = (Project) session.get(Project.class, projectId);
        } catch(HibernateException e) { 
            project = null;
        } finally {
            if(session != null) {
                session.close();
            }
        }			
        return project;
    }
	
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean updateProject(Project project) {
        boolean isUpdated = true;
        Session session = null;
        try {
		    SessionFactory sessionFactory = dataBaseConnection.getSessionFactory();
            session = sessionFactory.openSession();
		    session.beginTransaction();
            session.update(project);
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
    public boolean checkProjectIdForCreate(int projectId) {
        Session session = null;
        boolean idExists = false;
		try {
            SessionFactory sessionFactory = dataBaseConnection.getSessionFactory();
            session = sessionFactory.openSession();
            Project project = (Project) session.get(Project.class, projectId);
            if (null == project) {
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
    public boolean checkProjectIdExists(int projectId) {
        Session session = null;
        boolean idExists = false;
		try {
            SessionFactory sessionFactory = dataBaseConnection.getSessionFactory();
            session = sessionFactory.openSession();
            Criteria criteria = session.createCriteria(Project.class);
		    criteria.add(Restrictions.eq("isDeleted", false));
            criteria.add(Restrictions.eq("id", projectId));
            List<Project> projects = criteria.list();
            if (0 == projects.size()) {
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
	
    /**
     * {@inheritDoc}
     */ 
	@Override
    public List<Project> getAllProject() {
        Session session = null;
        List<Project> projects = null;
		try {
            SessionFactory sessionFactory = dataBaseConnection.getSessionFactory();
            session = sessionFactory.openSession();
            String selectQuery = "FROM Project WHERE isDeleted = false";
            Query query = session.createQuery(selectQuery);
            projects = query.list();
        } catch(HibernateException e) { 
            projects = null;
        } finally {
            if(session != null) {
                session.close();
            }
        }			
        return projects;
    }
}
	  
















 