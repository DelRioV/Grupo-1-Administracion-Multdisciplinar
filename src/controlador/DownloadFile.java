package controlador;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

import javax.swing.*;
import java.io.*;

public class DownloadFile {
    //para saber el directorio y fichero seleccionado
    private String selectedFile = "", initialFolder = "/", selectedFolder = initialFolder;

    public DownloadFile(FTPClient client, String selectedFolder, String selectedFile) { // Parametros: Cliente FTP, la carpeta SIN el archivo, y el archivo
        if (selectedFolder != null)
            this.selectedFolder = selectedFolder;
        if (selectedFile != null)
            this.selectedFile = selectedFile;

        String directorio = this.selectedFolder;
        if (!this.selectedFolder.equals("/"))
            directorio = directorio + "/";
        if (!this.selectedFile.equals(""))
            downloadFile(directorio + this.selectedFile, this.selectedFile, client);
        else
            JOptionPane.showMessageDialog(null, "No se ha seleccionado ningun archivo");
    }

    private void downloadFile(String NombreCompleto, String nombreFichero, FTPClient client) {
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
            try {
                client.setFileType(FTP.BINARY_FILE_TYPE);
                BufferedOutputStream out = new BufferedOutputStream(
                        new FileOutputStream(fullRute));
                if (client.retrieveFile(NombreCompleto, out))
                    JOptionPane.showMessageDialog(null, nombreFichero
                            + " => Se ha descargado correctamente ...");
                else
                    JOptionPane.showMessageDialog(null, nombreFichero
                            + " => No se ha podido descargar ...");
                out.close();
            } catch (IOException el) {
                el.printStackTrace();
            }
        }

    }
}
