package controlador;

import modelo.ConnectionDB;
import modelo.ConnectionData;
import org.apache.commons.net.ftp.FTPClient;
import vista.LoginUI;
import vista.UploadFilesAuxWindow;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Controlador {

    private ConnectionDB myCon = new ConnectionDB();
    public Controlador() {

    }

    public void init(){
        LoginUI loginUI = new LoginUI();
        loginUI.getLoginButton().addActionListener(new LoginEvent(loginUI));
    }

}
