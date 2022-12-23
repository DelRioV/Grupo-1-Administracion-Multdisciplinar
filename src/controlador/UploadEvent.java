package controlador;
import modelo.MenuData;
import modelo.Modelo;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
/**
 * @author -Ismael Orellana Bello
 *         -Pablo Salvador Del Río Vergara
 *         -Ángel Acedo Moreno
 *         -Javier Tienda
 *         -Jorge Luis López
 *         -José Ramón Gallego
 * @version 1.0
 * @date 23/12/2022
 * That class contains methods with the events for the aux window to upload
 */
public class UploadEvent implements ActionListener {

    //The window
    //Modelo
    private Modelo model = new Modelo();

    /**
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);  //solo se pueden seleccionar directorios
            fileChooser.setDialogTitle("Selecciona el Directorio donde DESCARGAR el fichero"); //titulo de la ventana
            int returnVal = fileChooser.showDialog(null, "Subir");
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                String name = JOptionPane.showInputDialog("Introduce el nombre del fichero que quieres subir");
                new UploadFiles().upload(fileChooser.getSelectedFile().getAbsolutePath(), name);
                MenuData.fic = name;
                JOptionPane.showMessageDialog(null, "Fichero subido correctamente");
            }
        } catch (Exception er) {
            JOptionPane.showMessageDialog(null, "Los parámetros introducidos son incorrectos");
        }
        try {
            MenuData.fillList(MenuData.getClient().listFiles());
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

    }

}
