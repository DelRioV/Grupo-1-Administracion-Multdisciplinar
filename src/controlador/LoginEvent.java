package controlador;

import modelo.ConnectionDB;
import vista.Client;
import vista.LoginUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginEvent implements ActionListener {

    private LoginUI loginUI;

    public LoginEvent(LoginUI loginUI){
        this.loginUI = loginUI;
    }

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
                ex.printStackTrace();
            }
        }
        else{
            System.out.println("Incorrecto");
        }
    }
}
