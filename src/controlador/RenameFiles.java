package controlador;

import org.apache.commons.net.ftp.FTPClient;
import vista.Client;

import java.io.IOException;

public class RenameFiles {

    private FTPClient client;
    private String server;
    private String user;
    private String pasw;
    private String newName;

    public RenameFiles(FTPClient client, String server, String user, String pasw, String newName) {
        this.client = client;
        this.server = server;
        this.user = user;
        this.pasw = pasw;

        this.newName = newName;
    }

    public void renameFile() {
        try {
            client.changeWorkingDirectory(Client.getDirecSelec());
            client.rename(Client.getFileSelec(), newName);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
