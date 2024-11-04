
package barangaydocs;

import java.util.Scanner;

public class Citizen {
    Scanner sc = new Scanner(System.in);
    String response;
    
    public void getCitizen(){
        
        do { 
            System.out.println(" ================= ");
            System.out.println("|  Adding Citizen |");
            System.out.println(" ================= ");
            System.out.println("1. ADD Citizen    |");
            System.out.println("2. VIEW Citizen   |");
            System.out.println("3. UPDATE Citizen |");
            System.out.println("4. DELETE Citizen |");
            System.out.println("5. EXIT           |");
            System.out.println("Enter Action:     |");
            System.out.println("------------------ ");
            int action = sc.nextInt();
            Citizen hehe = new Citizen();
            
            switch(action){
                case 1:
                        hehe.addCitizen();
                    break;
                case 2:
                        hehe.viewCitizens();
                    break;    
               case 3:
                        hehe.updateCitizen();
                    break;     
               case 4:
                        hehe.deleteCitizen();
                    break;
               case 5:
                   System.out.println("Exiting Adding Citizen. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid action. Please try again.");
                    break;
            }
            System.out.println("Do you want to continue? (Y/N): ");
            response = sc.next();
        
        }while(response.equalsIgnoreCase("y"));
      
    }
    
    public void addCitizen(){
        Scanner sc = new Scanner(System.in);
        config conf = new config();
        System.out.println("=======================");
        System.out.print("Citizen First Name: ");
        String fname = sc.next();
        System.out.print("Citizen Last Name: ");
        String lname = sc.next();
        System.out.print("Citizen Email: ");
        String email = sc.next();
        System.out.print("Citizen Status: ");
        String status = sc.next();
        System.out.print("Citizen Postal Code: ");
        System.out.print("\n=======================");
        String pcode = sc.next();
        
        String sql = "INSERT INTO citizen (c_fname, c_lname, c_email, c_status, c_postalCode) VALUES (?, ?, ?, ?, ?)";


        conf.addRecord( sql, fname, lname, email, status, pcode);
        
    }
    private void viewCitizens() {
        config conf = new config();
        String BDRAQuery = "SELECT * FROM citizen";
        String[] BDRAHeaders = { "ID","First Name", "Last Name", "Email" ,"Status" ,"Postal Code"};
        String[] BDRAColumns = {"c_id" ,"c_fname", "c_lname", "c_email", "c_status" ,"c_postalCode"};

        conf.viewRecords(BDRAQuery, BDRAHeaders, BDRAColumns);
    }
    private void updateCitizen() {
        Scanner sc = new Scanner(System.in);
        config conf = new config();

        System.out.print("Enter Citizen ID to Update: ");
        int id = sc.nextInt();

        System.out.print("=================== ");
        System.out.print("New First Name: ");
        String fname = sc.next();
        System.out.print("New Last Name: ");
        String lname = sc.next();
        System.out.print("New Email: ");
        String email = sc.next();
        System.out.print("New Status: ");
        String status = sc.next();
        System.out.print("New Postal Code: ");
        System.out.print("================== ");
        String pcode = sc.next();       

        String sql = "UPDATE citizen SET c_fname = ?, c_lname = ?, c_email = ?, c_status = ?, c_postalCode = ? WHERE c_id = ?";
        conf.updateRecord(sql, fname, lname, email, status, pcode, id);
    }
    private void deleteCitizen() {
        Scanner sc = new Scanner(System.in);
        config conf = new config();

        System.out.print("============================");
        System.out.print("Enter Citizen ID to Delete: ");
        System.out.print("============================");
        int id = sc.nextInt();

        String sql = "DELETE FROM citizen WHERE c_id = ?";
        conf.deleteRecord(sql, id);
    }
}
