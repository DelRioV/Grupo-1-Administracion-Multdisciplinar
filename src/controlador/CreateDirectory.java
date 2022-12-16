package controlador;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import vista.Client;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class CreateDirectory implements ActionListener {

    private FTPClient client;
    private JLabel field;

    public CreateDirectory(FTPClient client,JLabel field) {
        this.client = client;
        this.field = field;
    }

    public void creaDir() {
        String directoryName = JOptionPane.showInputDialog(null,
                "Introduce el nombre del directorio", "carpeta");
        System.out.println(Client.getDirecSelec());
        if (!(directoryName == null)) {
            String directorio = Client.getDirecSelec();
            if (!Client.getDirecSelec().equals("/")) directorio = directorio + "/";
            //nombre del directorio a crear
            directorio += directoryName.trim();//quita blancos a der e izd
            System.out.println(Client.getDirecSelec());
            try {
                if (Client.getClient().makeDirectory(directorio)) {
                    String m = directoryName.trim() + " => Se ha creado correctamente ...";
                    JOptionPane.showMessageDialog(null, m);
                    field.setText(m);
                    Client.getClient().changeWorkingDirectory(Client.getDirecSelec());
                    FTPFile[] ff2 = null;
                    //obtener ficheros del directorio actual
                    ff2 = client.listFiles();
                } else
                    JOptionPane.showMessageDialog(null,
                            directoryName.trim() + " => No se ha podido crear ...");
            } catch (IOException el) {
                el.printStackTrace();
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        creaDir();
    }
}
