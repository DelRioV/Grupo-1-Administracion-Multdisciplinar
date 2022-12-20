package vista;

import javax.swing.*;
import java.awt.*;

public class LoginUI extends JFrame{
    private JPanel Login;
    private JTextField txfUser;
    private JPasswordField psf;
    private JButton loginButton;
    private JButton forgotPasswordButton;
    private JPanel loginPanel;

    public LoginUI(){
        super("Login");
        setContentPane(loginPanel);
        setMinimumSize(new Dimension(500, 700));
        setLocationRelativeTo(new JFrame());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
    }

    public static void main(String[] args) {
        new LoginUI();
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
