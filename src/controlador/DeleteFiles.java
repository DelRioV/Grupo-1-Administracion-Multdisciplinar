package controlador;
import modelo.MenuData;
import org.apache.commons.net.ftp.FTPClient;
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
 * That class contains methods that delete files in the FTPServer
 */
public class DeleteFiles implements ActionListener {
    /**
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (MenuData.getFileSelec().contains(MenuData.getUser()) || MenuData.getUser().equals("admin")) {
            deleteFiles();
            try {
                MenuData.fillList(MenuData.getClient().listFiles());
            } catch (IOException ex) {

            }
        }
    }

    /**
     * Method that deletes files in the FTPServer
     */
    private static void deleteFiles() {
        try {
            File f = new File(MenuData.getFileSelec());
            System.out.println(MenuData.getFileSelec());
            if (MenuData.getClient().deleteFile(f.getPath())) {
                JOptionPane.showMessageDialog(null, "Archivo borrado");
                new BDLogic().insertMove(MenuData.getUserId(), "Delete", f.getName());
            } else {
                JOptionPane.showMessageDialog(null, "No se ha podido borrar el archivo");
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "No se ha podido borrar el archivo");
        }
    }
}
