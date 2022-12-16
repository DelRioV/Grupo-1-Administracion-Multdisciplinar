package controlador;

import vista.LoginWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginEvent implements ActionListener {

    private LoginWindow loginWindow;
    public LoginEvent(LoginWindow loginWindow){
        this.loginWindow = loginWindow;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try{
        if(new GetUsersCredentials().getResult(loginWindow.getTextFields().get(0).getText(),loginWindow.getTextFields().get(1).getText())){
            System.out.println("Hola mundo");
        }
        }catch (Exception er){
            er.printStackTrace();
        }
    }
}
