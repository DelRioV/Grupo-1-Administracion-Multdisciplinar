package controlador;

import modelo.Modelo;
import vista.LoginWindow;

import javax.swing.*;

public class LoginWindowManager {

    private LoginWindow loginWindow = null;
    private  Modelo model = new Modelo();
    public void createWindow(){

        loginWindow = new LoginWindow(model.getLoginwindowname());
        addComponents();
        loginWindow.setDifferentProperties();
    }


    private void addComponents(){
        loginWindow.createButtons(model.getLoginwindownumbotones());
        loginWindow.createLabels(model.getLoginwindownumlabels());
        loginWindow.createPanels(model.getLoginwindownumpanels());
        loginWindow.createTextFields(model.getLoginwindownumtextfields());
        loginWindow.getPanels().get(0).add(loginWindow.getLabels().get(0));
        loginWindow.getPanels().get(0).add(loginWindow.getTextFields().get(0));
        loginWindow.getPanels().get(0).add(loginWindow.getLabels().get(1));
        loginWindow.getPanels().get(0).add(loginWindow.getTextFields().get(1));
        loginWindow.getPanels().get(0).add(loginWindow.getButtons().get(0));
    }

}
