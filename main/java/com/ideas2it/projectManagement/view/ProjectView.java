package com.ideas2it.projectManagement.view;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import com.ideas2it.projectManagement.controller.ProjectController;

/**
 * projectView for doing CRUD operation.
 *
 * @version  1.0 29-03-2021.
 * @author   Nandhakumar.
 */
public class ProjectView {
    private Scanner scanner = new Scanner(System.in);
    private ProjectController projectController = new ProjectController();
	
    /**
     * Main page for giving options for project CRUD operations.
     */
    public void mainPage() {
        int choosedOption = 0;
        String mainOptions = "\nChoose Your Service.\n1.Create"
                + "\t2.Show Project. \t3.update Project"
                + "\t4.Assign Project. \t5.unAssign Project"
                + "\t6.Delete Project  \t7.ShowAllProjects"
                + "\t8.Exit";
        while (8 != choosedOption) {
            System.out.println(mainOptions);
            choosedOption = scanner.nextInt();
            switch(choosedOption) {
                case 1:  createProject();
                         break;
                case 2:  showProject();
                         break;
                case 3:  updateProject();
                         break;
                case 4:  assignProject();
                         break;
                case 5:  unAssignProject();
                         break;
                case 6:  deleteProject();
                         break;
                case 7:  showAllProject();
                         break;
                case 8:  System.out.println("\t\t\tThank You!");
                         break;
                default: System.out.println("Please Enter Valid Input");
                         break;
            }
        }
    }  
   
    /**
     * project Details are get from user and Store into Database.
     */
    private void createProject() {
        System.out.print("Enter Id: ");
        int projectId  = scanner.nextInt();
        if(projectController.checkProjectIdForCreate(projectId)) {
            String projectName = getProjectName();
            String projectManager = getProjectManager();
            String department = getDepartment(); 
            int timePeriod = getTimePeriod();
            if (projectController.addProject(projectId,
                        projectName, projectManager, department, timePeriod)) {
                System.out.println("Details Added Sucessfully.");
            } else {
                System.out.println("Uploading Failed!");
            }
        } else {
            System.out.println("Project Id Already registered");
        }			
    }
 
    /**
     * projectId get from user and get details from,
     * database and display to user.
     */
    private void showProject() {
        System.out.println("Enter project Id: ");
        int projectId = getProjectId();
        List<String> projectDetails = new LinkedList<String>();
        projectDetails = projectController.getProjectDetails(projectId);	
        if (null == projectDetails) {
            System.out.println("Failed please try again");
        } else {
            for(String project : projectDetails) {
                System.out.println(project);
            }			
        }			
    }

    /**
     * Getting All the project details from database and display to user.
     */
    private void showAllProject() {
        List<String> projects = projectController.getAllProjectDetails();
        for(String project : projects) {
            System.out.println(project);
        }
    }		

    /**
     * To giving options for Editing project Details and update details.
     */
    private void updateProject() {
        System.out.println("Enter projectId");
        int projectId = getProjectId();
        int choosedService = 0;
        String editOptions = "Edit" + "\n\t1.projectName."
                + "\n\t2.projectManager." + "\n\t3.department."
                + "\n\t4.TimePeriod" + "\n\t5.Back to MainPage";
        String projectName = null, department = null;
        int timePeriod = 0, updateCount = 0;
        String projectManager = null;
    	while (5 != choosedService) {
            System.out.println(editOptions);
            choosedService = scanner.nextInt();
            switch(choosedService) {
                case 1:  projectName = getProjectName();
                         updateCount = 1;
                         break;
                case 2:  projectManager = getProjectManager();
                         updateCount = 1;
                         break;
                case 3:  department = getDepartment();
                         updateCount = 1;
                         break;
                case 4:  timePeriod = getTimePeriod();
                         updateCount = 1;
                         break;
                case 5:  System.out.println("Thank You!");
                         break;
                default: System.out.println("Please Enter Valid Input");
                         break;
            }
        }
        if (1 == updateCount) {
            if (projectController.updateProject(projectId, projectName,
                      projectManager, department, timePeriod)) {
                System.out.println("Sucessfully Updated"); 
            } else {
                System.out.println("Failed to upload"); 
            }
        }			
    }

    /**
     * Get projectName from user.
     *
     * @return projectName  projectName of the employee. 
     */
    private String getProjectName() {
        System.out.println("Enter projectName:");
        scanner.skip("[\r\n]+");
        String projectName = scanner.nextLine();
        return projectName;
    }
          
    /**
     * Get projectManager from user.
     *
     * @return projectManager  projectManager of the employee.
     */
    private String getProjectManager() {      
        System.out.println("Enter projectManager:");
        String projectManager = scanner.next();
        return projectManager;
    }
	
    /**
     * To getting department  from user.
     *
     * @return department   department of the project.
     */
    private String getDepartment() {
        System.out.println("Enter Department:");
        String department = scanner.next();
        return department;
    }
    
    /**
     * To get timePeriod from user.
     *
     * @return timePeriod  time period for completeing project.
     */
    private int getTimePeriod() {
        System.out.println("Enter Timeperiod:");
        int timePeriod = scanner.nextInt();
        return timePeriod;
    }
    
	
    /**
     * To get project Id from user and delete employee Details.
     */
    private void deleteProject() {
        System.out.println("Enter Id: ");
        int projectId  = getProjectId();
        if (projectController.deleteProjectDetails(projectId)) {
            System.out.println("Deleted Sucessfully!");
        } else {
            System.out.println("Failed to delete!");
        }
    }
    
    /**
     * To projectId and employeeid from user,
     * assign that employee to that project.
     */
    private void assignProject() {
        System.out.println("Enter project Id: ");
        int projectId  = getProjectId();
        System.out.println("Enter EmployeeId to assign project" + "\n\t");
        List <List> projectEmployees = getEmployeIdList(projectId);
        List<Integer> employeesId = new ArrayList<Integer>();
        if (1 < projectEmployees.size()) {
            employeesId = projectEmployees.get(1);
            if (0 != (projectEmployees.get(0)).size()) {
                System.out.println(projectEmployees.get(0) + " Already Assigned");
            }
        } else {
            employeesId = projectEmployees.get(0);
        }
        if (projectController.assignProject(projectId, employeesId)) {
            System.out.println("Project Assigned Sucessfully"); 
        } else {
            System.out.println("Failed to Assign ");
        }
    }

    /**
     * To projectId and employeeid from user,
     * unassign that employee to that project.
     */      
    private void unAssignProject() {
        System.out.println("Enter project Id: ");
        int projectId  = getProjectId();
        System.out.println("Enter EmployeeId to unassign project" + "\n\t"); 
        List <List> projectEmployees = getEmployeIdList(projectId);
        List<Integer> employeesId = new ArrayList<Integer>();
        if (1 < projectEmployees.size()) {
            employeesId = projectEmployees.get(0);
            if (0 != projectEmployees.get(1).size()) {
                System.out.println(projectEmployees.get(1) + " Not Assigned Employees");
            }
        } else {
            System.out.println("No one Assigned In this project");
        }
        if (projectController.unAssignProject(projectId, employeesId)) {
            System.out.println("Project unAssigned Sucessfully"); 
        } else {
            System.out.println("Failed to Assign ");
        }
    } 
 
    /**
     * To getting list of Id from user and for,
     * doing single opertaions for multiple employees.
     *
     * @return employeesId.  contains employees id what you enter.
     */  
    private List<List> getEmployeIdList(int projectId) {
        String options = "1.Enter 1 to add employeeId"
                + "\t Enter 0 to Exit";	
        int choosedOption = 1;
        List<Integer> employeesId = new ArrayList<Integer>();
        do {
            if (1 == choosedOption) {
                System.out.println("Enter Employee Id: ");
                int employeeId = getEmployeeId();
                employeesId.add(employeeId);
            }
            System.out.println(options);
            choosedOption = scanner.nextInt();
        } while (0 != choosedOption);
        List<List> projectEmployees
                = projectController.getProjectEmployees(projectId, employeesId);
        return 	projectEmployees;
    }		
		
    /**
     * To getting Id from user and check Id present in our map or not.
     *
     * @return projectId.  If Id present return id.
     */
    private int getProjectId() {
        int projectId = scanner.nextInt();
        if(projectController.checkProjectIdExists(projectId)) {
            System.out.println("project Id Not Found!"
                    + "\nEnter ID Again");
            projectId = getProjectId();
            return projectId;
        } else {
            return projectId;
        }
    }
	
    /**
     * To getting Id from user and check Id present in database or not.
     *
     * @return employeeId.  If Id present return id.
     */
    private int getEmployeeId() {
        int employeeId = scanner.nextInt();
        if(projectController.checkEmployeeIdExists(employeeId)) {
            System.out.println("Employee Id Not Found!"
                    + "\nEnter ID Again");
            employeeId = getEmployeeId();
            return employeeId;
        } else {
            return employeeId;
        }  
    }
}