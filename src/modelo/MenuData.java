package modelo;

import controlador.Encrypt;
import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class MenuData {

    private static List<FTPFile> listFileDir;
    private static final long serialVersionUID = 1L;
    //Lista para los datos del directorio
    private static JList listDirec = new JList();
    //Datos del servidor FTP - Servidor local
    private static FTPClient client = new FTPClient();//cliente FTP
    private static String server = "127.0.0.1";
    private static String user = "admin";
    private static String pasw = "admin";
    private boolean login;
    static String direclnicial = "/";
    //para saber el directorio y fichero seleccionado
    private static String direcSelec = direclnicial;
    private static String fileSelec = "";
    public static String fic = "";

    public static int getUserId() {
        return userId;
    }

    private static int userId;
    private static String userName;
    private static String email;
    private static String emailKey;

    public MenuData(int userId, String userName, String email, String emailKey) throws IOException{
        this.userId = userId;
        this.userName = userName;
        this.email = email;
        this.emailKey = new Encrypt().decrypt(emailKey);
        //para ver los comandos que se originan
        getClient().addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out)));
        getClient().connect(getServer()); //conexión al servidor
        setLogin(getClient().login(getUser(), getPasw()));
        //Se establece el directorio de trabajo actual
        getClient().changeWorkingDirectory(direclnicial);
        //Oteniendo ficheros y directorios del directorio actual
        FTPFile[] files = getClient().listFiles();
        //Construyendo la lista de ficheros y directorios
        //del directorio de trabajo actual
        fillList(files);
        //Preparación de la lista
        //se configura el tipo de selección para que solo se pueda
        //seleccionar un elemento de la lista
        getListDirec().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        //ACCIONES AL PULSAR en la lista o en los botones
        getListDirec().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                try {
                    setListFileDir(Arrays.asList(getClient().listFiles(getDirecSelec())));
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                if (e.getValueIsAdjusting()) {
                    //elemento que se ha seleccionado de la lista
                    fic = getListDirec().getSelectedValue().toString();

                    if (getListDirec().getSelectedIndex() == 0) {
                        //Se hace clic en el primer elemento del JList
                        //se comprueba si es el directorio inicial
                        if (!fic.equals(direclnicial)) {
                            //si no estamos en el dictorio inicial, hay que
                            //subir al directorio padre
                            try {
                                getClient().changeToParentDirectory();
                                setDirecSelec(getClient().printWorkingDirectory());
                                FTPFile[] ff2 = null;
                                //directorio de trabajo actual=directorio padre
                                getClient().changeWorkingDirectory(getDirecSelec());
                                //se obtienen ficheros y directorios
                                ff2 = getClient().listFiles();
                                //se llena la lista con fich. del directorio padre
                                fillList(ff2);
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }
                        }
                    } //No se hace clic en el primer elemento del JList
                    //Puede ser un fichero o un directorio
                    else {
                        try {

                            if (fic.substring(0, 6).equals("(DIR) ")) {
                                //SE TRATA DE UN DIRECTORIO
                                try {
                                    fic = fic.substring(6);
                                    String direcSelec2 = "";
                                    if (getDirecSelec().equals("/"))
                                        direcSelec2 = getDirecSelec() + fic;
                                    else
                                        direcSelec2 = getDirecSelec() + "/" + fic;
                                    FTPFile[] ff2 = null;
                                    if (direcSelec2.contains(getUser()) || MenuData.getUser().equals("admin")) {
                                        getClient().changeWorkingDirectory(direcSelec2);
                                        ff2 = getClient().listFiles();
                                        //directorio actual
                                        setDirecSelec(direcSelec2);
                                        //se llena la lista con datos del directorio
                                        fillList(ff2);
                                    }
                                } catch (IOException e2) {
                                }
                            }

                        } catch (Exception ex) {

                        }

                    }
                }
                try {
                    setFileSelec(getListFileDir().get(getListDirec().getSelectedIndex() - 1).getName());
                    System.out.println(getListFileDir());
                    System.out.println(getDirecSelec());
                    System.out.println(getFileSelec());
                } catch (Exception ex) {
                }
            }
        });
    }

    public static void fillList(FTPFile[] files) {
        if (files == null) return;
        //se crea un objeto DefaultListModel
        DefaultListModel modeloLista = new DefaultListModel();
        modeloLista = new DefaultListModel();
        //se definen propiedades para la lista, color y tipo de fuente
        getListDirec().setForeground(Color.blue);
        Font fuente = new Font("Roboto Light", Font.PLAIN, 12);
        getListDirec().setFont(fuente);
        //se eliminan los elementos de la lista
        getListDirec().removeAll();
        try {
            //se establece el directorio de trabajo actual
            getClient().changeWorkingDirectory(getDirecSelec());
        } catch (IOException e) {
            e.printStackTrace();
        }
        //se añade el directorio de trabajo al listmodel, primer elemento
        modeloLista.addElement(getDirecSelec());
        //se recorre el array con los ficheros y directorios
        for (int i = 0; i < files.length; i++) {
            if (!(files[i].getName()).equals(".")
                    && !(files[i].getName()).equals("..")) {
                //nos saltamos los directorios . y ..
                //Se obtiene el nombre del fichero o directorio
                String f = files[i].getName();
                //Si es diretorio se añade al nombre (DIR)
                if (files[i].isDirectory()) f = "(DIR) " + f;
                //se añade el nombre del fie o direc al listmodel
                modeloLista.addElement(f);
            } //fin if
        }// fin for
        try {
            //se asigna el listmodel al JList,
            //se muestra en pantalla la lista de ficheros y direc
            getListDirec().setModel(modeloLista);
        } catch (NullPointerException n) {
            ; //Se produce al cambiar de directorio
        }

    }

    public static JList getListDirec() {
        return listDirec;
    }

    public static void setListDirec(JList listDirec) {
        MenuData.listDirec = listDirec;
    }

    public static FTPClient getClient() {
        return client;
    }

    public static void setClient(FTPClient client) {
        MenuData.client = client;
    }

    public static String getDirecSelec() {
        return direcSelec;
    }

    public static void setDirecSelec(String direcSelec) {
        MenuData.direcSelec = direcSelec;
    }

    public static String getFileSelec() {
        return fileSelec;
    }

    public static void setFileSelec(String fileSelec) {
        MenuData.fileSelec = fileSelec;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public static String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public static String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public static String getUserName() {
        return userName;
    }

    public void setUserName(String user) {
        this.user = userName;
    }

    public static String getPasw() {
        return pasw;
    }

    public void setPasw(String pasw) {
        this.pasw = pasw;
    }

    public static String getEmail() {
        return email;
    }

    public static void setEmail(String email) {
        MenuData.email = email;
    }

    public static String getEmailKey() {
        return emailKey;
    }

    public static void setEmailKey(String emailKey) {
        MenuData.emailKey = emailKey;
    }

    public boolean isLogin() {
        return login;
    }

    public void setLogin(boolean login) {
        this.login = login;
    }


    public static java.util.List<FTPFile> getListFileDir() {
        return listFileDir;
    }

    public static void setListFileDir(List<FTPFile> listFileDir) {
        MenuData.listFileDir = listFileDir;
    }

    public static String getAllMails(){
        String mails = "";
        try {
            ResultSet rs = new ConnectionDB().getRemoteConnection().executeQuery("select email from Users where Username not like 'Admin';");
            while (rs.next()){
                mails += rs.getString(1) + ",";
            }
            mails = mails.substring(0, mails.length()-1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return mails;
    }

    public static String getAdminMail(){
        try {
            ResultSet rs = new ConnectionDB().getRemoteConnection().executeQuery("select email from Users where Username like 'Admin';");
            rs.first();
            return rs.getString(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}
