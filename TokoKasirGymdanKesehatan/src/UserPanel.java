import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.sql.*;

public class UserPanel extends JPanel {
    private DefaultTableModel tableModel;
    private JTable table;
    
    public UserPanel() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Title
        JLabel titleLabel = new JLabel("Kelola User");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(titleLabel, BorderLayout.NORTH);
        
        // Table
        String[] columns = {"ID", "Username", "Role", "Dibuat"};
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
        
        JButton addButton = new JButton("Tambah User");
        addButton.setFont(new Font("Arial", Font.PLAIN, 12));
        addButton.addActionListener(e -> showAddDialog());
        buttonPanel.add(addButton);
        
        JButton deleteButton = new JButton("Hapus User");
        deleteButton.setFont(new Font("Arial", Font.PLAIN, 12));
        deleteButton.setBackground(new Color(204, 0, 0));
        deleteButton.setForeground(Color.WHITE);
        deleteButton.addActionListener(e -> deleteUser());
        buttonPanel.add(deleteButton);
        
        JButton refreshButton = new JButton("Refresh");
        refreshButton.setFont(new Font("Arial", Font.PLAIN, 12));
        refreshButton.addActionListener(e -> loadData());
        buttonPanel.add(refreshButton);
        
        add(buttonPanel, BorderLayout.SOUTH);
        
        loadData();
    }
    
    private void loadData() {
        String sql = "SELECT id, username, role, created_at FROM users ORDER BY username";
        tableModel.setRowCount(0);
        
        try (Connection conn = DatabaseManager.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                tableModel.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getString("username"),
                    rs.getString("role"),
                    rs.getString("created_at")
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private void showAddDialog() {
        JDialog dialog = new JDialog((JFrame) SwingUtilities.getWindowAncestor(this), "Tambah User", true);
        dialog.setSize(400, 250);
        dialog.setLocationRelativeTo(this);
        
        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        JTextField usernameField = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        String[] roles = {"KASIR", "OWNER", "ADMIN", "CUSTOMER"};
        JComboBox<String> roleCombo = new JComboBox<>(roles);
        
        panel.add(new JLabel("Username:"));
        panel.add(usernameField);
        panel.add(new JLabel("Password:"));
        panel.add(passwordField);
        panel.add(new JLabel("Role:"));
        panel.add(roleCombo);
        
        JButton saveButton = new JButton("Simpan");
        saveButton.addActionListener(e -> {
            try {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                String role = (String) roleCombo.getSelectedItem();
                
                if (username.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(dialog, "Username dan password tidak boleh kosong!", "Peringatan", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                
                String sql = "INSERT INTO users (username, password, role) VALUES (?, ?, ?)";
                try (Connection conn = DatabaseManager.getConnection();
                     PreparedStatement pstmt = conn.prepareStatement(sql)) {
                    pstmt.setString(1, username);
                    pstmt.setString(2, password);
                    pstmt.setString(3, role);
                    pstmt.executeUpdate();
                    
                    JOptionPane.showMessageDialog(dialog, "User berhasil ditambahkan!", "Sukses", JOptionPane.INFORMATION_MESSAGE);
                    loadData();
                    dialog.dispose();
                }
            } catch (SQLException ex) {
                if (ex.getMessage().contains("UNIQUE")) {
                    JOptionPane.showMessageDialog(dialog, "Username sudah ada!", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(dialog, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
        panel.add(saveButton);
        dialog.add(panel);
        dialog.setVisible(true);
    }
    
    private void deleteUser() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this, "Pilih user terlebih dahulu!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        String username = (String) tableModel.getValueAt(selectedRow, 1);
        if ("admin".equals(username)) {
            JOptionPane.showMessageDialog(this, "Tidak bisa menghapus user admin!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int confirm = JOptionPane.showConfirmDialog(this, "Yakin ingin menghapus user ini?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            int userId = (Integer) tableModel.getValueAt(selectedRow, 0);
            String sql = "DELETE FROM users WHERE id = ?";
            
            try (Connection conn = DatabaseManager.getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, userId);
                pstmt.executeUpdate();
                
                JOptionPane.showMessageDialog(this, "User berhasil dihapus!", "Sukses", JOptionPane.INFORMATION_MESSAGE);
                loadData();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Gagal menghapus user!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
