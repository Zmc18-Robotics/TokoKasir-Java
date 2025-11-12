import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ReportPanel extends JPanel {
    private DefaultTableModel tableModel;
    private JTable table;
    private JLabel totalRevenueLabel;
    private JLabel totalTransactionsLabel;
    
    public ReportPanel() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Title
        JLabel titleLabel = new JLabel("Laporan Penjualan");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(titleLabel, BorderLayout.NORTH);
        
        // Stats panel
        JPanel statsPanel = createStatsPanel();
        add(statsPanel, BorderLayout.NORTH);
        
        // Table
        String[] columns = {"No. Transaksi", "Kasir", "Total", "Metode Pembayaran", "Tanggal"};
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
        
        JButton refreshButton = new JButton("Refresh");
        refreshButton.setFont(new Font("Arial", Font.PLAIN, 12));
        refreshButton.addActionListener(e -> loadData());
        buttonPanel.add(refreshButton);
        
        JButton detailButton = new JButton("Lihat Detail");
        detailButton.setFont(new Font("Arial", Font.PLAIN, 12));
        detailButton.addActionListener(e -> showDetail());
        buttonPanel.add(detailButton);
        
        add(buttonPanel, BorderLayout.SOUTH);
        
        loadData();
    }
    
    private JPanel createStatsPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 30, 10));
        panel.setBackground(new Color(240, 240, 240));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        JLabel revenueTextLabel = new JLabel("Total Pendapatan:");
        revenueTextLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        panel.add(revenueTextLabel);
        
        totalRevenueLabel = new JLabel("Rp 0");
        totalRevenueLabel.setFont(new Font("Arial", Font.BOLD, 14));
        totalRevenueLabel.setForeground(new Color(0, 102, 204));
        panel.add(totalRevenueLabel);
        
        JLabel transactionTextLabel = new JLabel("Total Transaksi:");
        transactionTextLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        panel.add(transactionTextLabel);
        
        totalTransactionsLabel = new JLabel("0");
        totalTransactionsLabel.setFont(new Font("Arial", Font.BOLD, 14));
        totalTransactionsLabel.setForeground(new Color(0, 102, 204));
        panel.add(totalTransactionsLabel);
        
        return panel;
    }
    
    private void loadData() {
        String sql = "SELECT t.id, u.username, t.total_amount, t.payment_method, t.created_at " +
                     "FROM transactions t " +
                     "LEFT JOIN users u ON t.user_id = u.id " +
                     "ORDER BY t.created_at DESC";
        
        tableModel.setRowCount(0);
        double totalRevenue = 0;
        int totalTransactions = 0;
        
        try (Connection conn = DatabaseManager.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                double amount = rs.getDouble("total_amount");
                totalRevenue += amount;
                totalTransactions++;
                
                tableModel.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getString("username"),
                    "Rp " + String.format("%.0f", amount),
                    rs.getString("payment_method"),
                    rs.getString("created_at")
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        totalRevenueLabel.setText("Rp " + String.format("%.0f", totalRevenue));
        totalTransactionsLabel.setText(String.valueOf(totalTransactions));
    }
    
    private void showDetail() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this, "Pilih transaksi terlebih dahulu!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int transactionId = (Integer) tableModel.getValueAt(selectedRow, 0);
        
        JDialog dialog = new JDialog((JFrame) SwingUtilities.getWindowAncestor(this), "Detail Transaksi", true);
        dialog.setSize(600, 400);
        dialog.setLocationRelativeTo(this);
        
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Header info
        JPanel headerPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        headerPanel.setBorder(BorderFactory.createTitledBorder("Informasi Transaksi"));
        
        String sql = "SELECT t.id, u.username, t.total_amount, t.payment_method, t.created_at " +
                     "FROM transactions t " +
                     "LEFT JOIN users u ON t.user_id = u.id " +
                     "WHERE t.id = ?";
        
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, transactionId);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                headerPanel.add(new JLabel("No. Transaksi:"));
                headerPanel.add(new JLabel(String.valueOf(rs.getInt("id"))));
                headerPanel.add(new JLabel("Kasir:"));
                headerPanel.add(new JLabel(rs.getString("username")));
                headerPanel.add(new JLabel("Total:"));
                headerPanel.add(new JLabel("Rp " + String.format("%.0f", rs.getDouble("total_amount"))));
                headerPanel.add(new JLabel("Tanggal:"));
                headerPanel.add(new JLabel(rs.getString("created_at")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        panel.add(headerPanel, BorderLayout.NORTH);
        
        // Items table
        String itemsSql = "SELECT p.name, ti.quantity, ti.price, ti.discount, ti.subtotal " +
                          "FROM transaction_items ti " +
                          "LEFT JOIN products p ON ti.product_id = p.id " +
                          "WHERE ti.transaction_id = ?";
        
        String[] columns = {"Produk", "Jumlah", "Harga", "Diskon %", "Subtotal"};
        DefaultTableModel itemsModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(itemsSql)) {
            pstmt.setInt(1, transactionId);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                itemsModel.addRow(new Object[]{
                    rs.getString("name"),
                    rs.getInt("quantity"),
                    "Rp " + String.format("%.0f", rs.getDouble("price")),
                    rs.getDouble("discount") + "%",
                    "Rp " + String.format("%.0f", rs.getDouble("subtotal"))
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        JTable itemsTable = new JTable(itemsModel);
        itemsTable.setRowHeight(25);
        itemsTable.setFont(new Font("Arial", Font.PLAIN, 12));
        
        JScrollPane scrollPane = new JScrollPane(itemsTable);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Detail Item"));
        panel.add(scrollPane, BorderLayout.CENTER);
        
        dialog.add(panel);
        dialog.setVisible(true);
    }
}
