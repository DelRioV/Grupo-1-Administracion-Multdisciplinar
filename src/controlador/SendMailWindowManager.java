package controlador;

import modelo.MenuData;
import modelo.Modelo;
import vista.MenuUI;
import vista.SendMailWindow;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.swing.*;
import javax.swing.plaf.FileChooserUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

/**
 * @author -Ismael Orellana Bello
 * -Pablo Salvador Del Río Vergara
 * -Ángel Acedo Moreno
 * -Javier Tienda
 * -Jorge Luis López
 * -José Ramón Gallego
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

    private ArrayList<File> filesSelected = new ArrayList<>();
    private Message myMail;

    public SendMailWindowManager() {
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
            sender.connect(MenuData.getEmail(), MenuData.getEmailKey());
            myMail = sender.createNewMail();
            sender.setFrom(myMail, MenuData.getEmail());
            if (MenuData.getEmail().equals(MenuData.getAdminMail())) {
                sender.setRecipients(myMail, MenuData.getAllMails());
            } else {
                sender.setRecipients(myMail, MenuData.getAdminMail());
            }
            sender.setSubject(myMail, sendMailWindow.getTextFields().get(0).getText());
            if(filesSelected.size() != 0) {
                for (int i = 0; i < filesSelected.size(); i++) {
                    sender.addAttachment(myMail, filesSelected.get(i));
                }
            }
            sender.addBody(myMail, sendMailWindow.getTextAreas().get(0).getText());
            sender.sendMail(myMail);
            JOptionPane.showMessageDialog(null, "Mensaje enviado correctamente");
        } catch (MessagingException en) {
            JOptionPane.showMessageDialog(null, "Hubo un problema al enviar el mensaje");
        } catch (IOException en) {
            JOptionPane.showMessageDialog(null, "Hubo un problema al enviar el mensaje");
        }
    }

    /**
     * Method that set the components to the window
     */
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
        sendMailWindow.getPanels().get(1).add(sendMailWindow.getButtons().get(2), BorderLayout.CENTER);
        sendMailWindow.getPanels().get(0).add(sendMailWindow.getPanels().get(1));

        sendMailWindow.getButtons().get(0).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMailWindow.dispose();
            }
        });
        sendMailWindow.getButtons().get(1).addActionListener(this);
        sendMailWindow.getButtons().get(2).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);  //solo se pueden seleccionar directorios
                fileChooser.setDialogTitle("Selecciona el Directorio donde DESCARGAR el fichero"); //titulo de la ventana
                int returnVal = fileChooser.showDialog(null, "Subir");
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    filesSelected.add(new File(fileChooser.getSelectedFile().getAbsolutePath()));
                    sendMailWindow.getButtons().get(2).setText("(Adjuntos)" + + filesSelected.size());
                }
            }
        });
    }
}
