package controlador;
import modelo.Modelo;
import vista.Client;
import vista.UploadFilesAuxWindow;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
/**
 * @author -Ismael Orellana Bello
 *         -Pablo Salvador Del Río Vergara
 *         -Ángel Acedo Moreno
 *         -Javier Tienda
 *         -Jorge Luis López
 * @version 1.0
 * @date 23/12/2022
 * That class contains methods with the events for the aux window to upload
 */
public class UploadEvent implements ActionListener {

    //The window
    private UploadFilesAuxWindow uploadFilesAuxWindow = null;
    //Modelo
    private Modelo model = new Modelo();

    /**
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Subir fichero")){
            uploadFilesAuxWindow=new UploadFilesAuxWindow("hola");
            addComponents();
        }
        else{
            try{
                File f = uploadFilesAuxWindow.getF().getSelectedFile();
                new UploadFiles().upload(f.getAbsolutePath(),uploadFilesAuxWindow.getTextFields().get(1).getText());
                Client.fic = f.getName();
                uploadFilesAuxWindow.dispose();
                JOptionPane.showMessageDialog(null,"Fichero subido correctamente");
            }catch (Exception er){
                uploadFilesAuxWindow.dispose();
                JOptionPane.showMessageDialog(null,"Los parámetros introducidos son incorrectos");
            }

        }
        try {
            Client.fillList(Client.getClient().listFiles());
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * Method that add the components to the window
     */
    private void addComponents() {
        uploadFilesAuxWindow.createButtons(model.getLoginwindownumbotones());
        uploadFilesAuxWindow.getButtons().get(0).addActionListener(this);
        uploadFilesAuxWindow.createLabels(model.getLoginwindownumlabels());
        uploadFilesAuxWindow.createPanels(model.getLoginwindownumpanels());
        uploadFilesAuxWindow.createTextFields(model.getLoginwindownumtextfields());
        uploadFilesAuxWindow.createJFileChooser();
        uploadFilesAuxWindow.getPanels().get(0).add(uploadFilesAuxWindow.getF());
        uploadFilesAuxWindow.getPanels().get(0).add(uploadFilesAuxWindow.getLabels().get(1));
        uploadFilesAuxWindow.getPanels().get(0).add(uploadFilesAuxWindow.getTextFields().get(1));
        uploadFilesAuxWindow.getPanels().get(0).add(uploadFilesAuxWindow.getButtons().get(0));
        uploadFilesAuxWindow.setDifferentProperties();
    }
}
