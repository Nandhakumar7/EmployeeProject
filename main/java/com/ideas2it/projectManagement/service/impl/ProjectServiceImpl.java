package com.ideas2it.projectManagement.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.ideas2it.projectManagement.dao.impl.ProjectDaoImpl;
import com.ideas2it.employeeManagement.model.Employee;
import com.ideas2it.projectManagement.model.Project;
import com.ideas2it.employeeManagement.service.EmployeeService;
import com.ideas2it.employeeManagement.service.impl.EmployeeServiceImpl;
import com.ideas2it.projectManagement.service.ProjectService;

/**
 * ServiceImplementation get Details from user,
 * for doing CRUD operation.
 *
 * @version  1.0 29-03-2021.
 * @author   Nandhakumar.
 */
public class ProjectServiceImpl implements ProjectService{
    private ProjectDaoImpl projectDaoImpl = new ProjectDaoImpl();

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean addProject(int projectId,
            String projectName, String projectManager, String department,
            int timePeriod) {
        Project project = new Project (projectId, projectName, 
     	        projectManager, department, timePeriod);
        boolean isProjectAdded = projectDaoImpl.insertProjectDetails(project);	
        return isProjectAdded;
    }
			
    /**
     * {@inheritDoc}
     */
    @Override
    public List<String> getProjectDetails(int projectId) {
        Project project = projectDaoImpl.getProjectDetails(projectId);
        List<Employee> employees = new ArrayList<Employee>();
        List<String> projectDetails = new LinkedList<String>();
        employees = project.getEmployeesList();
        Integer id = project.getId();
        Integer timePeriod = project.getTimePeriod();
        projectDetails.add(id.toString());
        projectDetails.add(project.getProjectName());
        projectDetails.add(project.getManagerName());
        projectDetails.add(project.getDepartment());
        projectDetails.add(timePeriod.toString());
        if(null != employees) {
            for(Employee employee : employees) {	
                Integer employeeId = employee.getId();
                projectDetails.add(employeeId.toString());
                projectDetails.add(employee.getName());
            } 	
        } else {
            projectDetails.add("\n" + "NO Employee Assigned ");
        }
        return projectDetails;
    }
    
    /**
     * {@inheritDoc}
     */ 
    @Override
    public boolean checkProjectIdExists(int projectId){
        return(projectDaoImpl.checkProjectIdExists(projectId));
    }
	
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean checkProjectIdForCreate(int projectId){
        return(projectDaoImpl.checkProjectIdForCreate(projectId));
    }
 
    /**
     * {@inheritDoc}
     */ 
    @Override
    public List<List<String>> getAllProjectDetails() {
        List<Project> allProject = projectDaoImpl.getAllProject();
        List<List<String>> allProjects = new LinkedList<List<String>>();
        for(Project project : allProject) {
            List<String> projectDetails = new LinkedList<String>();
        	Integer id = project.getId();
            Integer timePeriod = project.getTimePeriod();
            projectDetails.add(id.toString());
            projectDetails.add(project.getProjectName());
            projectDetails.add(project.getManagerName());
            projectDetails.add(project.getDepartment());
            projectDetails.add(timePeriod.toString());
            allProjects.add(projectDetails);
        }
        return allProjects;
    }

    /**
     * {@inheritDoc}
     */ 
    @Override
    public boolean updateProject(int projectId, String projectName, String managerName,
            String department, int timePeriod) {
        Project project = projectDaoImpl.getProjectDetails(projectId);
        String newProjectName
                = (null == projectName) ? project.getProjectName() : projectName;
        String newManagerName
                = (null == managerName) ? project.getManagerName() : managerName;
        String newDepartment
                = (null == department) ? project.getDepartment() : department;
        int newTimePeriod
                = (0 == timePeriod) ? project.getTimePeriod() : timePeriod;
        project.setProjectName(newProjectName);
        project.setManagerName(newManagerName);
        project.setDepartment(newDepartment);
        project.setTimePeriod(newTimePeriod);
        return projectDaoImpl.updateProject(project);
    }
	
    /**
     * {@inheritDoc}
     */ 
    @Override
    public boolean assignProject(int projectId, List<Integer> employeeId) {
        EmployeeService employeeService = new EmployeeServiceImpl();
        Project project = projectDaoImpl.getProjectDetails(projectId);
        List<Employee> projectEmployees = project.getEmployeesList();
        List<Employee> employees 
                = employeeService.getEmployeeForProject(employeeId);
        for (Employee employee : employees) {
            projectEmployees.add(employee);
        }
        project.setEmployeesList(projectEmployees);
        return projectDaoImpl.updateProject(project);
    }

    /**
     * {@inheritDoc}
     */
    @Override
	public List<Project> getProjectsForEmployee(List<Integer> projectsId) {
        List<Project> requiredProjects = new ArrayList<Project>();
        List<Project> projects = projectDaoImpl.getAllProject();
        for (Project project : projects) {			
            if (projectsId.contains(project.getId())) {
                requiredProjects.add(project);
            }
        }
        return requiredProjects;
    }
	
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean unAssignProject(int projectId, List<Integer> employeesId) {
        Project project = projectDaoImpl.getProjectDetails(projectId);
        List<Employee> projectEmployees = project.getEmployeesList();
        List<Employee> employees = new ArrayList<Employee>();
        for (Employee employee : projectEmployees) {
            if( ! (employeesId.contains(employee.getId()))) {
                employees.add(employee);
            }
        }
        project.setEmployeesList(employees);
        return projectDaoImpl.updateProject(project);
    }

    /**
     * {@inheritDoc}
     */ 
    @Override
    public boolean deleteProjectDetails(int projectId) {
        Project project = projectDaoImpl.getProjectDetails(projectId);
        project.setIsDeleted(true);
        project.setEmployeesList(null);
        return projectDaoImpl.updateProject(project);
    }
	
    /**
     * {@inheritDoc}
     */ 
    @Override  
    public boolean checkEmployeeIdExists(int employeeId) {
        EmployeeService employeeService = new EmployeeServiceImpl();		
        return employeeService.checkEmployeeIdExists(employeeId);
    }

    /**
     * {@inheritDoc}
     */ 
    @Override
    public List<List<Integer>> getProjectEmployees(int projectId) {
    	EmployeeService employeeService = new EmployeeServiceImpl();
        Project project = projectDaoImpl.getProjectDetails(projectId);
        List<Integer> projectAssignedEmployees = new LinkedList<Integer>();
        List<Employee> employees = project.getEmployeesList();
        List<Integer> allEmployeesId = new ArrayList<Integer>();
        List<List<String>> allEmployees = employeeService.getAllEmployeeDetails(); 
        List<List<Integer>> employeeProjectAssignedDetails = new LinkedList<List<Integer>>();
        if (null == employees) {
            projectAssignedEmployees = null;
        } else {
            for(Employee employee : employees) {
                projectAssignedEmployees.add(employee.getId());
            }
        }
        for(List<String> employee : allEmployees) {
        	int employeeId = Integer.parseInt(employee.get(0));
        	if(!(projectAssignedEmployees.contains(employeeId))) {
        	    allEmployeesId.add(employeeId);
        	}
        }
        employeeProjectAssignedDetails.add(projectAssignedEmployees);
        employeeProjectAssignedDetails.add(allEmployeesId);
        return employeeProjectAssignedDetails;
    }
}