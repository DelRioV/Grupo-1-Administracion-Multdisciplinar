package controlador;
import vista.LoginUI;
import vista.MenuUI;

/**
 * @author -Ismael Orellana Bello
 *         -Pablo Salvador Del Río Vergara
 *         -Ángel Acedo Moreno
 *         -Javier Tienda
 *         -Jorge Luis López
 *         -José Ramón Gallego
 * @version 1.0
 * @date 23/12/2022
 * Class that inits the window app.
 */
public class Controlador {

    static LoginUI loginUI;
    static MenuUI menuUI;

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
        loginUI = new LoginUI();
        loginUI.getLoginButton().addActionListener(new LoginEvent(loginUI));
        loginUI.getPsf().addKeyListener(new LoginEvent(loginUI));
    }

    public static LoginUI getLoginUI() {
        return loginUI;
    }

    public static void setLoginUI(LoginUI loginUI) {
        Controlador.loginUI = loginUI;
    }
}
