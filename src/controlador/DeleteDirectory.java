package controlador;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import vista.Client;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
/**
 * @author -Ismael Orellana Bello
 *         -Pablo Salvador Del Río Vergara
 *         -Ángel Acedo Moreno
 *         -Javier Tienda
 *         -Jorge Luis López
 * @version 1.0
 * @date 23/12/2022
 * That class contains methods that delete Directories in the FTPServer
 */
public class DeleteDirectory implements ActionListener {
    //Client
    private FTPClient client;
    //JLabel
    private JLabel field;

    /**
     * Constructor of the class
     *
     * @param client -FTPCLient the client
     * @param field -JLabel a label
     */
    public DeleteDirectory(FTPClient client, JLabel field){
        this.client = client;
        this.field = field;
    }

    /**
     * Method that deletes directories in the FTPServer
     */
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
                    new BDLogic().insertMove(Client.getUserId(), "Delete", directory);
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

    /**
     *
     * @param e the event to be processed
     */
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
