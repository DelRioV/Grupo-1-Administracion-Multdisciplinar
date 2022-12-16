package controlador;

import org.apache.commons.net.ftp.FTPClient;
import vista.Client;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class DeleteFiles implements ActionListener {

    public DeleteFiles(){
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
        File f = new File(Client.getFileSelec());
            System.out.println(Client.getFileSelec());
                if (Client.getClient().deleteFile(f.getPath())) {
                    JOptionPane.showMessageDialog(null,"Archivo borrado");
                }
                else{
                    JOptionPane.showMessageDialog(null,"No se ha podido borrar el archivo");
                }
        }
        catch (IOException ex) {
            JOptionPane.showMessageDialog(null,"No se ha podido borrar el archivo");
        }
    }
}
