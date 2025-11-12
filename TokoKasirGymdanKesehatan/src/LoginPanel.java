import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginPanel extends JPanel {
    private JFrame frame;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JLabel errorLabel;
    
    public LoginPanel(JFrame frame) {
        this.frame = frame;
        setLayout(null);
        setBackground(new Color(240, 240, 240));
        
        // Title
        JLabel titleLabel = new JLabel("SISTEM KASIR TOKO");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setBounds(250, 80, 500, 50);
        add(titleLabel);
        
        // Username label and field
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        usernameLabel.setBounds(300, 180, 100, 30);
        add(usernameLabel);
        
        usernameField = new JTextField();
        usernameField.setFont(new Font("Arial", Font.PLAIN, 14));
        usernameField.setBounds(300, 210, 400, 40);
        add(usernameField);
        
        // Password label and field
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        passwordLabel.setBounds(300, 270, 100, 30);
        add(passwordLabel);
        
        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Arial", Font.PLAIN, 14));
        passwordField.setBounds(300, 300, 400, 40);
        add(passwordField);
        
        // Error label
        errorLabel = new JLabel("");
        errorLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        errorLabel.setForeground(Color.RED);
        errorLabel.setBounds(300, 350, 400, 30);
        add(errorLabel);
        
        // Login button
        JButton loginButton = new JButton("Login");
        loginButton.setFont(new Font("Arial", Font.BOLD, 14));
        loginButton.setBounds(350, 400, 300, 50);
        loginButton.setBackground(new Color(0, 102, 204));
        loginButton.setForeground(Color.WHITE);
        loginButton.addActionListener(e -> handleLogin());
        add(loginButton);
        
        // Demo info
        JLabel demoLabel = new JLabel("Demo: username=admin, password=admin123");
        demoLabel.setFont(new Font("Arial", Font.ITALIC, 11));
        demoLabel.setForeground(new Color(100, 100, 100));
        demoLabel.setBounds(300, 480, 400, 30);
        add(demoLabel);
        
        // Temporary quick access buttons (bottom left)
        addTemporaryButtons();
    }
    
    private void addTemporaryButtons() {
        // Admin button
        JButton adminButton = new JButton("Admin");
        adminButton.setFont(new Font("Arial", Font.BOLD, 10));
        adminButton.setBounds(10, 620, 60, 30);
        adminButton.setBackground(new Color(200, 50, 50));
        adminButton.setForeground(Color.WHITE);
        adminButton.addActionListener(e -> quickLogin("admin", "ADMIN", 1));
        add(adminButton);
        
        // Kasir button
        JButton kasirButton = new JButton("Kasir");
        kasirButton.setFont(new Font("Arial", Font.BOLD, 10));
        kasirButton.setBounds(75, 620, 60, 30);
        kasirButton.setBackground(new Color(50, 150, 50));
        kasirButton.setForeground(Color.WHITE);
        kasirButton.addActionListener(e -> quickLogin("kasir", "KASIR", 2));
        add(kasirButton);
        
        // Customer button
        JButton customerButton = new JButton("Customer");
        customerButton.setFont(new Font("Arial", Font.BOLD, 10));
        customerButton.setBounds(140, 620, 70, 30);
        customerButton.setBackground(new Color(50, 100, 200));
        customerButton.setForeground(Color.WHITE);
        customerButton.addActionListener(e -> quickLogin("customer", "CUSTOMER", 3));
        add(customerButton);
    }
    
    private void quickLogin(String username, String role, int userId) {
        // Create user directly without checking database
        User user = new User(userId, username, role);
        System.out.println("DEBUG: Quick login sebagai " + role + " (" + username + ")");
        frame.setContentPane(new MainPanel(frame, user));
        frame.revalidate();
        frame.repaint();
    }
    
    private void handleLogin() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        
        if (username.isEmpty() || password.isEmpty()) {
            errorLabel.setText("Username dan password tidak boleh kosong!");
            return;
        }
        
        System.out.println("DEBUG: Attempting login with username: " + username);
        User user = DatabaseManager.authenticateUser(username, password);
        if (user != null) {
            System.out.println("DEBUG: Login sukses! User role: " + user.getRole());
            frame.setContentPane(new MainPanel(frame, user));
            frame.revalidate();
            frame.repaint();
        } else {
            System.out.println("DEBUG: Login GAGAL!");
            errorLabel.setText("Username atau password salah!");
            passwordField.setText("");
        }
    }
}
