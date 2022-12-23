package controlador;

import modelo.MenuData;
import org.apache.commons.net.ftp.FTPClient;
import javax.swing.*;
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
        if (MenuData.getFileSelec().contains(MenuData.getUser()) || MenuData.getUser().equals("admin")) {
            try {
                client.changeWorkingDirectory(MenuData.getDirecSelec());
                client.rename(MenuData.getFileSelec(), newName);
                MenuData.fillList(MenuData.getClient().listFiles());
                new BDLogic().insertMove(MenuData.getUserId(), "Rename", fic);
            } catch (IOException ioe) {

            }
        }
        else{
            JOptionPane.showMessageDialog(null,"No tienes permisos para renombrar el fichero");
        }
    }
}
