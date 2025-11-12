import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.sql.*;

public class CategoryPanel extends JPanel {
    private DefaultTableModel tableModel;
    private JTable table;
    
    public CategoryPanel() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Title
        JLabel titleLabel = new JLabel("Kelola Kategori");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(titleLabel, BorderLayout.NORTH);
        
        // Table
        String[] columns = {"ID", "Nama Kategori", "Deskripsi"};
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
        
        JButton addButton = new JButton("Tambah Kategori");
        addButton.setFont(new Font("Arial", Font.PLAIN, 12));
        addButton.addActionListener(e -> showAddDialog());
        buttonPanel.add(addButton);
        
        JButton editButton = new JButton("Edit Kategori");
        editButton.setFont(new Font("Arial", Font.PLAIN, 12));
        editButton.addActionListener(e -> showEditDialog());
        buttonPanel.add(editButton);
        
        JButton deleteButton = new JButton("Hapus Kategori");
        deleteButton.setFont(new Font("Arial", Font.PLAIN, 12));
        deleteButton.setBackground(new Color(204, 0, 0));
        deleteButton.setForeground(Color.WHITE);
        deleteButton.addActionListener(e -> deleteCategory());
        buttonPanel.add(deleteButton);
        
        JButton refreshButton = new JButton("Refresh");
        refreshButton.setFont(new Font("Arial", Font.PLAIN, 12));
        refreshButton.addActionListener(e -> loadData());
        buttonPanel.add(refreshButton);
        
        add(buttonPanel, BorderLayout.SOUTH);
        
        loadData();
    }
    
    private void loadData() {
        String sql = "SELECT * FROM categories ORDER BY name";
        tableModel.setRowCount(0);
        
        try (Connection conn = DatabaseManager.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                tableModel.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("description")
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private void showAddDialog() {
        JDialog dialog = new JDialog((JFrame) SwingUtilities.getWindowAncestor(this), "Tambah Kategori", true);
        dialog.setSize(400, 200);
        dialog.setLocationRelativeTo(this);
        
        JPanel panel = new JPanel(new GridLayout(2, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        JTextField nameField = new JTextField();
        JTextField descField = new JTextField();
        
        panel.add(new JLabel("Nama Kategori:"));
        panel.add(nameField);
        panel.add(new JLabel("Deskripsi:"));
        panel.add(descField);
        
        JButton saveButton = new JButton("Simpan");
        saveButton.addActionListener(e -> {
            try {
                String name = nameField.getText();
                String description = descField.getText();
                
                if (name.isEmpty()) {
                    JOptionPane.showMessageDialog(dialog, "Nama kategori tidak boleh kosong!", "Peringatan", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                
                String sql = "INSERT INTO categories (name, description) VALUES (?, ?)";
                try (Connection conn = DatabaseManager.getConnection();
                     PreparedStatement pstmt = conn.prepareStatement(sql)) {
                    pstmt.setString(1, name);
                    pstmt.setString(2, description);
                    pstmt.executeUpdate();
                    
                    JOptionPane.showMessageDialog(dialog, "Kategori berhasil ditambahkan!", "Sukses", JOptionPane.INFORMATION_MESSAGE);
                    loadData();
                    dialog.dispose();
                }
            } catch (SQLException ex) {
                if (ex.getMessage().contains("UNIQUE")) {
                    JOptionPane.showMessageDialog(dialog, "Kategori sudah ada!", "Error", JOptionPane.ERROR_MESSAGE);
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
            JOptionPane.showMessageDialog(this, "Pilih kategori terlebih dahulu!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int categoryId = (Integer) tableModel.getValueAt(selectedRow, 0);
        
        JDialog dialog = new JDialog((JFrame) SwingUtilities.getWindowAncestor(this), "Edit Kategori", true);
        dialog.setSize(400, 200);
        dialog.setLocationRelativeTo(this);
        
        JPanel panel = new JPanel(new GridLayout(2, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        JTextField nameField = new JTextField((String) tableModel.getValueAt(selectedRow, 1));
        JTextField descField = new JTextField((String) tableModel.getValueAt(selectedRow, 2));
        
        panel.add(new JLabel("Nama Kategori:"));
        panel.add(nameField);
        panel.add(new JLabel("Deskripsi:"));
        panel.add(descField);
        
        JButton saveButton = new JButton("Simpan");
        saveButton.addActionListener(e -> {
            try {
                String name = nameField.getText();
                String description = descField.getText();
                
                if (name.isEmpty()) {
                    JOptionPane.showMessageDialog(dialog, "Nama kategori tidak boleh kosong!", "Peringatan", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                
                String sql = "UPDATE categories SET name=?, description=? WHERE id=?";
                try (Connection conn = DatabaseManager.getConnection();
                     PreparedStatement pstmt = conn.prepareStatement(sql)) {
                    pstmt.setString(1, name);
                    pstmt.setString(2, description);
                    pstmt.setInt(3, categoryId);
                    pstmt.executeUpdate();
                    
                    JOptionPane.showMessageDialog(dialog, "Kategori berhasil diperbarui!", "Sukses", JOptionPane.INFORMATION_MESSAGE);
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
    
    private void deleteCategory() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this, "Pilih kategori terlebih dahulu!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int confirm = JOptionPane.showConfirmDialog(this, "Yakin ingin menghapus kategori ini?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            int categoryId = (Integer) tableModel.getValueAt(selectedRow, 0);
            String sql = "DELETE FROM categories WHERE id = ?";
            
            try (Connection conn = DatabaseManager.getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, categoryId);
                pstmt.executeUpdate();
                
                JOptionPane.showMessageDialog(this, "Kategori berhasil dihapus!", "Sukses", JOptionPane.INFORMATION_MESSAGE);
                loadData();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Gagal menghapus kategori!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
