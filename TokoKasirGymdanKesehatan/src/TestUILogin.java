import javax.swing.*;

public class TestUILogin {
    public static void main(String[] args) {
        System.out.println("\n╔══════════════════════════════════════════════════════════╗");
        System.out.println("║       TEST UI LOGIN - Sistem Kasir Toko                   ║");
        System.out.println("╚══════════════════════════════════════════════════════════╝\n");
        
        // Initialize database
        System.out.println("[1] Initializing database...");
        try {
            DatabaseManager.initializeDatabase();
            System.out.println("✓ Database initialized\n");
        } catch (Exception e) {
            System.out.println("✗ ERROR: " + e.getMessage());
            e.printStackTrace();
            return;
        }
        
        // Test authentication like LoginPanel does
        System.out.println("[2] Testing authentication (simulating LoginPanel)...");
        String testUsername = "admin";
        String testPassword = "admin123";
        
        System.out.println("  Input username: " + testUsername);
        System.out.println("  Input password: " + testPassword);
        
        User user = DatabaseManager.authenticateUser(testUsername, testPassword);
        
        if (user != null) {
            System.out.println("  ✓ Authentication SUCCESS");
            System.out.println("    - User ID: " + user.getId());
            System.out.println("    - Username: " + user.getUsername());
            System.out.println("    - Role: " + user.getRole() + "\n");
            
            // Try to create MainPanel
            System.out.println("[3] Testing MainPanel creation...");
            try {
                SwingUtilities.invokeLater(() -> {
                    try {
                        JFrame testFrame = new JFrame("Test");
                        testFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        testFrame.setSize(1000, 700);
                        
                        System.out.println("  ✓ Creating MainPanel with user: " + user.getUsername());
                        MainPanel mainPanel = new MainPanel(testFrame, user);
                        testFrame.setContentPane(mainPanel);
                        testFrame.setLocationRelativeTo(null);
                        testFrame.setVisible(true);
                        
                        System.out.println("  ✓ MainPanel created and displayed");
                        System.out.println("  ✓ UI LOGIN TEST PASSED!\n");
                        
                        // Auto close after 3 seconds
                        javax.swing.Timer timer = new javax.swing.Timer(3000, e -> {
                            System.out.println("[4] Test Complete - Closing window...");
                            testFrame.dispose();
                        });
                        timer.setRepeats(false);
                        timer.start();
                        
                    } catch (Exception e) {
                        System.out.println("  ✗ ERROR creating MainPanel: " + e.getMessage());
                        e.printStackTrace();
                    }
                });
            } catch (Exception e) {
                System.out.println("  ✗ ERROR: " + e.getMessage());
                e.printStackTrace();
            }
        } else {
            System.out.println("  ✗ Authentication FAILED");
        }
        
        System.out.println("\n╔══════════════════════════════════════════════════════════╗");
        System.out.println("║                    TEST COMPLETE                         ║");
        System.out.println("╚══════════════════════════════════════════════════════════╝\n");
        
        // Keep thread alive for Swing
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
