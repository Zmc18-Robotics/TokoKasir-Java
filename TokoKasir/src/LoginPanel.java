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
    }
    
    private void handleLogin() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        
        if (username.isEmpty() || password.isEmpty()) {
            errorLabel.setText("Username dan password tidak boleh kosong!");
            return;
        }
        
        User user = DatabaseManager.authenticateUser(username, password);
        if (user != null) {
            frame.setContentPane(new MainPanel(frame, user));
            frame.revalidate();
            frame.repaint();
        } else {
            errorLabel.setText("Username atau password salah!");
            passwordField.setText("");
        }
    }
}
