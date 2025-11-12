import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.sql.*;
import java.text.NumberFormat;
import java.util.Locale;

public class OrderPanel extends JPanel {
    private User currentUser;
    private DefaultTableModel tableModel;
    private JTable table;
    private NumberFormat currencyFormat;
    
    public OrderPanel(User user) {
        this.currentUser = user;
        this.currencyFormat = NumberFormat.getCurrencyInstance(Locale.of("id", "ID"));
        
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Title
        String title = currentUser.isCustomer() ? "Transaksi Saya" : "Kelola Transaksi Pelanggan";
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(titleLabel, BorderLayout.NORTH);
        
        // Table
        String[] columns = {"ID", "Pelanggan", "Produk", "Jumlah", "Harga", "Diskon %", "Subtotal", "Status", "Catatan", "Tanggal"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        table = new JTable(tableModel);
        table.setRowHeight(25);
        table.setFont(new Font("Arial", Font.PLAIN, 12));
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        
        // Set column widths
        table.getColumnModel().getColumn(0).setPreferredWidth(40);
        table.getColumnModel().getColumn(1).setPreferredWidth(100);
        table.getColumnModel().getColumn(2).setPreferredWidth(150);
        table.getColumnModel().getColumn(3).setPreferredWidth(60);
        table.getColumnModel().getColumn(4).setPreferredWidth(90);
        table.getColumnModel().getColumn(5).setPreferredWidth(70);
        table.getColumnModel().getColumn(6).setPreferredWidth(100);
        table.getColumnModel().getColumn(7).setPreferredWidth(80);
        table.getColumnModel().getColumn(8).setPreferredWidth(150);
        table.getColumnModel().getColumn(9).setPreferredWidth(130);
        
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
        
        // Buttons panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        
        // Only show status buttons for staff (not customers)
        if (!currentUser.isCustomer()) {
            JButton processButton = new JButton("Proses");
            processButton.setFont(new Font("Arial", Font.PLAIN, 12));
            processButton.setBackground(new Color(0, 153, 76));
            processButton.setForeground(Color.WHITE);
            processButton.addActionListener(e -> updateStatus("DIPROSES"));
            buttonPanel.add(processButton);
            
            JButton completeButton = new JButton("Selesai");
            completeButton.setFont(new Font("Arial", Font.PLAIN, 12));
            completeButton.setBackground(new Color(0, 102, 204));
            completeButton.setForeground(Color.WHITE);
            completeButton.addActionListener(e -> updateStatus("SELESAI"));
            buttonPanel.add(completeButton);
            
            JButton cancelButton = new JButton("Batalkan");
            cancelButton.setFont(new Font("Arial", Font.PLAIN, 12));
            cancelButton.setBackground(new Color(204, 0, 0));
            cancelButton.setForeground(Color.WHITE);
            cancelButton.addActionListener(e -> updateStatus("DIBATALKAN"));
            buttonPanel.add(cancelButton);
        }
        
        JButton refreshButton = new JButton("Refresh");
        refreshButton.setFont(new Font("Arial", Font.PLAIN, 12));
        refreshButton.addActionListener(e -> loadOrders());
        buttonPanel.add(refreshButton);
        
        // Statistics panel for staff
        if (!currentUser.isCustomer()) {
            JButton statsButton = new JButton("Lihat Statistik");
            statsButton.setFont(new Font("Arial", Font.PLAIN, 12));
            statsButton.addActionListener(e -> showStatistics());
            buttonPanel.add(statsButton);
        }
        
        add(buttonPanel, BorderLayout.SOUTH);
        
        loadOrders();
    }
    
    private void loadOrders() {
        tableModel.setRowCount(0);
        
        String sql;
        if (currentUser.isCustomer()) {
            // Customer only sees their own orders
            sql = "SELECT o.id, u.username, p.name as product_name, o.quantity, o.price, " +
                  "o.discount, o.subtotal, o.status, o.notes, o.created_at " +
                  "FROM orders o " +
                  "JOIN users u ON o.customer_id = u.id " +
                  "JOIN products p ON o.product_id = p.id " +
                  "WHERE o.customer_id = " + currentUser.getId() + " " +
                  "ORDER BY o.created_at DESC";
        } else {
            // Staff sees all orders
            sql = "SELECT o.id, u.username, p.name as product_name, o.quantity, o.price, " +
                  "o.discount, o.subtotal, o.status, o.notes, o.created_at " +
                  "FROM orders o " +
                  "JOIN users u ON o.customer_id = u.id " +
                  "JOIN products p ON o.product_id = p.id " +
                  "ORDER BY o.created_at DESC";
        }
        
        try (Connection conn = DatabaseManager.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                tableModel.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getString("username"),
                    rs.getString("product_name"),
                    rs.getInt("quantity"),
                    currencyFormat.format(rs.getDouble("price")),
                    String.format("%.0f%%", rs.getDouble("discount")),
                    currencyFormat.format(rs.getDouble("subtotal")),
                    rs.getString("status"),
                    rs.getString("notes") != null ? rs.getString("notes") : "-",
                    rs.getString("created_at")
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error memuat pesanan: " + e.getMessage(), 
                                        "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void updateStatus(String newStatus) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this, "Pilih pesanan terlebih dahulu!", 
                                        "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int orderId = (Integer) tableModel.getValueAt(selectedRow, 0);
        String currentStatus = (String) tableModel.getValueAt(selectedRow, 7);
        
        // Don't allow changing completed or cancelled orders
        if ("SELESAI".equals(currentStatus) || "DIBATALKAN".equals(currentStatus)) {
            JOptionPane.showMessageDialog(this, 
                "Pesanan dengan status " + currentStatus + " tidak dapat diubah!", 
                "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int confirm = JOptionPane.showConfirmDialog(this, 
            "Ubah status pesanan menjadi " + newStatus + "?", 
            "Konfirmasi", JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
            String sql = "UPDATE orders SET status = ? WHERE id = ?";
            
            try (Connection conn = DatabaseManager.getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                
                pstmt.setString(1, newStatus);
                pstmt.setInt(2, orderId);
                pstmt.executeUpdate();
                
                JOptionPane.showMessageDialog(this, "Status pesanan berhasil diubah!", 
                                            "Sukses", JOptionPane.INFORMATION_MESSAGE);
                loadOrders();
                
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Gagal mengubah status: " + e.getMessage(), 
                                            "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void showStatistics() {
        String sql = "SELECT " +
                     "COUNT(*) as total_orders, " +
                     "SUM(subtotal) as total_revenue, " +
                     "SUM(CASE WHEN status = 'PENDING' THEN 1 ELSE 0 END) as pending, " +
                     "SUM(CASE WHEN status = 'DIPROSES' THEN 1 ELSE 0 END) as processing, " +
                     "SUM(CASE WHEN status = 'SELESAI' THEN 1 ELSE 0 END) as completed, " +
                     "SUM(CASE WHEN status = 'DIBATALKAN' THEN 1 ELSE 0 END) as cancelled " +
                     "FROM orders";
        
        try (Connection conn = DatabaseManager.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            if (rs.next()) {
                int totalOrders = rs.getInt("total_orders");
                double totalRevenue = rs.getDouble("total_revenue");
                int pending = rs.getInt("pending");
                int processing = rs.getInt("processing");
                int completed = rs.getInt("completed");
                int cancelled = rs.getInt("cancelled");
                
                String message = String.format(
                    "=== STATISTIK PESANAN ===\n\n" +
                    "Total Pesanan: %d\n" +
                    "Total Pendapatan: %s\n\n" +
                    "Status Pesanan:\n" +
                    "- Pending: %d\n" +
                    "- Diproses: %d\n" +
                    "- Selesai: %d\n" +
                    "- Dibatalkan: %d",
                    totalOrders, currencyFormat.format(totalRevenue),
                    pending, processing, completed, cancelled
                );
                
                JOptionPane.showMessageDialog(this, message, "Statistik Pesanan", 
                                            JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error memuat statistik: " + e.getMessage(), 
                                        "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
