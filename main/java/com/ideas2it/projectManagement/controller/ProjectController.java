package com.ideas2it.projectManagement.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.internal.build.AllowSysOut;

import com.ideas2it.projectManagement.service.impl.ProjectServiceImpl;
import com.ideas2it.projectManagement.model.Project;
import com.ideas2it.projectManagement.service.ProjectService;

/**
 * Servlet implementation class projectController
 */
public class ProjectController extends HttpServlet {
	
	private ProjectService projectService = new ProjectServiceImpl();
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 String action = request.getParameter("action");
		 System.out.println("hi........."+action);
		 System.out.println("hidddd");
		 switch (action) {
		     case "add":
		    	 addProject(request, response);
		    	 break;
		     case "view":
		    	 showProject(request, response);
		    	 break;
		     case "edit":
		    	 updateProject(request, response);
		    	 break;
		     case "showAll":
		    	 getAllProject(request, response);
		    	 break;
		     case "show":
		    	 getShow(request, response);
		    	 break;
		     case "delete":
		    	 deleteProject(request, response);
		    	 break;
		     case "getAllProjectEmployees":
		         getAllProjectEmployees(request, response);
		         break;
		    default:
		    	break;
		 }
	}
	

	private void getAllProjectEmployees(HttpServletRequest request, HttpServletResponse response) {
		List<List<Integer>> projectEmployeesList = projectService.getProjectEmployees(0);	
	}


	private void getShow(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("editProject.jsp");
        dispatcher.forward(request, response);
		
	}


	private void getAllProject(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException { 
        List<List<String>> allProject = new LinkedList<List<String>>();
        allProject = projectService.getAllProjectDetails();
        request.setAttribute("allProject", allProject);
        RequestDispatcher dispatcher = request.getRequestDispatcher("project.jsp");
        dispatcher.forward(request, response);
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);	     
	}
	
    public void addProject(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    	int id = Integer.parseInt(request.getParameter("id"));
    	String name = request.getParameter("name");
    	String manager = request.getParameter("manager");
    	String department = request.getParameter("department");
    	int timePeriod =Integer.parseInt(request.getParameter("timePeriod"));
    	String message = null;
    	if(projectService.addProject(id, name, manager, department,timePeriod)) {
             message = "SUCESSFULLY ADDED!";    
    	} else {
    		 message = "FAILED TO ADD!..PLEASE TRY AGAIN";    
    	}
    	request.setAttribute("message", message);
        RequestDispatcher dispatcher = request.getRequestDispatcher("messagePrint.jsp");
        dispatcher.forward(request, response);
    }
    
    public void showProject(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    	int id = Integer.parseInt(request.getParameter("id"));
    	System.out.println(id); 
    	List<String> projectDetails = new LinkedList<String>();
        if(projectService.checkProjectIdExists(id)) {
        	projectDetails.add("Employee Not Exists"); 
        } else {
    	    projectDetails = projectService.getProjectDetails(id);
            for(String project: projectDetails) {
        	    System.out.println(project);
            }
        }
        request.setAttribute("view", projectDetails);
        RequestDispatcher dispatcher = request.getRequestDispatcher("viewProject.jsp");
        dispatcher.forward(request, response);
     }
      


	public void updateProject(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
     	int id = Integer.parseInt(request.getParameter("id"));
     	String name = request.getParameter("name");
     	String manager = request.getParameter("manager");
     	String department = request.getParameter("department");
     	int timePeriod =Integer.parseInt(request.getParameter("timePeriod"));
       	String message = null;
    	if(projectService.updateProject(id, name, manager, department,timePeriod)) {
             message = "SUCESSFULLY UPDATED!";    
    	} else {
    		 message = "FAILED TO UPDATE!..PLEASE TRY AGAIN";    
    	}
    	request.setAttribute("message", message);
        RequestDispatcher dispatcher = request.getRequestDispatcher("messagePrint.jsp");
        dispatcher.forward(request, response);
    }
    
    
    public void deleteProject(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        String message = null;
        if(projectService.deleteProjectDetails(id)) {
            message = "SUCESSFULLY DELETED!";    
   	} else {
   		 message = "FAILED TO DELETE!..PLEASE TRY AGAIN";    
   	}
   	request.setAttribute("message", message);
       RequestDispatcher dispatcher = request.getRequestDispatcher("messagePrint.jsp");
       dispatcher.forward(request, response);
    }
}
