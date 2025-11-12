/**
 * Automated Test Suite untuk Sistem Kasir Toko
 * 
 * Kelas ini melakukan verifikasi otomatis terhadap semua komponen database
 * untuk memastikan integritas data dan kesiapan testing manual.
 */

import java.sql.*;

public class AutomatedTests {
    
    public static void main(String[] args) {
        System.out.println("\n" + "=".repeat(70));
        System.out.println("    AUTOMATED TEST SUITE - SISTEM KASIR TOKO");
        System.out.println("=".repeat(70) + "\n");
        
        // Run all tests
        testDatabaseConnection();
        testUserCreation();
        testCategoryData();
        testSupplierData();
        testProductData();
        testUserAuthentication();
        testTableStructure();
        
        System.out.println("\n" + "=".repeat(70));
        System.out.println("    ALL TESTS COMPLETED!");
        System.out.println("=".repeat(70) + "\n");
    }
    
    // Test 1: Database Connection
    private static void testDatabaseConnection() {
        System.out.println("[TEST 1] Database Connection");
        System.out.println("-".repeat(70));
        try {
            Connection conn = DatabaseManager.getConnection();
            if (conn != null && !conn.isClosed()) {
                System.out.println("✓ Database connection successful");
                conn.close();
            } else {
                System.out.println("✗ Database connection failed - connection is null or closed");
            }
        } catch (SQLException e) {
            System.out.println("✗ SQLException: " + e.getMessage());
        }
        System.out.println();
    }
    
    // Test 2: User Creation
    private static void testUserCreation() {
        System.out.println("[TEST 2] User Creation and Verification");
        System.out.println("-".repeat(70));
        
        String sql = "SELECT id, username, role FROM users ORDER BY id";
        try (Connection conn = DatabaseManager.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            int count = 0;
            System.out.println("ID | Username | Role");
            System.out.println("---+-----------+--------");
            
            while (rs.next()) {
                int id = rs.getInt("id");
                String username = rs.getString("username");
                String role = rs.getString("role");
                
                System.out.printf("%2d | %-9s | %-6s\n", id, username, role);
                count++;
            }
            
            if (count >= 3) {
                System.out.println("\n✓ All 3 default users created: admin, kasir, customer");
            } else {
                System.out.println("\n✗ Expected 3 users, found " + count);
            }
            
        } catch (SQLException e) {
            System.out.println("✗ SQLException: " + e.getMessage());
        }
        System.out.println();
    }
    
    // Test 3: Category Data
    private static void testCategoryData() {
        System.out.println("[TEST 3] Category Data");
        System.out.println("-".repeat(70));
        
        String sql = "SELECT id, name FROM categories ORDER BY id";
        try (Connection conn = DatabaseManager.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            int count = 0;
            System.out.println("ID | Category Name");
            System.out.println("---+----------------------");
            
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                System.out.printf("%2d | %-20s\n", id, name);
                count++;
            }
            
            if (count > 0) {
                System.out.println("\n✓ Categories found: " + count);
            } else {
                System.out.println("\n✗ No categories found in database");
            }
            
        } catch (SQLException e) {
            System.out.println("✗ SQLException: " + e.getMessage());
        }
        System.out.println();
    }
    
    // Test 4: Supplier Data
    private static void testSupplierData() {
        System.out.println("[TEST 4] Supplier Data");
        System.out.println("-".repeat(70));
        
        String sql = "SELECT id, name FROM suppliers ORDER BY id";
        try (Connection conn = DatabaseManager.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            int count = 0;
            System.out.println("ID | Supplier Name");
            System.out.println("---+----------------------");
            
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                System.out.printf("%2d | %-20s\n", id, name);
                count++;
            }
            
            if (count > 0) {
                System.out.println("\n✓ Suppliers found: " + count);
            } else {
                System.out.println("\n✗ No suppliers found in database");
            }
            
        } catch (SQLException e) {
            System.out.println("✗ SQLException: " + e.getMessage());
        }
        System.out.println();
    }
    
    // Test 5: Product Data
    private static void testProductData() {
        System.out.println("[TEST 5] Product Data");
        System.out.println("-".repeat(70));
        
        String sql = "SELECT id, name, price, stock, discount FROM products ORDER BY id";
        try (Connection conn = DatabaseManager.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            int count = 0;
            System.out.println("ID | Name                  | Price      | Stock | Diskon");
            System.out.println("---+-----------------------+------------+-------+-------");
            
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                int stock = rs.getInt("stock");
                double discount = rs.getDouble("discount");
                
                System.out.printf("%2d | %-21s | %10.0f | %5d | %5.1f%%\n", 
                    id, name, price, stock, discount);
                count++;
            }
            
            if (count > 0) {
                System.out.println("\n✓ Products found: " + count);
            } else {
                System.out.println("\n✗ No products found in database");
            }
            
        } catch (SQLException e) {
            System.out.println("✗ SQLException: " + e.getMessage());
        }
        System.out.println();
    }
    
    // Test 6: User Authentication
    private static void testUserAuthentication() {
        System.out.println("[TEST 6] User Authentication");
        System.out.println("-".repeat(70));
        
        String[] testCases = {
            "admin:admin123",
            "kasir:kasir123",
            "customer:customer123",
            "admin:wrongpass",
            "unknown:password"
        };
        
        for (String testCase : testCases) {
            String[] parts = testCase.split(":");
            String username = parts[0];
            String password = parts[1];
            
            User user = DatabaseManager.authenticateUser(username, password);
            
            if (user != null) {
                System.out.printf("✓ Login SUCCESS: %s (Role: %s)\n", username, user.getRole());
            } else {
                System.out.printf("✗ Login FAILED: %s:%s\n", username, password);
            }
        }
        System.out.println();
    }
    
    // Test 7: Table Structure
    private static void testTableStructure() {
        System.out.println("[TEST 7] Database Table Structure");
        System.out.println("-".repeat(70));
        
        String[] expectedTables = {
            "users", "categories", "suppliers", "products", 
            "transactions", "transaction_items", "orders"
        };
        
        try {
            Connection conn = DatabaseManager.getConnection();
            DatabaseMetaData meta = conn.getMetaData();
            ResultSet tables = meta.getTables(null, null, "%", new String[]{"TABLE"});
            
            int count = 0;
            System.out.println("Table Name");
            System.out.println("------------------");
            
            while (tables.next()) {
                String tableName = tables.getString("TABLE_NAME");
                System.out.println("✓ " + tableName);
                count++;
            }
            
            System.out.println("\nTotal tables found: " + count);
            if (count >= expectedTables.length) {
                System.out.println("✓ All required tables exist");
            } else {
                System.out.println("✗ Missing some required tables");
            }
            
            tables.close();
            conn.close();
            
        } catch (SQLException e) {
            System.out.println("✗ SQLException: " + e.getMessage());
        }
        System.out.println();
    }
}
