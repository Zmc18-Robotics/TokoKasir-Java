import java.sql.*;

public class InitializeData {
    public static void initializeSampleData() {
        try (Connection conn = DatabaseManager.getConnection();
             Statement stmt = conn.createStatement()) {
            
            // Check and insert sample categories
            String checkCategories = "SELECT COUNT(*) as cnt FROM categories";
            ResultSet rs = stmt.executeQuery(checkCategories);
            if (rs.next() && rs.getInt("cnt") == 0) {
                stmt.execute("INSERT INTO categories (name, description) VALUES ('Elektronik', 'Barang elektronik dan gadget')");
                stmt.execute("INSERT INTO categories (name, description) VALUES ('Pakaian', 'Pakaian dan aksesoris')");
                stmt.execute("INSERT INTO categories (name, description) VALUES ('Makanan', 'Makanan dan minuman')");
                stmt.execute("INSERT INTO categories (name, description) VALUES ('Buku', 'Buku dan media')");
                System.out.println("DEBUG: Sample categories berhasil dibuat");
            }
            
            // Check and insert sample suppliers
            String checkSuppliers = "SELECT COUNT(*) as cnt FROM suppliers";
            rs = stmt.executeQuery(checkSuppliers);
            if (rs.next() && rs.getInt("cnt") == 0) {
                stmt.execute("INSERT INTO suppliers (name, phone, address) VALUES ('Supplier A', '08123456789', 'Jl. Merdeka No. 1, Jakarta')");
                stmt.execute("INSERT INTO suppliers (name, phone, address) VALUES ('Supplier B', '08234567890', 'Jl. Ahmad Yani No. 2, Bandung')");
                stmt.execute("INSERT INTO suppliers (name, phone, address) VALUES ('Supplier C', '08345678901', 'Jl. Diponegoro No. 3, Surabaya')");
                System.out.println("DEBUG: Sample suppliers berhasil dibuat");
            }
            
            // Check and insert sample products
            String checkProducts = "SELECT COUNT(*) as cnt FROM products";
            rs = stmt.executeQuery(checkProducts);
            if (rs.next() && rs.getInt("cnt") == 0) {
                stmt.execute("INSERT INTO products (name, category_id, supplier_id, price, stock, discount, description) " +
                            "VALUES ('Laptop Dell', 1, 1, 5000000, 10, 5, 'Laptop gaming performa tinggi')");
                stmt.execute("INSERT INTO products (name, category_id, supplier_id, price, stock, discount, description) " +
                            "VALUES ('Mouse Logitech', 1, 1, 150000, 50, 0, 'Mouse wireless ergonomis')");
                stmt.execute("INSERT INTO products (name, category_id, supplier_id, price, stock, discount, description) " +
                            "VALUES ('T-Shirt Polos', 2, 2, 50000, 100, 10, 'T-shirt polos 100% katun')");
                stmt.execute("INSERT INTO products (name, category_id, supplier_id, price, stock, discount, description) " +
                            "VALUES ('Celana Jeans', 2, 2, 150000, 50, 15, 'Celana jeans kualitas premium')");
                stmt.execute("INSERT INTO products (name, category_id, supplier_id, price, stock, discount, description) " +
                            "VALUES ('Kopi Nescafe', 3, 3, 25000, 200, 0, 'Kopi instan kemasan box')");
                stmt.execute("INSERT INTO products (name, category_id, supplier_id, price, stock, discount, description) " +
                            "VALUES ('Buku Pemrograman Java', 4, 1, 85000, 30, 5, 'Panduan lengkap belajar Java')");
                System.out.println("DEBUG: Sample products berhasil dibuat");
            }
            
        } catch (SQLException e) {
            System.out.println("DEBUG: Error di initializeSampleData: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
