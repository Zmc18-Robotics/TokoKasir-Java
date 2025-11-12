public class TestLogin {
    public static void main(String[] args) {
        System.out.println("===========================================");
        System.out.println("TEST LOGIN - Sistem Kasir Toko");
        System.out.println("===========================================\n");
        
        // Initialize database
        System.out.println("1. Initializing database...");
        DatabaseManager.initializeDatabase();
        System.out.println("\n2. Testing user authentication...\n");
        
        // Test cases
        testLogin("admin", "admin123");
        testLogin("kasir", "kasir123");
        testLogin("customer", "customer123");
        testLogin("admin", "wrongpassword");
        testLogin("unknownuser", "password");
        
        System.out.println("\n===========================================");
        System.out.println("TEST COMPLETE");
        System.out.println("===========================================");
    }
    
    private static void testLogin(String username, String password) {
        System.out.println("Testing: " + username + " / " + password);
        User user = DatabaseManager.authenticateUser(username, password);
        if (user != null) {
            System.out.println("✓ LOGIN SUCCESS - Role: " + user.getRole());
        } else {
            System.out.println("✗ LOGIN FAILED");
        }
        System.out.println();
    }
}
