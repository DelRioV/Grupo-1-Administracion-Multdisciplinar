package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static controlador.Controlador.menuUI;

public class LogOutEvent implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        menuUI.dispose();
        new Controlador();
    }
}
