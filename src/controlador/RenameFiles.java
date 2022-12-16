package controlador;

import org.apache.commons.net.ftp.FTPClient;
import vista.Client;

import java.io.IOException;

public class RenameFiles {
    private FTPClient client;
    private String newName;

    public RenameFiles(FTPClient client, String newName) {
        this.client = client;
        this.newName = newName;
    }

    public void renameFile() {
        try {
            client.changeWorkingDirectory(Client.getDirecSelec());
            client.rename(Client.getFileSelec(), newName);
            Client.fillList(Client.getClient().listFiles());
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
