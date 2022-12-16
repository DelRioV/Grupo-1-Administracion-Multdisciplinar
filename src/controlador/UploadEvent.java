package controlador;

import modelo.Modelo;
import vista.UploadFilesAuxWindow;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UploadEvent implements ActionListener {

    private UploadFilesAuxWindow uploadFilesAuxWindow = new UploadFilesAuxWindow("hola");
    private Modelo model = new Modelo();
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Subir fichero")){
            addComponents();
        }
        else{
            try{
                new UploadFiles().upload("admin","admin",uploadFilesAuxWindow.getTextFields().get(0).getText(),uploadFilesAuxWindow.getTextFields().get(1).getText());
                uploadFilesAuxWindow.dispose();
                JOptionPane.showConfirmDialog(null,"Fichero subido correctamente");
            }catch (Exception er){
                JOptionPane.showConfirmDialog(null,"Los parámetros introducidos son incorrectos");
            }

        }
    }

    private void addComponents() {
        uploadFilesAuxWindow.createButtons(model.getLoginwindownumbotones());
        uploadFilesAuxWindow.getButtons().get(0).addActionListener(this);
        uploadFilesAuxWindow.createLabels(model.getLoginwindownumlabels());
        uploadFilesAuxWindow.createPanels(model.getLoginwindownumpanels());
        uploadFilesAuxWindow.createTextFields(model.getLoginwindownumtextfields());
        uploadFilesAuxWindow.getPanels().get(0).add(uploadFilesAuxWindow.getLabels().get(0));
        uploadFilesAuxWindow.getPanels().get(0).add(uploadFilesAuxWindow.getTextFields().get(0));
        uploadFilesAuxWindow.getPanels().get(0).add(uploadFilesAuxWindow.getLabels().get(1));
        uploadFilesAuxWindow.getPanels().get(0).add(uploadFilesAuxWindow.getTextFields().get(1));
        uploadFilesAuxWindow.getPanels().get(0).add(uploadFilesAuxWindow.getButtons().get(0));
        uploadFilesAuxWindow.setDifferentProperties();
    }
}
