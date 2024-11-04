
package barangaydocs;

import java.util.Scanner;

public class Documents {
    
    Scanner sc = new Scanner(System.in);
    String response;
    
    public void getDocuments(){
                        
        do { 
            System.out.println(" ================== ");
            System.out.println("|  Adding Document |");
            System.out.println(" ================== ");
            System.out.println("1. ADD Document    |");
            System.out.println("2. VIEW Document   |");
            System.out.println("3. UPDATE Document |");
            System.out.println("4. DELETE Document |");
            System.out.println("5. EXIT            |");
            System.out.println("Enter Action:      |");
            System.out.println("------------------- ");
            int action = sc.nextInt();
            Documents ahay = new Documents();
            
            switch(action){
                case 1:
                        ahay.addDocument();
                    break;
                case 2:
                        ahay.viewDocument();
                    break;    
               case 3:
                        ahay.updateDocument();
                    break;     
               case 4:
                        ahay.deleteDocument();
                    break;
               case 5:
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
    
    public void addDocument(){
        Scanner sc = new Scanner(System.in);
        config conf = new config();
        System.out.println("=======================");
        System.out.print("Document Name: ");
        String dname = sc.next();       
        
        String sql = "INSERT INTO document (document_name) VALUES (?)";


        conf.addRecord( sql, dname);
        
    }
    private void viewDocument() {
        config conf = new config();
        String BDRAQuery = "SELECT * FROM document";
        String[] BDRAHeaders = { "ID","Document Name"};
        String[] BDRAColumns = {"d_id" ,"document_name"};

        conf.viewRecords(BDRAQuery, BDRAHeaders, BDRAColumns);
    }
    private void updateDocument() {
        Scanner sc = new Scanner(System.in);
        config conf = new config();

        System.out.print("Enter Citizen ID to Update: ");
        int id = sc.nextInt();

        System.out.print("===================");
        System.out.print("New Document Name: ");
        String dname = sc.next();
        

        String sql = "UPDATE document SET document_name = ? WHERE d_id = ?";
        conf.updateRecord(sql, dname, id);
    }
    private void deleteDocument() {
        Scanner sc = new Scanner(System.in);
        config conf = new config();

        System.out.print("============================");
        System.out.print("Enter Document ID to Delete: ");
        System.out.print("============================");
        int id = sc.nextInt();

        String sql = "DELETE FROM document WHERE d_id = ?";
        conf.deleteRecord(sql, id);
    }
        
        
}
