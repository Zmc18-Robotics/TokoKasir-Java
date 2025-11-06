import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.sql.*;

public class ProductPanel extends JPanel {
    private DefaultTableModel tableModel;
    private JTable table;
    
    public ProductPanel() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Title
        JLabel titleLabel = new JLabel("Kelola Produk");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(titleLabel, BorderLayout.NORTH);
        
        // Table
        String[] columns = {"ID", "Nama", "Kategori", "Supplier", "Harga", "Stok", "Diskon %", "Deskripsi"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        table = new JTable(tableModel);
        table.setRowHeight(25);
        table.setFont(new Font("Arial", Font.PLAIN, 12));
        
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
        
        // Buttons panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        
        JButton addButton = new JButton("Tambah Produk");
        addButton.setFont(new Font("Arial", Font.PLAIN, 12));
        addButton.addActionListener(e -> showAddDialog());
        buttonPanel.add(addButton);
        
        JButton editButton = new JButton("Edit Produk");
        editButton.setFont(new Font("Arial", Font.PLAIN, 12));
        editButton.addActionListener(e -> showEditDialog());
        buttonPanel.add(editButton);
        
        JButton deleteButton = new JButton("Hapus Produk");
        deleteButton.setFont(new Font("Arial", Font.PLAIN, 12));
        deleteButton.setBackground(new Color(204, 0, 0));
        deleteButton.setForeground(Color.WHITE);
        deleteButton.addActionListener(e -> deleteProduct());
        buttonPanel.add(deleteButton);
        
        JButton refreshButton = new JButton("Refresh");
        refreshButton.setFont(new Font("Arial", Font.PLAIN, 12));
        refreshButton.addActionListener(e -> loadData());
        buttonPanel.add(refreshButton);
        
        add(buttonPanel, BorderLayout.SOUTH);
        
        loadData();
    }
    
    private void loadData() {
        String sql = "SELECT p.id, p.name, c.name as category, s.name as supplier, p.price, p.stock, p.discount, p.description " +
                     "FROM products p " +
                     "LEFT JOIN categories c ON p.category_id = c.id " +
                     "LEFT JOIN suppliers s ON p.supplier_id = s.id " +
                     "ORDER BY p.name";
        
        tableModel.setRowCount(0);
        
        try (Connection conn = DatabaseManager.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                tableModel.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("category"),
                    rs.getString("supplier"),
                    "Rp " + String.format("%.0f", rs.getDouble("price")),
                    rs.getInt("stock"),
                    rs.getDouble("discount") + "%",
                    rs.getString("description")
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private void showAddDialog() {
        JDialog dialog = new JDialog((JFrame) SwingUtilities.getWindowAncestor(this), "Tambah Produk", true);
        dialog.setSize(500, 400);
        dialog.setLocationRelativeTo(this);
        
        JPanel panel = new JPanel(new GridLayout(8, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        JTextField nameField = new JTextField();
        JComboBox<String> categoryCombo = new JComboBox<>();
        JComboBox<String> supplierCombo = new JComboBox<>();
        JTextField priceField = new JTextField();
        JTextField stockField = new JTextField();
        JTextField discountField = new JTextField("0");
        JTextField descField = new JTextField();
        
        loadCategories(categoryCombo);
        loadSuppliers(supplierCombo);
        
        panel.add(new JLabel("Nama Produk:"));
        panel.add(nameField);
        panel.add(new JLabel("Kategori:"));
        panel.add(categoryCombo);
        panel.add(new JLabel("Supplier:"));
        panel.add(supplierCombo);
        panel.add(new JLabel("Harga:"));
        panel.add(priceField);
        panel.add(new JLabel("Stok:"));
        panel.add(stockField);
        panel.add(new JLabel("Diskon (%):"));
        panel.add(discountField);
        panel.add(new JLabel("Deskripsi:"));
        panel.add(descField);
        
        JButton saveButton = new JButton("Simpan");
        saveButton.addActionListener(e -> {
            try {
                String name = nameField.getText();
                int categoryId = getCategoryId((String) categoryCombo.getSelectedItem());
                int supplierId = getSupplierId((String) supplierCombo.getSelectedItem());
                double price = Double.parseDouble(priceField.getText());
                int stock = Integer.parseInt(stockField.getText());
                double discount = Double.parseDouble(discountField.getText());
                String description = descField.getText();
                
                String sql = "INSERT INTO products (name, category_id, supplier_id, price, stock, discount, description) VALUES (?, ?, ?, ?, ?, ?, ?)";
                try (Connection conn = DatabaseManager.getConnection();
                     PreparedStatement pstmt = conn.prepareStatement(sql)) {
                    pstmt.setString(1, name);
                    pstmt.setInt(2, categoryId);
                    pstmt.setInt(3, supplierId);
                    pstmt.setDouble(4, price);
                    pstmt.setInt(5, stock);
                    pstmt.setDouble(6, discount);
                    pstmt.setString(7, description);
                    pstmt.executeUpdate();
                    
                    JOptionPane.showMessageDialog(dialog, "Produk berhasil ditambahkan!", "Sukses", JOptionPane.INFORMATION_MESSAGE);
                    loadData();
                    dialog.dispose();
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(dialog, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        panel.add(saveButton);
        dialog.add(panel);
        dialog.setVisible(true);
    }
    
    private void showEditDialog() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this, "Pilih produk terlebih dahulu!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int productId = (Integer) tableModel.getValueAt(selectedRow, 0);
        
        JDialog dialog = new JDialog((JFrame) SwingUtilities.getWindowAncestor(this), "Edit Produk", true);
        dialog.setSize(500, 400);
        dialog.setLocationRelativeTo(this);
        
        JPanel panel = new JPanel(new GridLayout(8, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        JTextField nameField = new JTextField((String) tableModel.getValueAt(selectedRow, 1));
        JComboBox<String> categoryCombo = new JComboBox<>();
        JComboBox<String> supplierCombo = new JComboBox<>();
        JTextField priceField = new JTextField(((String) tableModel.getValueAt(selectedRow, 4)).replace("Rp ", ""));
        JTextField stockField = new JTextField(String.valueOf(tableModel.getValueAt(selectedRow, 5)));
        JTextField discountField = new JTextField(((String) tableModel.getValueAt(selectedRow, 6)).replace("%", ""));
        JTextField descField = new JTextField((String) tableModel.getValueAt(selectedRow, 7));
        
        loadCategories(categoryCombo);
        loadSuppliers(supplierCombo);
        
        panel.add(new JLabel("Nama Produk:"));
        panel.add(nameField);
        panel.add(new JLabel("Kategori:"));
        panel.add(categoryCombo);
        panel.add(new JLabel("Supplier:"));
        panel.add(supplierCombo);
        panel.add(new JLabel("Harga:"));
        panel.add(priceField);
        panel.add(new JLabel("Stok:"));
        panel.add(stockField);
        panel.add(new JLabel("Diskon (%):"));
        panel.add(discountField);
        panel.add(new JLabel("Deskripsi:"));
        panel.add(descField);
        
        JButton saveButton = new JButton("Simpan");
        saveButton.addActionListener(e -> {
            try {
                String name = nameField.getText();
                int categoryId = getCategoryId((String) categoryCombo.getSelectedItem());
                int supplierId = getSupplierId((String) supplierCombo.getSelectedItem());
                double price = Double.parseDouble(priceField.getText());
                int stock = Integer.parseInt(stockField.getText());
                double discount = Double.parseDouble(discountField.getText());
                String description = descField.getText();
                
                String sql = "UPDATE products SET name=?, category_id=?, supplier_id=?, price=?, stock=?, discount=?, description=? WHERE id=?";
                try (Connection conn = DatabaseManager.getConnection();
                     PreparedStatement pstmt = conn.prepareStatement(sql)) {
                    pstmt.setString(1, name);
                    pstmt.setInt(2, categoryId);
                    pstmt.setInt(3, supplierId);
                    pstmt.setDouble(4, price);
                    pstmt.setInt(5, stock);
                    pstmt.setDouble(6, discount);
                    pstmt.setString(7, description);
                    pstmt.setInt(8, productId);
                    pstmt.executeUpdate();
                    
                    JOptionPane.showMessageDialog(dialog, "Produk berhasil diperbarui!", "Sukses", JOptionPane.INFORMATION_MESSAGE);
                    loadData();
                    dialog.dispose();
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(dialog, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        panel.add(saveButton);
        dialog.add(panel);
        dialog.setVisible(true);
    }
    
    private void deleteProduct() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this, "Pilih produk terlebih dahulu!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int confirm = JOptionPane.showConfirmDialog(this, "Yakin ingin menghapus produk ini?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            int productId = (Integer) tableModel.getValueAt(selectedRow, 0);
            String sql = "DELETE FROM products WHERE id = ?";
            
            try (Connection conn = DatabaseManager.getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, productId);
                pstmt.executeUpdate();
                
                JOptionPane.showMessageDialog(this, "Produk berhasil dihapus!", "Sukses", JOptionPane.INFORMATION_MESSAGE);
                loadData();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Gagal menghapus produk!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void loadCategories(JComboBox<String> combo) {
        String sql = "SELECT name FROM categories";
        try (Connection conn = DatabaseManager.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                combo.addItem(rs.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private void loadSuppliers(JComboBox<String> combo) {
        String sql = "SELECT name FROM suppliers";
        try (Connection conn = DatabaseManager.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                combo.addItem(rs.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private int getCategoryId(String categoryName) throws SQLException {
        String sql = "SELECT id FROM categories WHERE name = ?";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, categoryName);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            }
        }
        return 1;
    }
    
    private int getSupplierId(String supplierName) throws SQLException {
        String sql = "SELECT id FROM suppliers WHERE name = ?";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, supplierName);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            }
        }
        return 1;
    }
}
