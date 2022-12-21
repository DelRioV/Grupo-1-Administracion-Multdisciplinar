package controlador;

import org.apache.commons.net.ftp.FTPClient;
import vista.Client;

import javax.swing.*;
import java.io.IOException;
/**
 * @author -Ismael Orellana Bello
 *         -Pablo Salvador Del Río Vergara
 *         -Ángel Acedo Moreno
 *         -Javier Tienda
 *         -Jorge Luis López
 * @version 1.0
 * @date 23/12/2022
 * That class contains methods for rename Files in the FTPServer
 */
public class RenameFiles {
    //Client
    private FTPClient client;
    //String newName
    private String newName;
    //The fic name
    public static String fic = "";

    /**
     * Constructor
     *
     * @param client   -Client
     * @param newName  -String the new Name
     */
    public RenameFiles(FTPClient client, String newName) {
        this.client = client;
        this.newName = newName;
    }

    /**
     * Method that renames files from the FTPServer
     */
    public void renameFile() {
        if (Client.getFileSelec().contains(Client.getUser()) || Client.getUser().equals("admin")) {
            try {
                client.changeWorkingDirectory(Client.getDirecSelec());
                client.rename(Client.getFileSelec(), newName);
                Client.fillList(Client.getClient().listFiles());
                new BDLogic().insertMove(Client.getUserId(), "Rename", fic);
            } catch (IOException ioe) {

            }
        }
        else{
            JOptionPane.showMessageDialog(null,"No tienes permisos para renombrar el fichero");
        }
    }
}
