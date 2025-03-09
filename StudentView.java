import java.sql.SQLException;
import java.util.Scanner;

public class StudentView {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in); 
             StudentController controller = new StudentController()) {

            while (true) {
                System.out.println("\n1. Add Student");
                System.out.println("2. View Students");
                System.out.println("3. Update Student Marks");
                System.out.println("4. Delete Student");
                System.out.println("5. Exit");
                System.out.print("Enter your choice: ");

                int choice = scanner.nextInt();
                switch (choice) {
                    case 1 -> {
                        System.out.print("ID: ");
                        int id = scanner.nextInt();
                        scanner.nextLine(); // Consume newline

                        System.out.print("Name: ");
                        String name = scanner.nextLine();

                        System.out.print("Department: ");
                        String dept = scanner.nextLine();

                        System.out.print("Marks: ");
                        double marks = scanner.nextDouble();

                        controller.addStudent(new Student(id, name, dept, marks));
                    }

                    case 2 -> {
                        System.out.println("\nStudent List:");
                        controller.getAllStudents().forEach(s -> 
                            System.out.println(s.getId() + " | " + s.getName() + " | " 
                                               + s.getDepartment() + " | " + s.getMarks()));
                    }

                    case 3 -> {
                        System.out.print("Enter Student ID to update: ");
                        int id = scanner.nextInt();
                        System.out.print("Enter New Marks: ");
                        double marks = scanner.nextDouble();
                        controller.updateStudent(id, marks);
                    }

                    case 4 -> {
                        System.out.print("Enter Student ID to delete: ");
                        int id = scanner.nextInt();
                        controller.deleteStudent(id);
                    }

                    case 5 -> {
                        System.out.println("Exiting...");
                        System.exit(0);
                    }

                    default -> System.out.println("Invalid choice! Please try again.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
