public class QuickDiagnosis {
    public static void main(String[] args) {
        System.out.println("\n╔═══════════════════════════════════════════════════════════╗");
        System.out.println("║          QUICK DIAGNOSIS - Login Issue Root Cause          ║");
        System.out.println("╚═══════════════════════════════════════════════════════════╝\n");
        
        System.out.println("DIAGNOSIS SEQUENCE:");
        System.out.println("═════════════════════\n");
        
        // Check 1: Database Initialization
        System.out.println("[CHECK 1] Database Initialization");
        System.out.println("─────────────────────────────────");
        try {
            DatabaseManager.initializeDatabase();
            System.out.println("✓ Database initialized successfully\n");
        } catch (Exception e) {
            System.out.println("✗ FAILED: " + e.getMessage());
            e.printStackTrace();
            return;
        }
        
        // Check 2: User Authentication
        System.out.println("[CHECK 2] User Authentication");
        System.out.println("─────────────────────────────────");
        User testUser = DatabaseManager.authenticateUser("admin", "admin123");
        if (testUser != null) {
            System.out.println("✓ Authentication works");
            System.out.println("  Username: " + testUser.getUsername());
            System.out.println("  Role: " + testUser.getRole() + "\n");
        } else {
            System.out.println("✗ FAILED: Authentication returns null\n");
            return;
        }
        
        // Check 3: Try to simulate full login flow
        System.out.println("[CHECK 3] Full Login Flow Simulation");
        System.out.println("──────────────────────────────────────");
        
        String inputUsername = "admin";
        String inputPassword = "admin123";
        
        System.out.println("  Input username: '" + inputUsername + "'");
        System.out.println("  Input password: '" + inputPassword + "'");
        System.out.println("  Calling: DatabaseManager.authenticateUser(...)");
        
        User user = DatabaseManager.authenticateUser(inputUsername, inputPassword);
        
        if (user != null) {
            System.out.println("  ✓ Result: User object returned");
            System.out.println("    - ID: " + user.getId());
            System.out.println("    - Username: " + user.getUsername());
            System.out.println("    - Role: " + user.getRole());
        } else {
            System.out.println("  ✗ Result: NULL");
        }
        System.out.println();
        
        // Check 4: Database content
        System.out.println("[CHECK 4] Database Content Verification");
        System.out.println("─────────────────────────────────────────");
        try {
            java.sql.Connection conn = DatabaseManager.getConnection();
            java.sql.Statement stmt = conn.createStatement();
            java.sql.ResultSet rs = stmt.executeQuery("SELECT COUNT(*) as cnt FROM users");
            
            if (rs.next()) {
                int count = rs.getInt("cnt");
                System.out.println("✓ Total users in database: " + count);
            }
            
            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            System.out.println("✗ ERROR: " + e.getMessage());
        }
        System.out.println();
        
        // Summary
        System.out.println("╔═══════════════════════════════════════════════════════════╗");
        System.out.println("║                     DIAGNOSIS SUMMARY                      ║");
        System.out.println("╚═══════════════════════════════════════════════════════════╝\n");
        
        System.out.println("IF YOU SEE:");
        System.out.println("✓ All checks passed");
        System.out.println("→ THEN: Database & Authentication are WORKING");
        System.out.println("→ PROBLEM IS IN: UI/GUI layer or Frame handling\n");
        
        System.out.println("IF YOU SEE:");
        System.out.println("✗ Any check failed");
        System.out.println("→ THEN: Check the error message above\n");
    }
}
