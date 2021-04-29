package com.ideas2it.employeeManagement.view;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.ideas2it.employeeManagement.controller.EmployeeController;

/**
 * EmployeeView for doing CRUD operation.
 *
 * @version  1.0 29-03-2021.
 * @author   Nandhakumar.
 */
public class EmployeeView {
    private Scanner scanner = new Scanner(System.in);
    private EmployeeController employeeController = new EmployeeController();
	
    /**
     * Main page for giving options for CRUD operations.
     */
    public void mainPage() {
        int choosedOption = 0;
        String mainOptions = "\nChoose Your Service.\n1.Create"
                + "\t2.Show Employee.\t3.Show All Employees"
                + "\t4.Edit Employee.\t5.Delete Employee."
                + "\t6.ProjectAssign \t7.Project UnAssign \t8.Exit";
        while (8 != choosedOption) {
            System.out.println(mainOptions);
            choosedOption = scanner.nextInt();
            switch(choosedOption) {
                case 1:  addEmployeeDetails();
                         break;
                case 2:  showEmployeeDetails();
                         break;
                case 3:  showAllEmployee();
                         break;
                case 4:  editEmployeeDetails();
                         break;
                case 5:  deleteEmployeeDetails();
                         break;
                case 6:  assignProject();
                         break;
                case 7:  unAssignProject();
                         break;
                case 8:  System.out.println("\t\t\tThank You!");
                         break;
                default: System.out.println("Please Enter Valid Input");
                         break;
            }
        }
    }  
   
    /**
     * Employee Details are get from user and Store into Database.
     */
    private void addEmployeeDetails() {
        System.out.print("Enter Id: ");
        int employeeId = scanner.nextInt();
        if(employeeController.checkEmployeeIdForCreate(employeeId)) {
            String name = getName();
            float salary = getSalary();
            String mobileNumber = getMobileNumber(); 
            Date dateOfBirth = getDateOfBirth();
            System.out.println("Enter Your Primary Address");			
            List<LinkedList<String>> addresses = new LinkedList<LinkedList<String>>();
            addresses = addAddress(employeeId);
            if (employeeController.addEmployeeDetails(employeeId,
     	               name, salary, mobileNumber, dateOfBirth, addresses)) {
                System.out.println("Details Added Sucessfully.");
            } else {
                System.out.println("Uploading Failed!");
            }				
        } else {
            System.out.println("EmployeeId already registered"); 
        }
    }

    /**
     * Employee Address Details are get from user and Store into Database.
     */
    private List<LinkedList<String>> addAddress(int employeeId) {
        String addressType = "Primary";
        List<LinkedList<String>> addresses = new LinkedList<LinkedList<String>>();
        do {
            LinkedList<String> address = new LinkedList<String>();
            address.add(addressType);
            System.out.print("   Door.NO:");		
            address.add(scanner.next());
            System.out.print("StreetName:");
            scanner.skip("[\r\n]+");
            address.add(scanner.nextLine()); 
            System.out.print("  District:");
            address.add(scanner.nextLine()); 
            System.out.print("     State:");
            address.add(scanner.nextLine());
            System.out.print("   Country:");		 
            address.add(scanner.nextLine());
            System.out.print("   PinCode:");
            address.add(scanner.nextLine());
            addresses.add(address);
            System.out.print("If you want to add more Address "
                    + "\tEnter 1 "+ "\tEnter 0 for Exit");
            addressType = "Secondary";				   
        } while (0 != scanner.nextInt());
        return addresses;
    }
	
    /**
     * EmployeeId get from user and get details from,
     * database and display to user.
     */ 
    private void showEmployeeDetails() {
        System.out.println("Enter Employee Id: ");
        int employeeId = getEmployeeId();
        List<String> employeeDetails 
                = employeeController.getEmployeeDetails(employeeId);	
        if (null == employeeDetails) {
            System.out.println("Failed try again");
        } else {
            for(String employee : employeeDetails) {
                System.out.println(employee);
            }			
        }			
    }
    
    /**
     * Getting All the employees details from database and display to user.
     */
    private void showAllEmployee() {
        List<String> employees = employeeController.getAllEmployeeDetails();
        for(String employeesDetails : employees) {
            System.out.println(employeesDetails);
        }
        String showOptions = "1.If want to see more details" 
                + " Enter 1" +"\t2.Enter 0 to Exit.";
        int choosedoption = 1;
        while(0 != choosedoption) {
            System.out.println(showOptions);
            choosedoption = scanner.nextInt();
            if (1 == choosedoption) {
                System.out.println("Enter EmployeeId:");
                int employeeId = scanner.nextInt();
                showEmployeeAddress(employeeId);
            } else {
                choosedoption = 0;
            }				
        }
    }

    /**
     * To giving options for Editing Employee Details and update details.
     */
    private void editEmployeeDetails() {
        System.out.println("Enter EmployeeID");
        int employeeId = getEmployeeId();
        int choosedService = 0;
        String editOptions = "Edit" + "\n\t1.Name."+ "\n\t2.Date Of Birth."
                + "\n\t3.Salary." + "\n\t4.MobileNumber."
                + "\n\t5.AddressChange" + "\n\t6.Back to MainPage";
        String name = null, mobileNumber = null;
        Date dateOfBirth = null;
        float salary = 0;
        int updateCount = 0;
    	while (6 != choosedService) {
            System.out.println(editOptions);
            choosedService = scanner.nextInt();
            switch(choosedService) {
                case 1:  name = getName();
                         updateCount = 1;
                         break;
                case 2:  dateOfBirth = getDateOfBirth();
                         updateCount = 1;
                         break;
                case 3:  salary = getSalary();
                         updateCount = 1;
                         break;
                case 4:  mobileNumber = getMobileNumber();
                         updateCount = 1;
                         break;
                case 5:  employeeAddressChange(employeeId);
                         break;
                case 6:  System.out.println("Thank You!");
                         break;
                default: System.out.println("Please Enter Valid Input");
                         break;
            }
        }
        if (1 == updateCount) {
            if (employeeController.updateEmployee(employeeId, name, salary,
                       mobileNumber, dateOfBirth)) {
                System.out.println("Sucessfully Updated"); 
            } else {
                System.out.println("Failed to upload"); 
            } 
        }
    }
	

    /**
     * Get Name from user.
     *
     * @return name  Name of the employee. 
     */
    private String getName() {
        System.out.println("Enter Name:");
        String name = scanner.next();
        return name;
    }
          
    /**
     * Get Salary from user.
     *
     * @return salary  Salary of the employee.
     */
    private float getSalary() {      
        System.out.println("Enter Salary:");
        float salary = scanner.nextFloat();
        return salary;
    }
	
    /**
     * To getting mobile number from user and validate,
     * then send validated mobilenumber.
     *
     * @return mobileNumber    For store validated,
     * mobileNumber in emloyees Map.
     */
    private String getMobileNumber() {
        System.out.print("Enter Mobile Number: ");
        String mobileNumber = scanner.next();
        if(employeeController.validateMobileNumber(mobileNumber)) {
    	    return mobileNumber;
    	} else {
    	    System.out.println("Invalid Mobile Number!");
    	    mobileNumber = getMobileNumber();
	    return mobileNumber;
        }   
    }
    
    /**
     * To get DateOfBirth from user and validate,
     * then send validated DateOfBirth.
     *
     * @return validatedDateOfBirth  For store validated,
     * DateOfBirth in emloyees Map.
     */
    private Date getDateOfBirth() {
        System.out.print("Enter Date of Birth Format('dd/mm/yyyy'):"); 
        String date = scanner.next();
        Date dateOfBirth = employeeController.getDateOfBirth(date);
        Date validatedDateOfBirth;
        if(null != dateOfBirth) {
    	    validatedDateOfBirth = dateOfBirth;
    	} else {
    	    System.out.println("Invalid Date Of Birth!");
    	    validatedDateOfBirth = getDateOfBirth();
        } 
        return validatedDateOfBirth;
    }
	
    /**
     * To get employee Id from user and delete employee Details.
     */
    private void deleteEmployeeDetails() {
        System.out.println("Enter Id: ");
        int employeeid  = getEmployeeId();
        if (employeeController.deleteEmployeeDetails(employeeid)) {
            System.out.println("Deleted Sucessfully!");
        } else {
            System.out.println("Failed to delete!");
        }
    }

    /**
     * Employee address Details are get from user and 
     * update and delete old address.
     *
     * @param employeeId    Id is for one whose Details need Change.
     */	
    private void employeeAddressChange(int employeeId){
        int addressCount = showEmployeeAddress(employeeId);
        System.out.println("\n\tIf you want to delete address Enter 1"
                + "\n\twant to update Existing address as a primary Enter 2"
                + "\n\twant to update address Enter 3"
                + "\n\tAdd New Address Enter 4");
        int enteredValue = scanner.nextInt();
        System.out.println("\nEnter which address you want to edit");
        int choosedAddress = scanner.nextInt();
        if(choosedAddress <= addressCount) { 
            if (1 == enteredValue){
                if (employeeController.deleteEmployeeAddress
                        (choosedAddress, employeeId)) {
                    System.out.println("Deleted Sucessfully!"); 
                } else {
                    System.out.println("\tPrimary Address can't delete"
                            + " please select other address as primary ");
                    employeeAddressChange(employeeId);
                }
            } else if (2 == enteredValue) {
                updateAsPrimaryAddress(choosedAddress,employeeId);
            } else if (3 == enteredValue) {
                updateAddress(choosedAddress,employeeId);
            } else if (4 == enteredValue) {
                addNewAddress(employeeId); 
	        } 
        } else {
            System.out.println("Enter valid input"); 
        }
    }
	
    /**
     * Employee address Details show to user
     *
     * @param employeeId    Id is for one whose address Details need.
     */
    private int showEmployeeAddress(int employeeId) {
        List<String> employeeAddress
                = employeeController.getEmployeeAddress(employeeId);
        int totalAddress = employeeAddress.size();
        int addressCount = 1;
        for (String address : employeeAddress) {
           System.out.println("\n" + addressCount + "." + address);
           addressCount ++;
        }
        return totalAddress;
    }
	
    /**
     * update address as a primary address
     * 
     * @choosedAddress  address to update
     * @employeeId      one whose address need to change.
     */
    private void updateAsPrimaryAddress(int choosedAddress,int employeeId) {
        if(employeeController.updateAsPrimaryAddress(choosedAddress,employeeId)) {
            System.out.println("update Sucessfully!");
        } else {
            System.out.println("Failed"); 
        }
    }
        

    /**
     * Employee address Details are get from user and 
     * update new address.
     *
     * @param employeeId      Id is for one whose Details need Change.
     * @param choosedAddress  which address want to update.
     */
    private void updateAddress(int choosedAddress, int employeeId) {
        System.out.print("Enter address:"); 
        System.out.print("Door.NO:");		
        int doorNumber = scanner.nextInt();
        System.out.print("StreetName:");
        scanner.skip("[\r\n]+");
        String streetName = scanner.nextLine(); 
        System.out.print("District:");
        String district = scanner.nextLine(); 
        System.out.print("State:");
        String State = scanner.nextLine();
        System.out.print("Country:");		 
        String Country = scanner.nextLine(); 
        System.out.print("PinCode:");
        int pinCode = scanner.nextInt();
        if (employeeController.updateAddress(choosedAddress,employeeId,
                    doorNumber, streetName, district, State, Country, pinCode)) {
            System.out.println("uploadede Sucessfully"); 
        } else {
            System.out.println("Failed to upload"); 
        }
    }

    /**
     * Employee  new address Details are get from user and 
     * add to database.
     *
     * @param employeeId    Id is for one whose address to add.
     */	
    private void addNewAddress(int employeeId) {
        System.out.print("Enter address:"); 
        System.out.print("Door.NO:");		
        int doorNumber = scanner.nextInt();
        System.out.print("StreetName:");
        scanner.skip("[\r\n]+");
        String streetName = scanner.nextLine(); 
        System.out.print("District:");
        String district = scanner.nextLine(); 
        System.out.print("State:");
        String State = scanner.nextLine();
        System.out.print("Country:");		 
        String Country = scanner.nextLine(); 
        System.out.print("PinCode:");
        int pinCode = scanner.nextInt();
        if (employeeController.addNewAddress(employeeId,
                    doorNumber, streetName, district, State, Country, pinCode)) {
            System.out.println("uploaded Sucessfully"); 
        } else {
            System.out.println("Failed to upload"); 
        }
    }
	
    /**
     * To getting Id from user and check Id present in our map or not.
     *
     * @return employeeId.  If Id present return id.
     */
    private int getEmployeeId() {
        int employeeId = scanner.nextInt();
        if(employeeController.checkEmployeeIdExists(employeeId)) {
            System.out.println("Employee Id Not Found!"
                    + "\nEnter ID Again");
            employeeId = getEmployeeId();
            return employeeId;
        } else {
            return employeeId;
        }  
    }

    /**
     * To projectId and employeeid from user,
     * assign that employee to that project.
     */
    private void assignProject() {
        System.out.println("Enter Employee Id: ");
        int employeeId  = getEmployeeId();
        System.out.println("Enter ProjectId to assign project" + "\n\t");
        List <List> employeeProjectsId = getProjectIdList(employeeId);
        List<Integer> projectsId = new ArrayList<Integer>();
        if(1 < employeeProjectsId.size()) {
            projectsId = employeeProjectsId.get(1);
            if (0 != employeeProjectsId.get(0).size()) {
                System.out.println(employeeProjectsId.get(0) + " Already Assigned");
            }
        } else {
            projectsId = employeeProjectsId.get(0);
        }
        if (employeeController.assignProject(employeeId, projectsId)) {
            System.out.println("Project Assigned Sucessfully"); 
        } else {
            System.out.println("Failed to Assign ");
        }
    }

    /**
     * To get projectId and employeeid from user,
     * unassign that employee to that project.
     */   
    private void unAssignProject() {
        System.out.println("Enter employeeId: ");
        int employeeId  = getEmployeeId();
        System.out.println("Enter projectId to unassign project" + "\n\t"); 
        List <List> employeeProjectsId = getProjectIdList(employeeId);
        List<Integer> projectsId = new ArrayList<Integer>();
        if(1 < employeeProjectsId.size()) {
            projectsId = employeeProjectsId.get(0);
            if (0 != employeeProjectsId.get(1).size()) {
                System.out.println(employeeProjectsId.get(1) + " Not Assigned Employees");
            }
        } else {
            System.out.println("No one Assigned In this project");
        }
        if (employeeController.unAssignProject(employeeId, projectsId)) {
            System.out.println("Project unAssigned Sucessfully"); 
        } else {
            System.out.println("Failed to Assign ");
        }
    } 
 
    /**
     * To getting list of Id from user and for,
     * doing single opertaions for multiple employees.
     *
     * @return projectsId.  contains employees id what you enter.
     */ 
    private List<List> getProjectIdList(int employeeId) {
        String options = "1.Enter 1 to add ProjectId"
                + "\t Enter 0 to Exit";	
        int choosedOption = 1;
        List<Integer> projectsId = new ArrayList<Integer>();
        do {
            if (1 == choosedOption) {
                System.out.println("Enter Project Id: ");
                int projectId = getProjectId();
                projectsId.add(projectId);
            }
            System.out.println(options);
            choosedOption = scanner.nextInt();
        } while (0 != choosedOption);
        List<List> employeeProjects
                = employeeController.employeeProjects(employeeId, projectsId);
        return 	employeeProjects;
    }

    /**
     * To getting Id from user and check Id present in our map or not.
     *
     * @return projectId.  If Id present return id.
     */
    private int getProjectId() {
        int projectId = scanner.nextInt();
        if(employeeController.checkProjectIdExists(projectId)) {
            System.out.println("project Id Not Found!"
			        + "\nEnter ID Again");
            projectId = getProjectId();
            return projectId;
        } else {
            return projectId;
        }
    }
}
