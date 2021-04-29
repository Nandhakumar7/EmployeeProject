import java.util.Scanner;

import com.ideas2it.employeeManagement.view.EmployeeView;
import com.ideas2it.projectManagement.view.ProjectView;

/**
 * EmployeeManagement for CRUD operation.
 *
 * @version   1.0 29-03-2021
 * @author    Nandhakumar.K
 */
public class EmployeeManagement {
    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        ProjectView projectView = new ProjectView();
        EmployeeView employeeView = new EmployeeView();
        String mainOptions = "\nChoose Your Opertaion.\n1.Employee"
                + "\t2.Project.\t3.Exit";
        int choosedOption = 0; 
        while (3 != choosedOption) {
            System.out.println(mainOptions);
            choosedOption = scanner.nextInt();
            switch(choosedOption) {
                case 1:
                    employeeView.mainPage();
                    break;
               case 2:
                    projectView.mainPage();
                    break;
                case 3:
                    System.out.println("\t\t\tThank You!");
                    break;
                default:
                    System.out.println("Please Enter Valid Input");
                    break;
            }
        }
    }  
}