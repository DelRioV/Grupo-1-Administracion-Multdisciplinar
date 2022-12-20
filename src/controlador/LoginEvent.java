package controlador;

import vista.Client;
import vista.LoginUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

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
                new Client();
            } catch (IOException ex) {

            }
        }
        else{
            System.out.println("Incorrecto");
        }
    }
}
