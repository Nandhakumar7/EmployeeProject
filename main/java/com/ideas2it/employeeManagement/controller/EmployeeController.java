package com.ideas2it.employeeManagement.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ideas2it.employeeManagement.service.EmployeeService;
import com.ideas2it.employeeManagement.service.impl.EmployeeServiceImpl;

/**
 * Servlet implementation class EmployeeContraoller
 */
//@WebServlet("/EmployeeContraoller")
public class EmployeeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private EmployeeService employeeServiceImpl 
        = new EmployeeServiceImpl();
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
		     case "delete":
		    	 deleteProject(request, response);
		    	 break;
		    default:
		    	break;
		 }
	}
	

	private void getAllProject(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException { 
       List<List<String>> allProject = new LinkedList<List<String>>();
       allProject = employeeServiceImpl.getAllEmployeeDetails();
       request.setAttribute("allProject", allProject);
       RequestDispatcher dispatcher = request.getRequestDispatcher("employee.jsp");
       dispatcher.forward(request, response);
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
		     
	}
	
   public void addProject(HttpServletRequest request, HttpServletResponse response) throws IOException {
   	int id = 4895;
   	String name = request.getParameter("name");
   	int salary = Integer.parseInt(request.getParameter("salary"));
   	String mobileNumber = request.getParameter("mobileNumber");
   	String date = request.getParameter("dateOfBirth");
   	java.util.Date DateOfBirth = employeeServiceImpl.getDateOfBirth(date);
   	System.out.println(DateOfBirth);
   	System.out.println("hi all");
   	System.out.println(date);
   	List<LinkedList<String>> addresses = getAddressList(request, response);
   	employeeServiceImpl.addEmployeeDetails(id, name, salary, mobileNumber, DateOfBirth, addresses);
   	response.getWriter().println("succesfully added a project");
   }
   
   private List<LinkedList<String>> getAddressList(HttpServletRequest request, HttpServletResponse response) {
	   List<String> primaryAddress = new LinkedList<String>();
	   List<String> secondaryAddress = new LinkedList<String>();
	   List<LinkedList<String>> addresses = new LinkedList<LinkedList<String>>();
	   	primaryAddress.add("Primary");
	   	primaryAddress.add(request.getParameter("doorNumber"));
	   	primaryAddress.add(request.getParameter("streetName"));
	   	primaryAddress.add(request.getParameter("district"));
	   	primaryAddress.add(request.getParameter("state"));
	   	primaryAddress.add(request.getParameter("country"));
	   	primaryAddress.add(request.getParameter("pincode"));
	   	addresses.add((LinkedList<String>) primaryAddress);
	   	secondaryAddress.add("Secondary");
	   	secondaryAddress.add(request.getParameter("doorNumber1"));
	   	secondaryAddress.add(request.getParameter("streetName1"));
	   	secondaryAddress.add(request.getParameter("district1"));
	   	secondaryAddress.add(request.getParameter("state1"));
	   	secondaryAddress.add(request.getParameter("country1"));
	   	secondaryAddress.add(request.getParameter("pincode1"));
	   	addresses.add((LinkedList<String>) secondaryAddress);
	   	return addresses;
    }


public void showProject(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
   	int id = Integer.parseInt(request.getParameter("id"));
   	System.out.println(id); 
   	List<List<String>> projectDetails = new LinkedList<List<String>>();
       if(employeeServiceImpl.checkEmployeeIdExists(id)) {
       } else {
   	    projectDetails = employeeServiceImpl.getEmployeeDetails(id);
       }
       request.setAttribute("view", projectDetails);
       RequestDispatcher dispatcher = request.getRequestDispatcher("showEmployee.jsp");
       dispatcher.forward(request, response);
    }
     


	public void updateProject(HttpServletRequest request, HttpServletResponse response) throws IOException {
    	int id = Integer.parseInt(request.getParameter("id"));
    	String name = request.getParameter("name");
    	String manager = request.getParameter("manager");
    	String department = request.getParameter("department");
    	int timePeriod =Integer.parseInt(request.getParameter("timePeriod"));
    	employeeServiceImpl.updateEmployee(id, name, timePeriod, department, null);
    	response.getWriter().println("succesfully added a project");	
   }
   
   
   public void deleteProject(HttpServletRequest request, HttpServletResponse response) throws IOException {
       int id = Integer.parseInt(request.getParameter("id"));
       employeeServiceImpl.deleteEmployeeDetails(id);
   }

}
