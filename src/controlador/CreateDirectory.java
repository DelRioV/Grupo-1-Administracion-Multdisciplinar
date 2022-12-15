package controlador;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import javax.swing.*;
import java.io.IOException;

public class CreateDirectory {

    public void creaDir(String direcSelec, FTPClient client, JTextField field){
        String directoryName= JOptionPane.showInputDialog(null,
                "Introduce el nombre del directorio", "carpeta");
        if (!(directoryName == null)) {
            String directorio = direcSelec;
            if (!direcSelec.equals("/")) directorio = directorio + "/";
            //nombre del directorio a crear
            directorio += directoryName.trim();//quita blancos a der e izd
            try {
                if (client.makeDirectory(directorio)) {
                    String m = directoryName.trim() + " => Se ha creado correctamente ...";
                    JOptionPane.showMessageDialog(null, m);
                    field.setText(m);
                    client.changeWorkingDirectory(direcSelec);
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
}
