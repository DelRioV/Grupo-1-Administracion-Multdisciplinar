package controlador;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import vista.Client;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
/**
 * @author -Ismael Orellana Bello
 *         -Pablo Salvador Del Río Vergara
 *         -Ángel Acedo Moreno
 *         -Javier Tienda
 *         -Jorge Luis López
 * @version 1.0
 * @date 23/12/2022
 * That class contains methods that download files to your PC from the FTPServer
 */
public class DownloadFile implements ActionListener {
    //Client
    private FTPClient client;

    /**
     * Constructor
     *
     * @param client -FTPClient client
     */
    public DownloadFile(FTPClient client) {
        this.client = client;
    }

    //to Know the selected directory
    private String selectedFile = "", initialFolder = "/", selectedFolder = initialFolder;

    /**
     * Method that get the selectedFile
     */
    public void dlFile() { // Parametros: Cliente FTP, la carpeta SIN el archivo, y el archivo
        if (selectedFolder != null)
            this.selectedFolder = Client.getDirecSelec();
        if (selectedFile != null)
            this.selectedFile = Client.getFileSelec();

        String directorio = this.selectedFolder;
        if (!this.selectedFolder.equals("/"))
            directorio = directorio + "/";
        if (!this.selectedFile.equals(""))
            downloadFile(directorio + Client.getFileSelec(), Client.getFileSelec());
        else
            JOptionPane.showMessageDialog(null, "No se ha seleccionado ningun archivo");
    }

    /**
     * Method that download files from FTPServer
     *
     * @param NombreCompleto  -String full ame
     * @param nombreFichero   -String file name
     */
    private void downloadFile(String NombreCompleto, String nombreFichero) {
        System.out.println(nombreFichero);
        File file;
        String fullRute = "";
        String folder = "";
        JFileChooser f = new JFileChooser();
        f.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);  //solo se pueden seleccionar directorios
        f.setDialogTitle("Selecciona el Directorio donde DESCARGAR el fichero"); //titulo de la ventana
        int returnVal = f.showDialog(null, "Descargar");
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            file = f.getSelectedFile();

            folder = (file.getAbsolutePath()).toString(); //Carpeta donde se guardara el archivo
            fullRute = folder + File.separator + nombreFichero;  // Ruta completa
            System.out.println(fullRute);
            try {
                client.setFileType(FTP.BINARY_FILE_TYPE);
                BufferedOutputStream out = new BufferedOutputStream(
                        new FileOutputStream(fullRute));
                if (client.retrieveFile(NombreCompleto, out)) {
                    JOptionPane.showMessageDialog(null, nombreFichero
                            + " => Se ha descargado correctamente ...");
                    new BDLogic().insertMove(Client.getUserId(), "Download", file.getName());
                }
                else
                    JOptionPane.showMessageDialog(null, nombreFichero
                            + " => No se ha podido descargar ...");
                out.close();
            } catch (IOException el) {
                el.printStackTrace();
            }
        }

    }

    /**
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (Client.getFileSelec().contains(Client.getUser()) || Client.getUser().equals("admin")) {
            dlFile();
            try {
                Client.fillList(Client.getClient().listFiles());
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
