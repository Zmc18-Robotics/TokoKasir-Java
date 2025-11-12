public class DebugLogin {
    public static void main(String[] args) {
        System.out.println("\n╔══════════════════════════════════════════════════════════╗");
        System.out.println("║       DEBUG LOGIN - Sistem Kasir Toko                    ║");
        System.out.println("╚══════════════════════════════════════════════════════════╝\n");
        
        // Step 1: Initialize database
        System.out.println("STEP 1: Initialize Database");
        System.out.println("─────────────────────────────");
        try {
            DatabaseManager.initializeDatabase();
            System.out.println("✓ Database initialized\n");
        } catch (Exception e) {
            System.out.println("✗ ERROR: " + e.getMessage());
            e.printStackTrace();
            return;
        }
        
        // Step 2: Check users in database
        System.out.println("STEP 2: Verify Users in Database");
        System.out.println("────────────────────────────────");
        checkUsersInDatabase();
        System.out.println();
        
        // Step 3: Test authentication manually
        System.out.println("STEP 3: Test Authentication");
        System.out.println("──────────────────────────");
        testAuthentication("admin", "admin123");
        testAuthentication("kasir", "kasir123");
        testAuthentication("customer", "customer123");
        testAuthentication("admin", "wrongpass");
        System.out.println();
        
        System.out.println("╔══════════════════════════════════════════════════════════╗");
        System.out.println("║                    DEBUG COMPLETE                        ║");
        System.out.println("╚══════════════════════════════════════════════════════════╝\n");
    }
    
    private static void checkUsersInDatabase() {
        try {
            java.sql.Connection conn = DatabaseManager.getConnection();
            java.sql.Statement stmt = conn.createStatement();
            java.sql.ResultSet rs = stmt.executeQuery("SELECT id, username, password, role FROM users ORDER BY id");
            
            System.out.println("Users in database:");
            System.out.println("┌────┬──────────┬──────────────┬──────────┐");
            System.out.println("│ ID │ Username │ Password     │ Role     │");
            System.out.println("├────┼──────────┼──────────────┼──────────┤");
            
            boolean hasUsers = false;
            while (rs.next()) {
                hasUsers = true;
                System.out.printf("│ %2d │ %-8s │ %-12s │ %-8s │\n",
                    rs.getInt("id"),
                    rs.getString("username"),
                    rs.getString("password"),
                    rs.getString("role")
                );
            }
            
            if (!hasUsers) {
                System.out.println("│    │ NO USERS │              │          │");
            }
            
            System.out.println("└────┴──────────┴──────────────┴──────────┘");
            
            stmt.close();
            conn.close();
        } catch (Exception e) {
            System.out.println("✗ ERROR checking users: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    private static void testAuthentication(String username, String password) {
        System.out.println("Testing: " + username + " / " + password);
        
        User user = DatabaseManager.authenticateUser(username, password);
        
        if (user != null) {
            System.out.println("  ✓ LOGIN SUCCESS");
            System.out.println("    - ID: " + user.getId());
            System.out.println("    - Username: " + user.getUsername());
            System.out.println("    - Role: " + user.getRole());
        } else {
            System.out.println("  ✗ LOGIN FAILED");
        }
        System.out.println();
    }
}
