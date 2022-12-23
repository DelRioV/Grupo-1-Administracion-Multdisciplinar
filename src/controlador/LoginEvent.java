package controlador;

import modelo.ConnectionDB;
import modelo.MenuData;
import vista.LoginUI;
import vista.MenuUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static controlador.Controlador.menuUI;

/**
 * @author -Ismael Orellana Bello
 *         -Pablo Salvador Del Río Vergara
 *         -Ángel Acedo Moreno
 *         -Javier Tienda
 *         -Jorge Luis López
 *         -José Ramón Gallego
 * @version 1.0
 * @date 23/12/2022
 * That class contains the event of the LoginUi button
 */
public class LoginEvent implements ActionListener, KeyListener {
    //Login UI
    private LoginUI loginUI;

    /**
     * Constructor
     * @param loginUI -LoginUi the window
     */
    public LoginEvent(LoginUI loginUI){
        this.loginUI = loginUI;
    }

    /**
     * The event
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        loginMethod();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_ENTER){
            loginMethod();
        }
    }

    private void loginMethod() {
        char[] pass = loginUI.getPsf().getPassword();
        String final_pass = "";
        for (char x : pass) {
            final_pass += x;
        }
        boolean cond = new BDLogic().getAccess(loginUI.getTxfUser().getText(),final_pass);
        if(cond){
            try {
                ResultSet rs = new ConnectionDB().getRemoteConnection().executeQuery("Select idUser, Username, Email, SecretEmailKey from Users where Username = " +
                        "'"+loginUI.getTxfUser().getText()+"' or Email = '"+loginUI.getTxfUser().getText()+"'");
                rs.first();
                MenuData md = new MenuData(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
                menuUI = new MenuUI();
                menuUI.setDifferentProperties();
                loginUI.setVisible(false);
            } catch (Exception ex) {

            }
        }
        else{
            JOptionPane.showMessageDialog(null,"Usuario o contraseña incorrecta");
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
