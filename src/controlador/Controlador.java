package controlador;
import modelo.ConnectionDB;
import modelo.ConnectionData;
import org.apache.commons.net.ftp.FTPClient;
import vista.LoginUI;
import vista.UploadFilesAuxWindow;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 * @author -Ismael Orellana Bello
 *         -Pablo Salvador Del Río Vergara
 *         -Ángel Acedo Moreno
 *         -Javier Tienda
 *         -Jorge Luis López
 * @version 1.0
 * @date 23/12/2022
 * Class that inits the window app.
 */
public class Controlador {
    /**
     * Empty Constructor
     */
    public Controlador() {
        init();
    }

    /**
     * Method that creates the loginWindow
     */
    public void init(){
        LoginUI loginUI = new LoginUI();
        loginUI.getLoginButton().addActionListener(new LoginEvent(loginUI));
    }

}
