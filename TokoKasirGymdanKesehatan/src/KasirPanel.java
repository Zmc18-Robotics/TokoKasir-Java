import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;

public class KasirPanel extends JPanel {
    private User currentUser;
    private DefaultTableModel cartModel;
    private JTable cartTable;
    private JLabel totalLabel;
    private JComboBox<String> productCombo;
    private JSpinner quantitySpinner;
    private List<Product> products;
    private double totalAmount = 0;
    
    public KasirPanel(User user) {
        this.currentUser = user;
        this.products = new ArrayList<>();
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Top panel - Product selection
        JPanel topPanel = createTopPanel();
        add(topPanel, BorderLayout.NORTH);
        
        // Middle panel - Cart table
        JPanel middlePanel = createMiddlePanel();
        add(middlePanel, BorderLayout.CENTER);
        
        // Bottom panel - Total and buttons
        JPanel bottomPanel = createBottomPanel();
        add(bottomPanel, BorderLayout.SOUTH);
        
        loadProducts();
    }
    
    private JPanel createTopPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        panel.setBackground(new Color(240, 240, 240));
        
        JLabel productLabel = new JLabel("Pilih Produk:");
        productLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        panel.add(productLabel);
        
        productCombo = new JComboBox<>();
        productCombo.setPreferredSize(new Dimension(300, 30));
        productCombo.setFont(new Font("Arial", Font.PLAIN, 12));
        panel.add(productCombo);
        
        JLabel quantityLabel = new JLabel("Jumlah:");
        quantityLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        panel.add(quantityLabel);
        
        quantitySpinner = new JSpinner(new SpinnerNumberModel(1, 1, 1000, 1));
        quantitySpinner.setPreferredSize(new Dimension(80, 30));
        panel.add(quantitySpinner);
        
        JButton addButton = new JButton("Tambah ke Keranjang");
        addButton.setFont(new Font("Arial", Font.PLAIN, 12));
        addButton.addActionListener(e -> addToCart());
        panel.add(addButton);
        
        return panel;
    }
    
    private JPanel createMiddlePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        
        JLabel cartLabel = new JLabel("Keranjang Belanja");
        cartLabel.setFont(new Font("Arial", Font.BOLD, 14));
        panel.add(cartLabel, BorderLayout.NORTH);
        
        // Cart table
        String[] columns = {"No", "Produk", "Harga", "Jumlah", "Diskon %", "Subtotal", "Hapus"};
        cartModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 6; // Only delete button is editable
            }
        };
        
        cartTable = new JTable(cartModel);
        cartTable.setRowHeight(30);
        cartTable.setFont(new Font("Arial", Font.PLAIN, 12));
        
        // Add button renderer for delete
        cartTable.getColumn("Hapus").setCellRenderer(new ButtonRenderer());
        cartTable.getColumn("Hapus").setCellEditor(new ButtonEditor(new JCheckBox(), cartModel, this));
        
        JScrollPane scrollPane = new JScrollPane(cartTable);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        return panel;
    }
    
    private JPanel createBottomPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        
        // Total panel
        JPanel totalPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        totalPanel.setBackground(new Color(240, 240, 240));
        
        JLabel totalLabelText = new JLabel("Total:");
        totalLabelText.setFont(new Font("Arial", Font.BOLD, 14));
        totalPanel.add(totalLabelText);
        
        totalLabel = new JLabel("Rp 0");
        totalLabel.setFont(new Font("Arial", Font.BOLD, 16));
        totalLabel.setForeground(new Color(0, 102, 204));
        totalPanel.add(totalLabel);
        
        panel.add(totalPanel, BorderLayout.NORTH);
        
        // Buttons panel
        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        
        JButton printButton = new JButton("Cetak Struk");
        printButton.setFont(new Font("Arial", Font.BOLD, 12));
        printButton.setBackground(new Color(0, 153, 0));
        printButton.setForeground(Color.WHITE);
        printButton.addActionListener(e -> printReceipt());
        buttonsPanel.add(printButton);
        
        JButton clearButton = new JButton("Batal");
        clearButton.setFont(new Font("Arial", Font.BOLD, 12));
        clearButton.setBackground(new Color(204, 0, 0));
        clearButton.setForeground(Color.WHITE);
        clearButton.addActionListener(e -> clearCart());
        buttonsPanel.add(clearButton);
        
        panel.add(buttonsPanel, BorderLayout.SOUTH);
        
        return panel;
    }
    
    private void loadProducts() {
        String sql = "SELECT * FROM products WHERE stock > 0";
        try (Connection conn = DatabaseManager.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            productCombo.removeAllItems();
            products.clear();
            
            while (rs.next()) {
                Product product = new Product(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getInt("category_id"),
                    rs.getInt("supplier_id"),
                    rs.getDouble("price"),
                    rs.getInt("stock"),
                    rs.getDouble("discount"),
                    rs.getString("description"),
                    rs.getString("image_path")
                );
                products.add(product);
                productCombo.addItem(product.getName() + " (Stok: " + product.getStock() + ")");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private void addToCart() {
        if (products.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Tidak ada produk tersedia!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int selectedIndex = productCombo.getSelectedIndex();
        if (selectedIndex < 0) return;
        
        Product product = products.get(selectedIndex);
        int quantity = (Integer) quantitySpinner.getValue();
        
        if (quantity > product.getStock()) {
            JOptionPane.showMessageDialog(this, "Stok tidak cukup!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        double subtotal = product.getFinalPrice() * quantity;
        
        cartModel.addRow(new Object[]{
            cartModel.getRowCount() + 1,
            product.getName(),
            "Rp " + String.format("%.0f", product.getPrice()),
            quantity,
            product.getDiscount(),
            "Rp " + String.format("%.0f", subtotal),
            "Hapus"
        });
        
        totalAmount += subtotal;
        updateTotal();
    }
    
    private void updateTotal() {
        totalLabel.setText("Rp " + String.format("%.0f", totalAmount));
    }
    
    private void printReceipt() {
        if (cartModel.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "Keranjang kosong!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        try {
            // Save transaction to database
            String insertTransaction = "INSERT INTO transactions (user_id, total_amount, payment_method) VALUES (?, ?, ?)";
            try (Connection conn = DatabaseManager.getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(insertTransaction, Statement.RETURN_GENERATED_KEYS)) {
                
                pstmt.setInt(1, currentUser.getId());
                pstmt.setDouble(2, totalAmount);
                pstmt.setString(3, "CASH");
                pstmt.executeUpdate();
                
                ResultSet rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    int transactionId = rs.getInt(1);
                    
                    // Save transaction items
                    for (int i = 0; i < cartModel.getRowCount(); i++) {
                        String productName = (String) cartModel.getValueAt(i, 1);
                        int quantity = (Integer) cartModel.getValueAt(i, 3);
                        double discount = (Double) cartModel.getValueAt(i, 4);
                        String subtotalStr = (String) cartModel.getValueAt(i, 5);
                        double subtotal = Double.parseDouble(subtotalStr.replace("Rp ", "").replace(".", ""));
                        
                        // Find product id
                        Product product = products.stream()
                            .filter(p -> p.getName().equals(productName))
                            .findFirst()
                            .orElse(null);
                        
                        if (product != null) {
                            String insertItem = "INSERT INTO transaction_items (transaction_id, product_id, quantity, price, discount, subtotal) VALUES (?, ?, ?, ?, ?, ?)";
                            try (PreparedStatement itemStmt = conn.prepareStatement(insertItem)) {
                                itemStmt.setInt(1, transactionId);
                                itemStmt.setInt(2, product.getId());
                                itemStmt.setInt(3, quantity);
                                itemStmt.setDouble(4, product.getPrice());
                                itemStmt.setDouble(5, discount);
                                itemStmt.setDouble(6, subtotal);
                                itemStmt.executeUpdate();
                                
                                // Update stock
                                String updateStock = "UPDATE products SET stock = stock - ? WHERE id = ?";
                                try (PreparedStatement stockStmt = conn.prepareStatement(updateStock)) {
                                    stockStmt.setInt(1, quantity);
                                    stockStmt.setInt(2, product.getId());
                                    stockStmt.executeUpdate();
                                }
                            }
                        }
                    }
                    
                    // Show receipt
                    showReceipt(transactionId);
                    clearCart();
                    loadProducts();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Gagal menyimpan transaksi!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void showReceipt(int transactionId) {
        StringBuilder receipt = new StringBuilder();
        receipt.append("================================\n");
        receipt.append("     STRUK PEMBELIAN TOKO\n");
        receipt.append("================================\n");
        receipt.append("Kasir: ").append(currentUser.getUsername()).append("\n");
        receipt.append("Tanggal: ").append(new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date())).append("\n");
        receipt.append("No. Transaksi: ").append(transactionId).append("\n");
        receipt.append("--------------------------------\n");
        
        for (int i = 0; i < cartModel.getRowCount(); i++) {
            String product = (String) cartModel.getValueAt(i, 1);
            int qty = (Integer) cartModel.getValueAt(i, 3);
            String subtotal = (String) cartModel.getValueAt(i, 5);
            receipt.append(product).append(" x").append(qty).append("\n");
            receipt.append("  ").append(subtotal).append("\n");
        }
        
        receipt.append("--------------------------------\n");
        receipt.append("TOTAL: ").append(totalLabel.getText()).append("\n");
        receipt.append("================================\n");
        receipt.append("Terima kasih telah berbelanja!\n");
        receipt.append("================================\n");
        
        JTextArea textArea = new JTextArea(receipt.toString());
        textArea.setFont(new Font("Courier New", Font.PLAIN, 11));
        textArea.setEditable(false);
        
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(400, 400));
        
        JOptionPane.showMessageDialog(this, scrollPane, "Struk Pembelian", JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void clearCart() {
        cartModel.setRowCount(0);
        totalAmount = 0;
        updateTotal();
        quantitySpinner.setValue(1);
    }
    
    // Button renderer and editor for delete button
    class ButtonRenderer extends JButton implements TableCellRenderer {
        public ButtonRenderer() {
            setOpaque(true);
        }
        
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            setText("Hapus");
            setBackground(new Color(204, 0, 0));
            setForeground(Color.WHITE);
            return this;
        }
    }
    
    class ButtonEditor extends DefaultCellEditor {
        private JButton button;
        private DefaultTableModel model;
        private KasirPanel panel;
        
        public ButtonEditor(JCheckBox checkBox, DefaultTableModel model, KasirPanel panel) {
            super(checkBox);
            this.model = model;
            this.panel = panel;
            button = new JButton("Hapus");
            button.setBackground(new Color(204, 0, 0));
            button.setForeground(Color.WHITE);
            button.addActionListener(e -> deleteRow());
        }
        
        private void deleteRow() {
            int row = cartTable.getSelectedRow();
            if (row >= 0) {
                String subtotalStr = (String) model.getValueAt(row, 5);
                double subtotal = Double.parseDouble(subtotalStr.replace("Rp ", "").replace(".", ""));
                totalAmount -= subtotal;
                model.removeRow(row);
                updateTotal();
            }
        }
        
        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            return button;
        }
    }
}
