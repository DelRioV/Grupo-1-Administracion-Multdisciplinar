package controlador;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class UploadFiles {

    private String servidor = "127.0.0.1"; //servidor

    public void upload(String user,String pasw,String path,String fileName){
        FTPClient cliente = new FTPClient();
        try {
            cliente.connect(servidor);
            boolean login = cliente.login(user, pasw);
            String direc = "/NUEVODIREC";
            if (login) {
                cliente.changeWorkingDirectory(direc);
                cliente.setFileType(FTP.BINARY_FILE_TYPE);
                BufferedInputStream in = new BufferedInputStream(
                        new FileInputStream(path));
                cliente.storeFile(fileName, in);
                in.close(); //cerrar flujo
                cliente.logout(); //logout del usuario
                cliente.disconnect(); //desconexión del servidor
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

}
