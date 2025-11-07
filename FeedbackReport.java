import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.*;
import java.util.*;

public class FeedbackReport {

    // Faculty report - show only for specific faculty
    public static void generateFacultyReport(String facultyName) {
        try {
            File xmlFile = new File("feedbacks.xml");
            if (!xmlFile.exists()) {
                System.out.println(" No feedback data found yet!");
                return;
            }

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();

            NodeList feedbackList = doc.getElementsByTagName("feedback");
            int total = 0, count = 0;

            System.out.println("\n Feedback Report for Faculty: " + facultyName);
            System.out.println("-----------------------------------------");

            for (int i = 0; i < feedbackList.getLength(); i++) {
                Element e = (Element) feedbackList.item(i);
                String faculty = e.getElementsByTagName("faculty").item(0).getTextContent();

                if (faculty.equalsIgnoreCase(facultyName)) {
                    String course = e.getElementsByTagName("course").item(0).getTextContent();
                    int rating = Integer.parseInt(e.getElementsByTagName("rating").item(0).getTextContent());
                    String comment = e.getElementsByTagName("comment").item(0).getTextContent();

                    System.out.println("Course: " + course);
                    System.out.println("Rating: " + rating);
                    System.out.println("Comment: " + comment);
                    System.out.println("------------------------------------");

                    total += rating;
                    count++;
                }
            }

            if (count > 0) {
                double avg = (double) total / count;
                System.out.println(" Total Feedbacks: " + count);
                System.out.println(" Average Rating: " + String.format("%.2f", avg));
            } else {
                System.out.println(" No feedback found for this faculty.");
            }

        } catch (Exception e) {
            System.out.println(" Error generating report: " + e.getMessage());
        }
    }

    // Admin report - show average rating of all faculties
    public static void generateAdminReport() {
        try {
            File xmlFile = new File("feedbacks.xml");
            if (!xmlFile.exists()) {
                System.out.println(" No feedback data found yet!");
                return;
            }

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();

            NodeList feedbackList = doc.getElementsByTagName("feedback");

            Map<String, List<Integer>> facultyRatings = new HashMap<>();

            for (int i = 0; i < feedbackList.getLength(); i++) {
                Element e = (Element) feedbackList.item(i);
                String faculty = e.getElementsByTagName("faculty").item(0).getTextContent();
                int rating = Integer.parseInt(e.getElementsByTagName("rating").item(0).getTextContent());

                facultyRatings.putIfAbsent(faculty, new ArrayList<>());
                facultyRatings.get(faculty).add(rating);
            }

            System.out.println("\n Institution-Wide Feedback Summary:");
            System.out.println("------------------------------------");

            for (String faculty : facultyRatings.keySet()) {
                List<Integer> ratings = facultyRatings.get(faculty);
                double avg = ratings.stream().mapToInt(Integer::intValue).average().orElse(0.0);
                System.out.println("Faculty: " + faculty + " | Average Rating: " + String.format("%.2f", avg));
            }

        } catch (Exception e) {
            System.out.println(" Error generating admin report: " + e.getMessage());
        }
    }
}
