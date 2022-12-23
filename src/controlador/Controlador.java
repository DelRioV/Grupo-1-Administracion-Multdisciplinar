package controlador;
import modelo.MenuData;
import vista.LoginUI;
import vista.MenuUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        loginUI.getForgotPasswordButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,"Contacta con el administrador\n" + MenuData.getAdminMail());
            }
        });
        loginUI.getPsf().addKeyListener(new LoginEvent(loginUI));
    }
}
