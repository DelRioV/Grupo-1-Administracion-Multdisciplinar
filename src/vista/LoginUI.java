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
        setIconImage(new ImageIcon("src/modelo/resources/ftp.png").getImage());
        setContentPane(loginPanel);
        setMinimumSize(new Dimension(500, 700));
        setLocationRelativeTo(new JFrame());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
    }

    public JPanel getLogin() {
        return Login;
    }

    public JTextField getTxfUser() {
        return txfUser;
    }

    public JPasswordField getPsf() {
        return psf;
    }

    public JButton getLoginButton() {
        return loginButton;
    }

    public JButton getForgotPasswordButton() {
        return forgotPasswordButton;
    }

    public JPanel getLoginPanel() {
        return loginPanel;
    }
}
