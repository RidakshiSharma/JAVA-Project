import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FeedbackHandler {

    public static void giveFeedback() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Course Name: ");
        String course = sc.nextLine();

        System.out.print("Enter Faculty Name: ");
        String faculty = sc.nextLine();

        System.out.print("Rate the Faculty (1-5): ");
        int rating = sc.nextInt();
        sc.nextLine(); // consume newline

        System.out.print("Enter your comments: ");
        String comment = sc.nextLine();

        // XML structure
        String feedbackXML = "<feedback>\n" +
                "   <course>" + course + "</course>\n" +
                "   <faculty>" + faculty + "</faculty>\n" +
                "   <rating>" + rating + "</rating>\n" +
                "   <comment>" + comment + "</comment>\n" +
                "</feedback>\n";

        try {
            // Save to XML file (append mode)
            FileWriter fw = new FileWriter("feedbacks.xml", true);
            fw.write(feedbackXML);
            fw.close();
            System.out.println("\n Feedback saved successfully in XML file!");
        } catch (IOException e) {
            System.out.println(" Error while saving feedback: " + e.getMessage());
        }
    }
}


