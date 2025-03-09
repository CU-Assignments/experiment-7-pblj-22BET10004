import java.sql.*;

public class FetchEmployeeData {
    public static void main(String[] args) {
        // Database credentials
        String url = "jdbc:mysql://localhost:3306/CompanyDB"; 
        String user = "root"; 
        String password = "root";
        String query = "SELECT EmpID, Name, Salary FROM Employee";

        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish connection
            Connection conn = DriverManager.getConnection(url, user, password);

            // Create a statement
            Statement stmt = conn.createStatement();

            // Execute query
            ResultSet rs = stmt.executeQuery(query);

            // Process results
            while (rs.next()) {
                int empID = rs.getInt("EmpID");
                String name = rs.getString("Name");
                double salary = rs.getDouble("Salary");

                System.out.println("EmpID: " + empID + ", Name: " + name + ", Salary: " + salary);
            }

            // Close resources
            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
