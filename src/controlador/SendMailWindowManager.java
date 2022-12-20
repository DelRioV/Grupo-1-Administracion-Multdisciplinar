package controlador;

import modelo.Modelo;
import vista.SendMailWindow;

import java.awt.*;

public class SendMailWindowManager {

    private SendMailWindow sendMailWindow = null;
    private Modelo model = new Modelo();

    public void createWindow(){
        sendMailWindow = new SendMailWindow(model.getSENDMAILWINDOWNAME());
        addComponents();
        sendMailWindow.setDifferentProperties();
    }

    private void addComponents() {
        sendMailWindow.createButtons(model.getSENDMAILWINDOWNUMBTNS());
        sendMailWindow.createTextArea(model.getSENDMAILWINDOWNUMTEXTAREA());
        sendMailWindow.createLabels(model.getSENDMAILWINDOWNUMLABELS());
        sendMailWindow.createPanels(model.getSENDMAILWINDOWNUMPANELS());
        sendMailWindow.createTextFields(model.getSENDMAILWINDOWTNUMEXTFIELDS());
        sendMailWindow.getPanels().get(0).add(sendMailWindow.getLabels().get(0));
        sendMailWindow.getPanels().get(0).add(sendMailWindow.getTextFields().get(0));
        sendMailWindow.getPanels().get(0).add(sendMailWindow.getLabels().get(1));
        sendMailWindow.getPanels().get(0).add(sendMailWindow.getTextAreas().get(0));
        sendMailWindow.getPanels().get(1).add(sendMailWindow.getButtons().get(0), BorderLayout.WEST);
        sendMailWindow.getPanels().get(1).add(sendMailWindow.getButtons().get(1), BorderLayout.EAST);
        sendMailWindow.getPanels().get(0).add(sendMailWindow.getPanels().get(1));
    }
}
