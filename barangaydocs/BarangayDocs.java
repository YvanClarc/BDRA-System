package barangaydocs;

import java.util.InputMismatchException;
import java.util.Scanner;

public class BarangayDocs {   

    public static void main(String[] args) {      
        Scanner sc = new Scanner(System.in);
        String response;
        Citizen ct = new Citizen();
        Documents dc = new Documents();
        
        int action = -1;
    
        do {
            System.out.println(" ======================== ");
            System.out.println("| Welcome to BarangayDocs|");
            System.out.println(" ======================== ");
            System.out.println("1. Citizen               |");
            System.out.println("2. Document              |");
            System.out.println("3. Reports               |");
            System.out.println("4. EXIT                  |");
            System.out.println("Enter Action:            |");
            System.out.println("------------------------- ");

        boolean validAction = false;
            while (!validAction) {
                try {
                    action = sc.nextInt();
                    if (action >= 1 && action <= 4) {
                        validAction = true;
                    } else {
                System.out.println("Invalid action. Please enter a number between 1 and 3.");
            }
        } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter an integer.");
                sc.next();
        }
}                
            switch(action){
                case 1:
                        ct.getCitizen();
                    break;
                case 2:
                        dc.getDocuments();
                    break;                   
               case 3:
                        
               case 4:
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