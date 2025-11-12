import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {
    private JFrame frame;
    private User currentUser;
    private JPanel contentPanel;
    
    public MainPanel(JFrame frame, User user) {
        this.frame = frame;
        this.currentUser = user;
        setLayout(new BorderLayout());
        
        // Top panel with user info and logout
        JPanel topPanel = createTopPanel();
        add(topPanel, BorderLayout.NORTH);
        
        // Left sidebar with menu
        JPanel sidebarPanel = createSidebar();
        add(sidebarPanel, BorderLayout.WEST);
        
        // Content panel
        contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());
        contentPanel.setBackground(new Color(245, 245, 245));
        add(contentPanel, BorderLayout.CENTER);
        
        // Show default panel based on role
        showDefaultPanel();
    }
    
    private JPanel createTopPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(0, 102, 204));
        panel.setPreferredSize(new Dimension(0, 60));
        
        JLabel titleLabel = new JLabel("SISTEM KASIR TOKO - " + currentUser.getRole());
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        panel.add(titleLabel, BorderLayout.WEST);
        
        JLabel userLabel = new JLabel("User: " + currentUser.getUsername());
        userLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        userLabel.setForeground(Color.WHITE);
        userLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.add(userLabel, BorderLayout.CENTER);
        
        JButton logoutButton = new JButton("Logout");
        logoutButton.setFont(new Font("Arial", Font.PLAIN, 12));
        logoutButton.addActionListener(e -> handleLogout());
        panel.add(logoutButton, BorderLayout.EAST);
        
        return panel;
    }
    
    private JPanel createSidebar() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(50, 50, 50));
        panel.setPreferredSize(new Dimension(200, 0));
        
        // Add menu items based on role
        if (currentUser.isKasir()) {
            addMenuButton(panel, "Transaksi Penjualan", () -> showKasirPanel());
            addMenuButton(panel, "Lihat Stok", () -> showStockPanel());
            addMenuButton(panel, "Transaksi Pelanggan", () -> showOrderPanel());
        } else if (currentUser.isOwner()) {
            addMenuButton(panel, "Kelola Produk", () -> showProductPanel());
            addMenuButton(panel, "Kategori Produk", () -> showCategoryPanel());
            addMenuButton(panel, "Laporan Penjualan", () -> showReportPanel());
            addMenuButton(panel, "Transaksi Pelanggan", () -> showOrderPanel());
            addMenuButton(panel, "Kelola User", () -> showUserPanel());
            addMenuButton(panel, "Backup Database", () -> handleBackup());
        } else if (currentUser.isAdmin()) {
            addMenuButton(panel, "Kelola Produk", () -> showProductPanel());
            addMenuButton(panel, "Kategori Produk", () -> showCategoryPanel());
            addMenuButton(panel, "Laporan Penjualan", () -> showReportPanel());
            addMenuButton(panel, "Transaksi Pelanggan", () -> showOrderPanel());
            addMenuButton(panel, "Kelola User", () -> showUserPanel());
            addMenuButton(panel, "Backup Database", () -> handleBackup());
        } else if (currentUser.isCustomer()) {
            addMenuButton(panel, "Katalog Produk", () -> showCustomerPanel());
            addMenuButton(panel, "Transaksi Saya", () -> showOrderPanel());
        }
        
        panel.add(Box.createVerticalGlue());
        return panel;
    }
    
    private void addMenuButton(JPanel panel, String text, Runnable action) {
        JButton button = new JButton(text);
        button.setMaximumSize(new Dimension(180, 40));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setFont(new Font("Arial", Font.PLAIN, 12));
        button.setBackground(new Color(70, 70, 70));
        button.setForeground(Color.WHITE);
        button.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        button.addActionListener(e -> action.run());
        panel.add(button);
        panel.add(Box.createVerticalStrut(5));
    }
    
    private void showDefaultPanel() {
        if (currentUser.isKasir()) {
            showKasirPanel();
        } else if (currentUser.isCustomer()) {
            showCustomerPanel();
        } else {
            showProductPanel();
        }
    }
    
    private void showKasirPanel() {
        contentPanel.removeAll();
        contentPanel.add(new KasirPanel(currentUser), BorderLayout.CENTER);
        contentPanel.revalidate();
        contentPanel.repaint();
    }
    
    private void showStockPanel() {
        contentPanel.removeAll();
        contentPanel.add(new StockPanel(), BorderLayout.CENTER);
        contentPanel.revalidate();
        contentPanel.repaint();
    }
    
    private void showProductPanel() {
        contentPanel.removeAll();
        contentPanel.add(new ProductPanel(), BorderLayout.CENTER);
        contentPanel.revalidate();
        contentPanel.repaint();
    }
    
    private void showCategoryPanel() {
        contentPanel.removeAll();
        contentPanel.add(new CategoryPanel(), BorderLayout.CENTER);
        contentPanel.revalidate();
        contentPanel.repaint();
    }
    
    private void showReportPanel() {
        contentPanel.removeAll();
        contentPanel.add(new ReportPanel(), BorderLayout.CENTER);
        contentPanel.revalidate();
        contentPanel.repaint();
    }
    
    private void showUserPanel() {
        contentPanel.removeAll();
        contentPanel.add(new UserPanel(), BorderLayout.CENTER);
        contentPanel.revalidate();
        contentPanel.repaint();
    }
    
    private void showCustomerPanel() {
        contentPanel.removeAll();
        contentPanel.add(new CustomerPanel(currentUser), BorderLayout.CENTER);
        contentPanel.revalidate();
        contentPanel.repaint();
    }
    
    private void showOrderPanel() {
        contentPanel.removeAll();
        contentPanel.add(new OrderPanel(currentUser), BorderLayout.CENTER);
        contentPanel.revalidate();
        contentPanel.repaint();
    }
    
    private void handleBackup() {
        DatabaseManager.backupDatabase();
        JOptionPane.showMessageDialog(this, "Database berhasil di-backup!", "Sukses", JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void handleLogout() {
        frame.setContentPane(new LoginPanel(frame));
        frame.revalidate();
        frame.repaint();
    }
}
