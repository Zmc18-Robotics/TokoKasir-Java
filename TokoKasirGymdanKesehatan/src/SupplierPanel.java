import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.sql.*;

public class SupplierPanel extends JPanel {
    private DefaultTableModel tableModel;
    private JTable table;
    
    public SupplierPanel() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Title
        JLabel titleLabel = new JLabel("Kelola Supplier");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(titleLabel, BorderLayout.NORTH);
        
        // Table
        String[] columns = {"ID", "Nama Supplier", "Telepon", "Alamat"};
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
        
        JButton addButton = new JButton("Tambah Supplier");
        addButton.setFont(new Font("Arial", Font.PLAIN, 12));
        addButton.addActionListener(e -> showAddDialog());
        buttonPanel.add(addButton);
        
        JButton editButton = new JButton("Edit Supplier");
        editButton.setFont(new Font("Arial", Font.PLAIN, 12));
        editButton.addActionListener(e -> showEditDialog());
        buttonPanel.add(editButton);
        
        JButton deleteButton = new JButton("Hapus Supplier");
        deleteButton.setFont(new Font("Arial", Font.PLAIN, 12));
        deleteButton.setBackground(new Color(204, 0, 0));
        deleteButton.setForeground(Color.WHITE);
        deleteButton.addActionListener(e -> deleteSupplier());
        buttonPanel.add(deleteButton);
        
        JButton refreshButton = new JButton("Refresh");
        refreshButton.setFont(new Font("Arial", Font.PLAIN, 12));
        refreshButton.addActionListener(e -> loadData());
        buttonPanel.add(refreshButton);
        
        add(buttonPanel, BorderLayout.SOUTH);
        
        loadData();
    }
    
    private void loadData() {
        String sql = "SELECT * FROM suppliers ORDER BY name";
        tableModel.setRowCount(0);
        
        try (Connection conn = DatabaseManager.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                tableModel.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("phone"),
                    rs.getString("address")
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private void showAddDialog() {
        JDialog dialog = new JDialog((JFrame) SwingUtilities.getWindowAncestor(this), "Tambah Supplier", true);
        dialog.setSize(400, 250);
        dialog.setLocationRelativeTo(this);
        
        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        JTextField nameField = new JTextField();
        JTextField phoneField = new JTextField();
        JTextField addressField = new JTextField();
        
        panel.add(new JLabel("Nama Supplier:"));
        panel.add(nameField);
        panel.add(new JLabel("Telepon:"));
        panel.add(phoneField);
        panel.add(new JLabel("Alamat:"));
        panel.add(addressField);
        
        JButton saveButton = new JButton("Simpan");
        saveButton.addActionListener(e -> {
            try {
                String name = nameField.getText();
                String phone = phoneField.getText();
                String address = addressField.getText();
                
                if (name.isEmpty()) {
                    JOptionPane.showMessageDialog(dialog, "Nama supplier tidak boleh kosong!", "Peringatan", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                
                String sql = "INSERT INTO suppliers (name, phone, address) VALUES (?, ?, ?)";
                try (Connection conn = DatabaseManager.getConnection();
                     PreparedStatement pstmt = conn.prepareStatement(sql)) {
                    pstmt.setString(1, name);
                    pstmt.setString(2, phone);
                    pstmt.setString(3, address);
                    pstmt.executeUpdate();
                    
                    JOptionPane.showMessageDialog(dialog, "Supplier berhasil ditambahkan!", "Sukses", JOptionPane.INFORMATION_MESSAGE);
                    loadData();
                    dialog.dispose();
                }
            } catch (SQLException ex) {
                if (ex.getMessage().contains("UNIQUE")) {
                    JOptionPane.showMessageDialog(dialog, "Supplier sudah ada!", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(dialog, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
        panel.add(saveButton);
        dialog.add(panel);
        dialog.setVisible(true);
    }
    
    private void showEditDialog() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this, "Pilih supplier terlebih dahulu!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int supplierId = (Integer) tableModel.getValueAt(selectedRow, 0);
        
        JDialog dialog = new JDialog((JFrame) SwingUtilities.getWindowAncestor(this), "Edit Supplier", true);
        dialog.setSize(400, 250);
        dialog.setLocationRelativeTo(this);
        
        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        JTextField nameField = new JTextField((String) tableModel.getValueAt(selectedRow, 1));
        JTextField phoneField = new JTextField((String) tableModel.getValueAt(selectedRow, 2));
        JTextField addressField = new JTextField((String) tableModel.getValueAt(selectedRow, 3));
        
        panel.add(new JLabel("Nama Supplier:"));
        panel.add(nameField);
        panel.add(new JLabel("Telepon:"));
        panel.add(phoneField);
        panel.add(new JLabel("Alamat:"));
        panel.add(addressField);
        
        JButton saveButton = new JButton("Simpan");
        saveButton.addActionListener(e -> {
            try {
                String name = nameField.getText();
                String phone = phoneField.getText();
                String address = addressField.getText();
                
                if (name.isEmpty()) {
                    JOptionPane.showMessageDialog(dialog, "Nama supplier tidak boleh kosong!", "Peringatan", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                
                String sql = "UPDATE suppliers SET name=?, phone=?, address=? WHERE id=?";
                try (Connection conn = DatabaseManager.getConnection();
                     PreparedStatement pstmt = conn.prepareStatement(sql)) {
                    pstmt.setString(1, name);
                    pstmt.setString(2, phone);
                    pstmt.setString(3, address);
                    pstmt.setInt(4, supplierId);
                    pstmt.executeUpdate();
                    
                    JOptionPane.showMessageDialog(dialog, "Supplier berhasil diperbarui!", "Sukses", JOptionPane.INFORMATION_MESSAGE);
                    loadData();
                    dialog.dispose();
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(dialog, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        panel.add(saveButton);
        dialog.add(panel);
        dialog.setVisible(true);
    }
    
    private void deleteSupplier() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this, "Pilih supplier terlebih dahulu!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int confirm = JOptionPane.showConfirmDialog(this, "Yakin ingin menghapus supplier ini?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            int supplierId = (Integer) tableModel.getValueAt(selectedRow, 0);
            String sql = "DELETE FROM suppliers WHERE id = ?";
            
            try (Connection conn = DatabaseManager.getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, supplierId);
                pstmt.executeUpdate();
                
                JOptionPane.showMessageDialog(this, "Supplier berhasil dihapus!", "Sukses", JOptionPane.INFORMATION_MESSAGE);
                loadData();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Gagal menghapus supplier!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
