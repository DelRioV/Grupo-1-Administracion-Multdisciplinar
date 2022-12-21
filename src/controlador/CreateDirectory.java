package controlador;

import modelo.MenuData;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
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
 * That class is for create directories in the FTPServer
 */
public class CreateDirectory implements ActionListener {

    //Client
    private FTPClient client;
    //Label
    private JLabel field;
    //FIC NAME
    private static String fic = "";

    /**
     * Constructor
     *
     * @param client -FTPClient the client
     * @param field -JLabel a label
     */
    public CreateDirectory(FTPClient client, JLabel field) {
        this.client = client;
        this.field = field;
    }

    /**
     * Method that creates a new dir in the server
     */
    public void creaDir() {
        String directoryName = JOptionPane.showInputDialog(null,
                "Introduce el nombre del directorio", "carpeta");
        System.out.println(MenuData.getDirecSelec());
        if (!(directoryName == null)) {
            String directorio = MenuData.getDirecSelec();
            if (!MenuData.getDirecSelec().equals("/")) directorio = directorio + "/";
            //nombre del directorio a crear
            directorio += MenuData.getUser() + "_" + directoryName.trim();//quita blancos a der e izd
            System.out.println(MenuData.getDirecSelec());
            try {
                if (MenuData.getClient().makeDirectory(directorio)) {
                    String m = directoryName.trim() + " => Se ha creado correctamente ...";
                    JOptionPane.showMessageDialog(null, m);
                    field.setText(m);
                    MenuData.getClient().changeWorkingDirectory(MenuData.getDirecSelec());
                    FTPFile[] ff2 = null;
                    //obtener ficheros del directorio actual
                    ff2 = client.listFiles();
                    fic = directorio;
                } else
                    JOptionPane.showMessageDialog(null,
                            directoryName.trim() + " => No se ha podido crear ...");
            } catch (IOException el) {
                el.printStackTrace();
            }
        }
    }

    /**
     * Method of the button
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        creaDir();
        try {
            MenuData.fillList(MenuData.getClient().listFiles());
            new BDLogic().insertMove(MenuData.getUserId(),"Create",fic);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
