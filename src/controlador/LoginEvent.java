package controlador;

import modelo.ConnectionDB;
import vista.Client;
import vista.LoginUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 * @author -Ismael Orellana Bello
 *         -Pablo Salvador Del Río Vergara
 *         -Ángel Acedo Moreno
 *         -Javier Tienda
 *         -Jorge Luis López
 * @version 1.0
 * @date 23/12/2022
 * That class contains the event of the LoginUi button
 */
public class LoginEvent implements ActionListener {
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
        char[] pass = loginUI.getPsf().getPassword();
        String final_pass = "";
        for (char x : pass) {
            final_pass += x;
        }
        boolean cond = new BDLogic().getAccess(loginUI.getTxfUser().getText(),final_pass);
        if(cond){
            try {
                ResultSet rs = new ConnectionDB().getRemoteConnection().executeQuery("Select idUser from Users where Username = " +
                        "'"+loginUI.getTxfUser().getText()+"' or Email = '"+loginUI.getTxfUser().getText()+"'");
                rs.first();
                new Client(rs.getInt(1));
            } catch (Exception ex) {

            }
        }
        else{
            JOptionPane.showMessageDialog(null,"Contraseña incorrecta");
        }
    }
}
