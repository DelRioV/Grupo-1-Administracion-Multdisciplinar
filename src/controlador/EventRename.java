package controlador;
import org.apache.commons.net.ftp.FTPClient;
import vista.Client;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * @author -Ismael Orellana Bello
 *         -Pablo Salvador Del Río Vergara
 *         -Ángel Acedo Moreno
 *         -Javier Tienda
 *         -Jorge Luis López
 * @version 1.0
 * @date 23/12/2022
 * That class contains methods that rename directories in the FTPServer
 */
public class EventRename implements ActionListener {

    //Client
    private FTPClient client;
    //Server
    private String server;
    //User
    private String user;
    //Pasw
    private String pasw;

    /**
     * Constructor of the class
     *
     * @param client
     * @param server
     * @param user
     * @param pasw
     */
    public EventRename(FTPClient client, String server, String user, String pasw) {
        this.client = client;
        this.server = server;
        this.user = user;
        this.pasw = pasw;

    }

    /**
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (!Client.getFileSelec().equals("") && Client.getFileSelec().contains(Client.getUser()) || Client.getUser().equals("admin")) {
            String newFileName = JOptionPane.showInputDialog("Nuevo nombre del fichero");
            RenameFiles.fic = newFileName;
            new RenameFiles(client, Client.getUser() + "_" + newFileName).renameFile();
        } else {
            JOptionPane.showMessageDialog(null, "Selecciona un fichero antes");
        }
    }
}
