package barangaydocs;

import java.util.Scanner;

public class BarangayDocs {   

    public static void main(String[] args) {      
        Scanner sc = new Scanner(System.in);
        String response;
        Citizen ct = new Citizen();
        Documents dc = new Documents();
    
        do {
            System.out.println(" ========================= ");
            System.out.println("| Welcome to BarangayDocs |");
            System.out.println(" ========================= ");
            System.out.println("1. Add Citizen            |");
            System.out.println("2. Add Document           |");           
            System.out.println("3. EXIT                   |");
            System.out.println("Enter Action:             |");           
            System.out.println("-------------------------- ");
            int action = sc.nextInt();            
    
            switch(action){
                case 1:
                        ct.getCitizen();
                    break;
                case 2:
                        dc.getDocuments();
                    break;                   
               case 3:
                   System.out.println("Exiting the program. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid action. Please try again.");
                    break;
            }
            System.out.println("Do you want to continue? (Y/N): ");
            response = sc.next();
    
        }while(response.equalsIgnoreCase("y"));
      
    }
}