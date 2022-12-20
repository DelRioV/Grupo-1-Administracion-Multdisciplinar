package controlador;

import modelo.Modelo;
import vista.InboxWindow;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class InboxWindowManager {

    private InboxWindow inboxWindow = null;
    private Modelo model = new Modelo();

    public InboxWindowManager() {
        //createWindow();
    }

    public void createWindow() {
        inboxWindow = new InboxWindow(model.getINBOXWINDOWNAME());
        addComponents();
        inboxWindow.setDifferentProperties();
    }

    private void addComponents() {
        inboxWindow.createButtons(model.getINBOXWINDOWNUMBUTTONS());
        inboxWindow.createTable();
        inboxWindow.createPanels(model.getINBOXWINDOWJPANEL());
        inboxWindow.createLabels(model.getINBOXWINDOWJTABLE());
        northBorderLayout();
        centerBorderLayout();
        southBorderLayout();
    }



    private void southBorderLayout() {
        JPanel jpanel = new JPanel();
        jpanel.setBackground(model.bgColorInboxNorthSouthBorderLayout);
        jpanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        jpanel.add(inboxWindow.getButtons().get(0));
        inboxWindow.getPanels().get(0).add(jpanel, BorderLayout.SOUTH);
    }

    private void centerBorderLayout() {
        JScrollPane jScrollPane = new JScrollPane(inboxWindow.getTable());
        jScrollPane.setBackground(model.bgColorInboxJTable);

        jScrollPane.setBorder(new LineBorder(Color.black, 3, true));
        inboxWindow.getPanels().get(0).add(jScrollPane, BorderLayout.CENTER);

    }

    private void northBorderLayout() {
        JPanel jpanel = new JPanel();
        jpanel.setBackground(model.bgColorInboxNorthSouthBorderLayout);
        jpanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        jpanel.add(inboxWindow.getLabels().get(0));
        inboxWindow.getPanels().get(0).add(jpanel, BorderLayout.NORTH);
    }


}
