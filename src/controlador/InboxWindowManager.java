package controlador;

import modelo.EMail;
import modelo.MenuData;
import modelo.Modelo;
import vista.InboxWindow;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author -Ismael Orellana Bello
 *         -Pablo Salvador Del Río Vergara
 *         -Ángel Acedo Moreno
 *         -Javier Tienda
 *         -Jorge Luis López
 * @version 1.0
 * @date 23/12/2022
 * Class that manage the inbox window
 */
public class InboxWindowManager implements ActionListener {
    //Inbox window
    private InboxWindow inboxWindow = null;
    private Inbox inbox;
    //Modelo
    private Modelo model = new Modelo();

    /**
     * Constructor
     */
    public InboxWindowManager() {
        inbox  = new Inbox(MenuData.getEmail(), MenuData.getEmailKey());
    }

    /**
     * Method that creates the window
     */
    public void createWindow() {
        inboxWindow = new InboxWindow(model.getINBOXWINDOWNAME());
        addComponents();
        inboxWindow.setDifferentProperties();
    }

    /**
     * Method that creates the components of the window
     */
    private void addComponents() {
        inboxWindow.createButtons(model.getINBOXWINDOWNUMBUTTONS());
        inboxWindow.createTable();
        inboxWindow.createPanels(model.getINBOXWINDOWJPANEL());
        inboxWindow.createLabels(model.getINBOXWINDOWJTABLE());
        northBorderLayout();
        centerBorderLayout();
        southBorderLayout();
    }

    /**
     * Method that put a panel in a south Layout
     */
    private void southBorderLayout() {
        JPanel jpanel = new JPanel();
        jpanel.setBackground(model.bgColorInboxNorthSouthBorderLayout);
        jpanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        inboxWindow.getButtons().get(0).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inboxWindow.dispose();
            }
        });
        jpanel.add(inboxWindow.getButtons().get(0));
        inboxWindow.getPanels().get(0).add(jpanel, BorderLayout.SOUTH);
    }
    /**
     * Method that put a panel in a center Layout
     */
    private void centerBorderLayout() {
        JScrollPane jScrollPane = new JScrollPane(inboxWindow.getTable());
        jScrollPane.setBackground(model.bgColorInboxJTable);
        jScrollPane.setBorder(new LineBorder(Color.black, 3, true));
        inboxWindow.getPanels().get(0).add(jScrollPane, BorderLayout.CENTER);

    }
    /**
     * Method that put a panel in a north Layout
     */
    private void northBorderLayout() {
        JPanel jpanel = new JPanel();
        jpanel.setBackground(model.bgColorInboxNorthSouthBorderLayout);
        jpanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        jpanel.add(inboxWindow.getLabels().get(0));
        inboxWindow.getPanels().get(0).add(jpanel, BorderLayout.NORTH);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        createWindow();
        try {
            inbox.connect();
            ArrayList<Folder> myFolder = inbox.listAllFolders();
            Message[] myMessages = inbox.getMessagesInFolder(myFolder.get(0));
            for(int j = 0; j < model.NUMMAILS; j++){
                try {
                    EMail email = inbox.readMessage(myMessages[j]);
                    inboxWindow.addRow(new Object[]{email.getMessage().getFrom()[0].toString(),email.getMessage().getSubject()});
                }
                catch (Exception npe){
                }
            }
        } catch (MessagingException ex) {
            throw new RuntimeException(ex);
        }
        inboxWindow.setDifferentProperties();
    }
}
