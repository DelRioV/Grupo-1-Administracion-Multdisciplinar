package controlador;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import vista.Client;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
/**
 * @author -Ismael Orellana Bello
 *         -Pablo Salvador Del Río Vergara
 *         -Ángel Acedo Moreno
 *         -Javier Tienda
 *         -Jorge Luis López
 * @version 1.0
 * @date 23/12/2022
 * That class contains methods to upload files to the ftpServer
 */
public class UploadFiles {

    public void upload(String path, String fileName) {
        try {
            Client.getClient().changeWorkingDirectory(Client.getDirecSelec());
            Client.getClient().setFileType(FTP.BINARY_FILE_TYPE);
            BufferedInputStream in = new BufferedInputStream(
                    new FileInputStream(path));
            Client.getClient().storeFile(Client.getUser() + "_" + fileName, in);
            new BDLogic().insertMove(Client.getUserId(),"Upload",Client.fic);
            in.close(); //cerrar flujo
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

}
