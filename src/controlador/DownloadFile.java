package controlador;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import vista.Client;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class DownloadFile implements ActionListener {
    private FTPClient client;

    public DownloadFile(FTPClient client) {
        this.client = client;
    }

    //para saber el directorio y fichero seleccionado
    private String selectedFile = "", initialFolder = "/", selectedFolder = initialFolder;

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
