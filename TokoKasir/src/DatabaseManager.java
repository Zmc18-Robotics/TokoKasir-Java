import java.sql.*;
import java.util.*;
import java.util.Date;
import java.text.SimpleDateFormat;

public class DatabaseManager {
    private static final String DB_URL = "jdbc:sqlite:tokokasir.db";
    
    public static void initializeDatabase() {
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            createTables(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private static void createTables(Connection conn) throws SQLException {
        String[] tables = {
            // Users table
            "CREATE TABLE IF NOT EXISTS users (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "username TEXT UNIQUE NOT NULL," +
            "password TEXT NOT NULL," +
            "role TEXT NOT NULL," +
            "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP)",
            
            // Categories table
            "CREATE TABLE IF NOT EXISTS categories (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "name TEXT UNIQUE NOT NULL," +
            "description TEXT)",
            
            // Suppliers table
            "CREATE TABLE IF NOT EXISTS suppliers (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "name TEXT UNIQUE NOT NULL," +
            "phone TEXT," +
            "address TEXT)",
            
            // Products table
            "CREATE TABLE IF NOT EXISTS products (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "name TEXT NOT NULL," +
            "category_id INTEGER NOT NULL," +
            "supplier_id INTEGER NOT NULL," +
            "price REAL NOT NULL," +
            "stock INTEGER NOT NULL," +
            "discount REAL DEFAULT 0," +
            "description TEXT," +
            "image_path TEXT," +
            "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP," +
            "FOREIGN KEY(category_id) REFERENCES categories(id)," +
            "FOREIGN KEY(supplier_id) REFERENCES suppliers(id))",
            
            // Transactions table
            "CREATE TABLE IF NOT EXISTS transactions (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "user_id INTEGER NOT NULL," +
            "total_amount REAL NOT NULL," +
            "payment_method TEXT," +
            "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP," +
            "FOREIGN KEY(user_id) REFERENCES users(id))",
            
            // Transaction items table
            "CREATE TABLE IF NOT EXISTS transaction_items (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "transaction_id INTEGER NOT NULL," +
            "product_id INTEGER NOT NULL," +
            "quantity INTEGER NOT NULL," +
            "price REAL NOT NULL," +
            "discount REAL DEFAULT 0," +
            "subtotal REAL NOT NULL," +
            "FOREIGN KEY(transaction_id) REFERENCES transactions(id)," +
            "FOREIGN KEY(product_id) REFERENCES products(id))",
            
            // Orders table (for customer orders)
            "CREATE TABLE IF NOT EXISTS orders (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "customer_id INTEGER NOT NULL," +
            "product_id INTEGER NOT NULL," +
            "quantity INTEGER NOT NULL," +
            "price REAL NOT NULL," +
            "discount REAL DEFAULT 0," +
            "subtotal REAL NOT NULL," +
            "status TEXT DEFAULT 'PENDING'," +
            "notes TEXT," +
            "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP," +
            "FOREIGN KEY(customer_id) REFERENCES users(id)," +
            "FOREIGN KEY(product_id) REFERENCES products(id))"
        };
        
        try (Statement stmt = conn.createStatement()) {
            for (String sql : tables) {
                stmt.execute(sql);
            }
            
            // Insert default admin user if not exists
            String checkAdmin = "SELECT COUNT(*) FROM users WHERE username = 'admin'";
            ResultSet rs = stmt.executeQuery(checkAdmin);
            if (rs.next() && rs.getInt(1) == 0) {
                String insertAdmin = "INSERT INTO users (username, password, role) VALUES ('admin', 'admin123', 'ADMIN')";
                stmt.execute(insertAdmin);
            }
            
            // Insert default customer user if not exists
            String checkCustomer = "SELECT COUNT(*) FROM users WHERE username = 'customer'";
            rs = stmt.executeQuery(checkCustomer);
            if (rs.next() && rs.getInt(1) == 0) {
                String insertCustomer = "INSERT INTO users (username, password, role) VALUES ('customer', 'customer123', 'CUSTOMER')";
                stmt.execute(insertCustomer);
            }
        }
    }
    
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }
    
    public static User authenticateUser(String username, String password) {
        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return new User(
                    rs.getInt("id"),
                    rs.getString("username"),
                    rs.getString("role")
                );
            } else {
                // Debug: Check if user exists at all
                String checkSql = "SELECT * FROM users WHERE username = ?";
                try (PreparedStatement checkStmt = conn.prepareStatement(checkSql)) {
                    checkStmt.setString(1, username);
                    ResultSet checkRs = checkStmt.executeQuery();
                    if (!checkRs.next()) {
                        System.out.println("DEBUG: User '" + username + "' tidak ditemukan di database");
                    } else {
                        System.out.println("DEBUG: User '" + username + "' ditemukan tapi password salah");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public static void ensureAdminExists() {
        String sql = "SELECT COUNT(*) FROM users WHERE username = 'admin'";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next() && rs.getInt(1) == 0) {
                String insertAdmin = "INSERT INTO users (username, password, role) VALUES ('admin', 'admin123', 'ADMIN')";
                stmt.execute(insertAdmin);
                System.out.println("DEBUG: Admin user berhasil dibuat");
            } else {
                System.out.println("DEBUG: Admin user sudah ada");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static void backupDatabase() {
        try {
            String timestamp = new java.text.SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String backupFile = "tokokasir_backup_" + timestamp + ".db";
            
            Process process = Runtime.getRuntime().exec(new String[]{
                "cmd.exe", "/c", "copy tokokasir.db " + backupFile
            });
            process.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
