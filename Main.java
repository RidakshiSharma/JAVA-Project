import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;

        System.out.println("======================================");
        System.out.println("     Feedback System with Reports");
        System.out.println("======================================");

        do {
            System.out.println("\nChoose your role:");
            System.out.println("1. Student");
            System.out.println("2. Faculty");
            System.out.println("3. Admin");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("\n Student Section Selected");
                    studentMenu();
                    break;

                case 2:
                    System.out.println("\nFaculty Section Selected");
                    facultyMenu();
                    break;

                case 3:
                    System.out.println("\nAdmin Section Selected");
                    adminMenu();
                    break;

                case 4:
                    System.out.println("\nThank you for using Feedback System!");
                    break;

                default:
                    System.out.println("Invalid choice! Try again.");
            }

        } while (choice != 4);

        sc.close();
    }

    public static void studentMenu() {
    System.out.println("-> Please fill out the feedback form below:");
    FeedbackHandler.giveFeedback();
    }

    public static void facultyMenu() {
    Scanner sc = new Scanner(System.in);
    System.out.print("Enter your faculty name: ");
    String name = sc.nextLine();
    FeedbackReport.generateFacultyReport(name);
}

public static void adminMenu() {
    FeedbackReport.generateAdminReport();
}
}
