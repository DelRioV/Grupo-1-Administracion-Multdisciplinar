package controlador;

import org.apache.commons.net.ftp.FTPClient;

import java.io.IOException;

public class RenameFiles {

    private FTPClient client;
    private String server;
    private String user;
    private String pasw;
    private String selectedFile;
    private String selectedFolder;
    private String newName;

    public RenameFiles(FTPClient client, String server, String user, String pasw, String selectedFile, String selectedFolder, String newName) {
        this.client = client;
        this.server = server;
        this.user = user;
        this.pasw = pasw;
        this.selectedFile = selectedFile;
        this.selectedFolder = selectedFolder;
        this.newName = newName;
    }

    public void renameFile(){
        try {
            client.connect(server);
            boolean login = client.login(user, pasw);
            client.changeWorkingDirectory(selectedFolder);
            client.rename(selectedFile, newName);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
