package controlador;
import org.apache.commons.net.ftp.FTPClient;
import vista.Client;
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
        if (Client.getFileSelec().contains(Client.getUser()) || Client.getUser().equals("admin")) {
            deleteFiles();
            try {
                Client.fillList(Client.getClient().listFiles());
            } catch (IOException ex) {

            }
        }
    }

    /**
     * Method that deletes files in the FTPServer
     */
    private static void deleteFiles() {
        try {
            File f = new File(Client.getFileSelec());
            System.out.println(Client.getFileSelec());
            if (Client.getClient().deleteFile(f.getPath())) {
                JOptionPane.showMessageDialog(null, "Archivo borrado");
                new BDLogic().insertMove(Client.getUserId(), "Delete", f.getName());
            } else {
                JOptionPane.showMessageDialog(null, "No se ha podido borrar el archivo");
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "No se ha podido borrar el archivo");
        }
    }
}
