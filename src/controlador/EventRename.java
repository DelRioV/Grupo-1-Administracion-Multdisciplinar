package controlador;

import org.apache.commons.net.ftp.FTPClient;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EventRename implements ActionListener {

    private FTPClient client;
    private String server;
    private String user;
    private String pasw;
    private String selectedFile;
    private String selectedFolder;

    public EventRename(FTPClient client, String server, String user, String pasw, String selectedFile, String selectedFolder){
        this.client = client;
        this.server = server;
        this.user = user;
        this.pasw = pasw;
        this.selectedFile = selectedFile;
        this.selectedFolder = selectedFolder;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String newFileName = JOptionPane.showInputDialog("Nuevo nombre del fichero");
        new RenameFiles(client, server, user, pasw, selectedFile, selectedFolder, newFileName).renameFile();
    }
}
