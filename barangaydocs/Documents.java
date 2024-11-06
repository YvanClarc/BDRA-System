
package barangaydocs;

import java.util.InputMismatchException;
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

    int action = -1;
    boolean validAction = false;

    while (!validAction) {
        try {
            action = sc.nextInt();
            if (action >= 1 && action <= 5) {
                validAction = true;
            } else {
                System.out.println("Invalid action. Please enter a number between 1 and 5.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter an integer.");
            sc.next();
        }
    }

    Documents ahay = new Documents();

    switch(action) {
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
    }

    while (true) {
        System.out.println("Do you want to continue? (Y/N): ");
        response = sc.next();
        
        if (response.equalsIgnoreCase("y") || response.equalsIgnoreCase("n")) {
            break;
        } else {
            System.out.println("Invalid response. Please enter 'Y' or 'N'.");
        }
    }

} while (response.equalsIgnoreCase("y"));
      
    }
    
    public void addDocument() {
        config conf = new config();
        String dname;
        int citizenId;

        // Loop until a valid document name is provided
        while (true) {
            System.out.println("=========================================");
            System.out.println("Here are The Lists of Available document: "
                    + "\nbirth_certificate"
                    + "\nmarriage_certificate"
                    + "\nresidency_certificate"
                    + "\nbarangay_clearance"
                    + "\ncertification_of_good_moral_character"
                    + "\ndeath_certificate"
                    + "\nindigency_certificate"
                    + "\nbusiness_permit"
                    + "\ncommunity_tax_certificate"
                    + "\ntransfer_of ownership");
            System.out.println("=========================================");
            
            System.out.print("Document Name: ");
            dname = sc.next();

            // Validate the document name
            if (isValidDocument(dname)) {
                break; // Exit the loop if valid
            } else {
                System.out.println("Invalid document name. Please enter a valid document name.");
            }
        }

        System.out.print("Enter Citizen ID (to link document): ");
        citizenId = sc.nextInt();

        // Set a fee based on the document type
        double fee = getDocumentFee(dname);

        String sql = "INSERT INTO document (document_name, c_id, fee) VALUES (?, ?, ?)";
        conf.addRecord(sql, dname, citizenId, fee);
    }

    private boolean isValidDocument(String documentName) {
        // List of valid document names
        String[] validDocuments = {
            "birth_certificate",
            "marriage_certificate",
            "residency_certificate",
            "barangay_clearance",
            "certification_of_good_moral_character",
            "death_certificate",
            "indigency_certificate",
            "business_permit",
            "community_tax_certificate",
            "transfer_of_ownership"
        };

        for (String validDocument : validDocuments) {
            if (validDocument.equalsIgnoreCase(documentName)) {
                return true; // Document name is valid
            }
        }
        return false; // Document name is not valid
    }

    private double getDocumentFee(String documentName) {
        // Example fees based on document types
        switch (documentName.toLowerCase()) {
            case "birth_certificate":
                return 100.00;
            case "marriage_certificate":
                return 150.00;
            case "residency_certificate":
                return 50.00;
            case "barangay_clearance":
                return 75.00;
            case "certification_of_good_moral_character":
                return 120.00;
            case "death_certificate":
                return 100.00;
            case "indigency_certificate":
                return 30.00;
            case "business_permit":
                return 200.00;
            case "community_tax_certificate":
                return 40.00;
            case "transfer_of_ownership":
                return 150.00;
            default:
                return 0.00; // Default fee for unspecified document types
        }
    }
    
    private void viewDocument() {
    config conf = new config();
    
    String countQuery = "SELECT COUNT(*) FROM document";
    
    if (conf.getSingleValue(countQuery) == 0) {
        System.out.println("No documents found in the database.");
        return;
    }

    String BDRAQuery = "SELECT document.d_id, document.document_name, citizen.c_fname, citizen.c_lname " +
                       "FROM document " +
                       "JOIN citizen ON document.c_id = citizen.c_id";
    String[] BDRAHeaders = { "Document ID", "Document Name", "Citizen First Name", "Citizen Last Name" };
    String[] BDRAColumns = { "d_id", "document_name", "c_fname", "c_lname" };

    conf.viewRecords(BDRAQuery, BDRAHeaders, BDRAColumns);
}
    
    private void updateDocument() {
    Scanner sc = new Scanner(System.in);
    config conf = new config();
    String dname;
    int id = -1;
    int citizenId = -1;

    String countQuery = "SELECT COUNT(*) FROM document";
    if (conf.getSingleValue(countQuery) == 0) {
        System.out.println("No documents available to update.");
        return;
    }

    while (true) {
        System.out.print("Enter Document ID to Update: ");
        
        try {
            id = sc.nextInt();
            
            String dsql = "SELECT d_id FROM document WHERE d_id = ?";
            if (conf.getSingleValue(dsql, id) == 0) {
                System.out.println("Document ID does not exist. Please try again.");
            } else {
                break;
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a numeric Document ID.");
            sc.next();
        }
    }
    
    while (true) {
            System.out.println("=========================================");
            System.out.println("Here are The Lists of Available document: "
                    + "\nbirth_certificate"
                    + "\nmarriage_certificate"
                    + "\nresidency_certificate"
                    + "\nbarangay_clearance"
                    + "\ncertification_of_good_moral_character"
                    + "\ndeath_certificate"
                    + "\nindigency_certificate"
                    + "\nbusiness_permit"
                    + "\ncommunity_tax_certificate"
                    + "\ntransfer_of ownership");
            System.out.println("=========================================");
            
            System.out.print("Document Name: ");
            dname = sc.next();

            // Validate the document name
            if (isValidDocument(dname)) {
                break; // Exit the loop if valid
            } else {
                System.out.println("Invalid document name. Please enter a valid document name.");
            }
        }

    while (true) {
        System.out.print("Enter Citizen ID (to link document): ");
        
        try {
            citizenId = sc.nextInt();

            String csql = "SELECT c_id FROM citizen WHERE c_id = ?";
            if (conf.getSingleValue(csql, citizenId) == 0) {
                System.out.println("Citizen ID does not exist. Please try again.");
            } else {
                break;
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a numeric Citizen ID.");
            sc.next();
        }
    }

    String sql = "UPDATE document SET document_name = ?, c_id = ? WHERE d_id = ?";
    conf.updateRecord(sql, dname, citizenId, id);
}
    
    private void deleteDocument() {
    Scanner sc = new Scanner(System.in);
    config conf = new config();
    int id = -1;

    String countQuery = "SELECT COUNT(*) FROM document";
    if (conf.getSingleValue(countQuery) == 0) {
        System.out.println("No documents available to delete.");
        return;
    }

    while (true) {
        System.out.print("Enter Document ID to Delete: ");
        
        try {
            id = sc.nextInt();

            String dsql = "SELECT d_id FROM document WHERE d_id = ?";
            if (conf.getSingleValue(dsql, id) == 0) {
                System.out.println("Document ID does not exist. Please try again.");
            } else {
                break;
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a numeric Document ID.");
            sc.next();
        }
    }

    String sql = "DELETE FROM document WHERE d_id = ?";
    conf.deleteRecord(sql, id);
    System.out.println("Document with ID " + id + " has been deleted successfully.");
}
                
}
