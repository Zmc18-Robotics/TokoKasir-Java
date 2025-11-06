import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Sistem Kasir Toko");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(1000, 700);
            frame.setLocationRelativeTo(null);
            frame.setResizable(false);
            
            // Initialize database
            DatabaseManager.initializeDatabase();
            
            // Show login screen
            frame.setContentPane(new LoginPanel(frame));
            frame.setVisible(true);
        });
    }
}
