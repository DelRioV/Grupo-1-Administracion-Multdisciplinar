package controlador;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import vista.Client;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class UploadFiles {

    private String servidor = "127.0.0.1"; //servidor

    public void upload(String path, String fileName) {
        try {
            Client.getClient().changeWorkingDirectory(Client.getDirecSelec());
            Client.getClient().setFileType(FTP.BINARY_FILE_TYPE);
            BufferedInputStream in = new BufferedInputStream(
                    new FileInputStream(path));
            Client.getClient().storeFile(Client.getUser() + "_" + fileName, in);
            in.close(); //cerrar flujo

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

}
