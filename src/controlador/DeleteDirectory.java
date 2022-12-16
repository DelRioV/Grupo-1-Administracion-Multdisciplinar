package controlador;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import vista.Client;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class DeleteDirectory implements ActionListener {
    private FTPClient client;
    private JLabel field;

    public DeleteDirectory(FTPClient client, JLabel field){
        this.client = client;
        this.field = field;
    }
    public void delDir() {
        String directoryName = JOptionPane.showInputDialog(null,
                "Introduce el nombre del directorio a eliminar", "carpeta");
        if (!(directoryName == null)) {
            String directory = Client.getDirecSelec();
            if (!Client.getDirecSelec().equals("/"))
                directory = directory + "/";
            //nombre del directorio a eliminar
            directory += directoryName.trim();
            try {
                if (client.removeDirectory(directory)) {
                    String message = directoryName.trim() + " => Se ha eliminado correctamente...";
                    JOptionPane.showMessageDialog(null, message);
                    field.setText(message);
                    client.changeWorkingDirectory(Client.getDirecSelec());
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

    @Override
    public void actionPerformed(ActionEvent e) {
        delDir();
        try {
            Client.fillList(Client.getClient().listFiles());
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
