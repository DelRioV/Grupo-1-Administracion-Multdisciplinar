package controlador;

import modelo.Modelo;
import vista.MenuUI;
import vista.SendMailWindow;

import javax.mail.Message;
import javax.mail.MessagingException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * @author -Ismael Orellana Bello
 *         -Pablo Salvador Del Río Vergara
 *         -Ángel Acedo Moreno
 *         -Javier Tienda
 *         -Jorge Luis López
 * @version 1.0
 * @date 23/12/2022
 * Class that controls the SendMailWindow
 */
public class SendMailWindowManager implements ActionListener {
    //The window
    private SendMailWindow sendMailWindow = null;
    //Modelo
    private Modelo model = new Modelo();

    //Mail Sender
    private Sender sender = new Sender();

    private Message myMail;

    public SendMailWindowManager(){
        sendMailWindow = new SendMailWindow(model.getSENDMAILWINDOWNAME());
        addComponents();
        sendMailWindow.setDifferentProperties();
    }

    /**
     * Method that creates the window
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            sender.connect("multidisciplinarcentral@gmail.com", "tonwofhocwylixdx");
            myMail = sender.createNewMail();
            sender.setFrom(myMail, "multidisciplinarcentral@gmail.com");
            sender.setRecipients(myMail, "funcionario2ejemplo@gmail.com");
            sender.setSubject(myMail, sendMailWindow.getTextFields().get(0).getText());
            sender.addBody(myMail, sendMailWindow.getTextAreas().get(0).getText());
            sender.sendMail(myMail);
        } catch (MessagingException en) {
            throw new RuntimeException(en);
        } catch (IOException en) {
            throw new RuntimeException(en);
        }
    }

    /**
     * Method that set the components to the window
     */
    private void addComponents(){
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

        sendMailWindow.getButtons().get(1).addActionListener(this);
    }
}
