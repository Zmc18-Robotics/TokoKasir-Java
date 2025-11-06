import java.sql.*;

public class CheckUsers {
    public static void main(String[] args) {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        
        String DB_URL = "jdbc:sqlite:tokokasir.db";
        
        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT id, username, role FROM users")) {
            
            System.out.println("=== DAFTAR USER DI DATABASE ===");
            System.out.println("ID | Username | Role");
            System.out.println("--------------------------------");
            
            while (rs.next()) {
                System.out.println(rs.getInt("id") + " | " + 
                                 rs.getString("username") + " | " + 
                                 rs.getString("role"));
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
