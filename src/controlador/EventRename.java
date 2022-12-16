package controlador;

import org.apache.commons.net.ftp.FTPClient;
import vista.Client;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EventRename implements ActionListener {

    private FTPClient client;
    private String server;
    private String user;
    private String pasw;


    public EventRename(FTPClient client, String server, String user, String pasw) {
        this.client = client;
        this.server = server;
        this.user = user;
        this.pasw = pasw;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!Client.getFileSelec().equals("") && Client.getFileSelec().contains(Client.getUser())) {
            String newFileName = JOptionPane.showInputDialog("Nuevo nombre del fichero");
            new RenameFiles(client, Client.getUser() + "_" + newFileName).renameFile();
        } else {
            JOptionPane.showMessageDialog(null, "Selecciona un fichero antes");
        }
    }
}
