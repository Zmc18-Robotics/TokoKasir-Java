import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.sql.*;
import java.text.NumberFormat;
import java.util.Locale;

public class CustomerPanel extends JPanel {
    private User currentUser;
    private DefaultTableModel tableModel;
    private JTable table;
    private NumberFormat currencyFormat;
    
    public CustomerPanel(User user) {
        this.currentUser = user;
        this.currencyFormat = NumberFormat.getCurrencyInstance(Locale.of("id", "ID"));
        
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Title
        JLabel titleLabel = new JLabel("Katalog Produk - Selamat Berbelanja!");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        add(titleLabel, BorderLayout.NORTH);
        
        // Table
        String[] columns = {"ID", "Nama Produk", "Kategori", "Harga", "Diskon %", "Harga Setelah Diskon", "Stok", "Deskripsi"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        table = new JTable(tableModel);
        table.setRowHeight(30);
        table.setFont(new Font("Arial", Font.PLAIN, 12));
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        
        // Set column widths
        table.getColumnModel().getColumn(0).setPreferredWidth(40);
        table.getColumnModel().getColumn(1).setPreferredWidth(150);
        table.getColumnModel().getColumn(2).setPreferredWidth(100);
        table.getColumnModel().getColumn(3).setPreferredWidth(100);
        table.getColumnModel().getColumn(4).setPreferredWidth(70);
        table.getColumnModel().getColumn(5).setPreferredWidth(120);
        table.getColumnModel().getColumn(6).setPreferredWidth(60);
        table.getColumnModel().getColumn(7).setPreferredWidth(200);
        
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
        
        // Buttons panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        
        JButton orderButton = new JButton("Pesan Produk");
        orderButton.setFont(new Font("Arial", Font.BOLD, 14));
        orderButton.setBackground(new Color(0, 153, 76));
        orderButton.setForeground(Color.WHITE);
        orderButton.addActionListener(e -> showOrderDialog());
        buttonPanel.add(orderButton);
        
        JButton refreshButton = new JButton("Refresh");
        refreshButton.setFont(new Font("Arial", Font.PLAIN, 12));
        refreshButton.addActionListener(e -> loadProducts());
        buttonPanel.add(refreshButton);
        
        add(buttonPanel, BorderLayout.SOUTH);
        
        loadProducts();
    }
    
    private void loadProducts() {
        String sql = "SELECT p.id, p.name, c.name as category, p.price, p.discount, p.stock, p.description " +
                     "FROM products p " +
                     "JOIN categories c ON p.category_id = c.id " +
                     "WHERE p.stock > 0 " +
                     "ORDER BY p.name";
        tableModel.setRowCount(0);
        
        try (Connection conn = DatabaseManager.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                double price = rs.getDouble("price");
                double discount = rs.getDouble("discount");
                double finalPrice = price - (price * discount / 100);
                
                tableModel.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("category"),
                    currencyFormat.format(price),
                    String.format("%.0f%%", discount),
                    currencyFormat.format(finalPrice),
                    rs.getInt("stock"),
                    rs.getString("description")
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error memuat produk: " + e.getMessage(), 
                                        "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void showOrderDialog() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this, "Pilih produk terlebih dahulu!", 
                                        "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int productId = (Integer) tableModel.getValueAt(selectedRow, 0);
        String productName = (String) tableModel.getValueAt(selectedRow, 1);
        int availableStock = (Integer) tableModel.getValueAt(selectedRow, 6);
        
        // Get product details from database
        String sql = "SELECT price, discount FROM products WHERE id = ?";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, productId);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                double price = rs.getDouble("price");
                double discount = rs.getDouble("discount");
                double finalPrice = price - (price * discount / 100);
                
                // Create order dialog
                JDialog dialog = new JDialog((JFrame) SwingUtilities.getWindowAncestor(this), 
                                           "Pesan: " + productName, true);
                dialog.setSize(450, 350);
                dialog.setLocationRelativeTo(this);
                
                JPanel panel = new JPanel(new GridBagLayout());
                panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
                GridBagConstraints gbc = new GridBagConstraints();
                gbc.fill = GridBagConstraints.HORIZONTAL;
                gbc.insets = new Insets(5, 5, 5, 5);
                
                // Product info
                gbc.gridx = 0; gbc.gridy = 0;
                panel.add(new JLabel("Produk:"), gbc);
                gbc.gridx = 1;
                JLabel productLabel = new JLabel(productName);
                productLabel.setFont(new Font("Arial", Font.BOLD, 12));
                panel.add(productLabel, gbc);
                
                gbc.gridx = 0; gbc.gridy = 1;
                panel.add(new JLabel("Harga:"), gbc);
                gbc.gridx = 1;
                panel.add(new JLabel(currencyFormat.format(finalPrice) + " per unit"), gbc);
                
                gbc.gridx = 0; gbc.gridy = 2;
                panel.add(new JLabel("Stok Tersedia:"), gbc);
                gbc.gridx = 1;
                panel.add(new JLabel(String.valueOf(availableStock)), gbc);
                
                // Quantity input
                gbc.gridx = 0; gbc.gridy = 3;
                panel.add(new JLabel("Jumlah:"), gbc);
                gbc.gridx = 1;
                JSpinner quantitySpinner = new JSpinner(new SpinnerNumberModel(1, 1, availableStock, 1));
                quantitySpinner.setFont(new Font("Arial", Font.PLAIN, 12));
                panel.add(quantitySpinner, gbc);
                
                // Total label
                gbc.gridx = 0; gbc.gridy = 4;
                panel.add(new JLabel("Total:"), gbc);
                gbc.gridx = 1;
                JLabel totalLabel = new JLabel(currencyFormat.format(finalPrice));
                totalLabel.setFont(new Font("Arial", Font.BOLD, 14));
                totalLabel.setForeground(new Color(0, 102, 204));
                panel.add(totalLabel, gbc);
                
                // Update total when quantity changes
                quantitySpinner.addChangeListener(e -> {
                    int qty = (Integer) quantitySpinner.getValue();
                    double total = finalPrice * qty;
                    totalLabel.setText(currencyFormat.format(total));
                });
                
                // Notes
                gbc.gridx = 0; gbc.gridy = 5;
                panel.add(new JLabel("Catatan:"), gbc);
                gbc.gridx = 1;
                JTextArea notesArea = new JTextArea(3, 20);
                notesArea.setLineWrap(true);
                notesArea.setWrapStyleWord(true);
                JScrollPane notesScroll = new JScrollPane(notesArea);
                panel.add(notesScroll, gbc);
                
                // Buttons
                JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
                
                JButton orderBtn = new JButton("Pesan Sekarang");
                orderBtn.setFont(new Font("Arial", Font.BOLD, 12));
                orderBtn.setBackground(new Color(0, 153, 76));
                orderBtn.setForeground(Color.WHITE);
                orderBtn.addActionListener(e -> {
                    int quantity = (Integer) quantitySpinner.getValue();
                    String notes = notesArea.getText().trim();
                    
                    if (placeOrder(productId, quantity, price, discount, notes)) {
                        JOptionPane.showMessageDialog(dialog, 
                            "Pesanan berhasil dibuat!\nTotal: " + totalLabel.getText() + 
                            "\n\nPesanan Anda akan diproses segera.", 
                            "Sukses", JOptionPane.INFORMATION_MESSAGE);
                        loadProducts();
                        dialog.dispose();
                    }
                });
                buttonPanel.add(orderBtn);
                
                JButton cancelBtn = new JButton("Batal");
                cancelBtn.setFont(new Font("Arial", Font.PLAIN, 12));
                cancelBtn.addActionListener(e -> dialog.dispose());
                buttonPanel.add(cancelBtn);
                
                gbc.gridx = 0; gbc.gridy = 6; gbc.gridwidth = 2;
                panel.add(buttonPanel, gbc);
                
                dialog.add(panel);
                dialog.setVisible(true);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), 
                                        "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private boolean placeOrder(int productId, int quantity, double price, double discount, String notes) {
        double subtotal = (price - (price * discount / 100)) * quantity;
        
        String sql = "INSERT INTO orders (customer_id, product_id, quantity, price, discount, subtotal, notes) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, currentUser.getId());
            pstmt.setInt(2, productId);
            pstmt.setInt(3, quantity);
            pstmt.setDouble(4, price);
            pstmt.setDouble(5, discount);
            pstmt.setDouble(6, subtotal);
            pstmt.setString(7, notes.isEmpty() ? null : notes);
            
            pstmt.executeUpdate();
            return true;
            
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Gagal membuat pesanan: " + e.getMessage(), 
                                        "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
}
