import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentController implements AutoCloseable { 
    private static final String URL = "jdbc:mysql://localhost:3306/CollegeDB"; 
    private static final String USER = "root"; // Change if needed
    private static final String PASSWORD = "root"; // Change if needed
    private Connection conn;

    public StudentController() throws SQLException {
        conn = DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public void addStudent(Student student) throws SQLException {
        String sql = "INSERT INTO Students (StudentID, Name, Department, Marks) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) { 
            stmt.setInt(1, student.getId());
            stmt.setString(2, student.getName());
            stmt.setString(3, student.getDepartment());
            stmt.setDouble(4, student.getMarks());
            stmt.executeUpdate();
            System.out.println("Student Added Successfully!");
        }
    }

    public List<Student> getAllStudents() throws SQLException { 
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM Students";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) { 
            while (rs.next()) {
                students.add(new Student(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4)));
            }
        }
        return students;
    }

    public void updateStudent(int id, double marks) throws SQLException { 
        String sql = "UPDATE Students SET Marks=? WHERE StudentID=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDouble(1, marks);
            stmt.setInt(2, id);
            System.out.println(stmt.executeUpdate() > 0 ? "Student Updated Successfully!" : "Student Not Found!");
        }
    }

    public void deleteStudent(int id) throws SQLException {
        String sql = "DELETE FROM Students WHERE StudentID=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            System.out.println(stmt.executeUpdate() > 0 ? "Student Deleted Successfully!" : "Student Not Found!");
        }
    }

    @Override
    public void close() throws SQLException { 
        if (conn != null) conn.close();
    }
}
