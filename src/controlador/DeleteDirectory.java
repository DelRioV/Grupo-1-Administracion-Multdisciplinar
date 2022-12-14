package controlador;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import javax.swing.*;
import java.io.IOException;

public class DeleteDirectory {

    public DeleteDirectory(String direcSelec, FTPClient client, JTextField field) {
        String directoryName = JOptionPane.showInputDialog(null,
                "Introduce el nombre del directorio a eliminar", "carpeta");
        if (!(directoryName == null)) {
            String directory = direcSelec;
            if (!direcSelec.equals("/"))
                directory = directory + "/";
            //nombre del directorio a eliminar
            directory += directoryName.trim();
            try {
                if (client.removeDirectory(directory)) {
                    String message = directoryName.trim() + " => Se ha eliminado correctamente...";
                    JOptionPane.showMessageDialog(null, message);
                    field.setText(message);
                    client.changeWorkingDirectory(direcSelec);
                    FTPFile[] ff2 = null;
                    ff2 = client.listFiles();
                    //llenar la lista
                    //llenarLista(ff2, direcSelec);
                } else
                    JOptionPane.showMessageDialog(null,
                            directoryName.trim() +
                                    " => No se ha podido eliminar ...");
            } catch (IOException el) {
                el.printStackTrace();
            }
        }
    }
}
