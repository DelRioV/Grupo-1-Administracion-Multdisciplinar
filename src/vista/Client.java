package vista;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import controlador.CreateDirectory;
import controlador.DeleteDirectory;
import controlador.DownloadFile;
import controlador.UploadFiles;
import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.*;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Client extends JFrame {

    private static List<FTPFile> dirFileNameOnView = new ArrayList<FTPFile>();

    private static final long serialVersionUID = 1L;
    //Campos de cabecera parte superior
    static JLabel serverLabel = new JLabel();
    static JLabel userLabel = new JLabel();
    static JLabel rootLabel = new JLabel();
    //Campos de mensajes parte inferior
    private static JLabel directoryTree = new JLabel();
    private static JLabel field = new JLabel();
    //Botones
    JButton btnUpload = new JButton("Subir fichero");
    JButton btnDownload = new JButton("Descargar fichero");
    JButton btnDelFile = new JButton("Eliminar fichero");
    JButton btnCreateDir = new JButton("Crear carpeta");
    JButton btnDelDir = new JButton("Eliminar carpeta");
    JButton btnExit = new JButton("Salir");

    JButton btnRenameFile = new JButton("Renombrar fichero");

    //Lista para los datos del directorio
    private static JList listDirec = new JList();
    //contenedor
    private final Container c = getContentPane();
    //Datos del servidor FTP - Servidor local
    private static FTPClient client = new FTPClient();//cliente FTP
    private String server = "127.0.0.1";
    String user = "pablo";
    String pasw = "1234";
    boolean login;
    private static String direclnicial = "/";
    //para saber el directorio y fichero seleccionado
    private static String direcSelec = getDireclnicial();
    private static String fileSelec = "";

    public Client() throws IOException {
        super("CLIENTE BÁSICO FTP");
        //para ver los comandos que se originan
        getClient().addProtocolCommandListener(new PrintCommandListener
                (new PrintWriter(System.out)));
        getClient().connect(getServer()); //conexión al servidor
        login = getClient().login(user, pasw);
        //Se establece el directorio de trabajo actual
        getClient().changeWorkingDirectory(getDireclnicial());
        //Oteniendo ficheros y directorios del directorio actual
        FTPFile[] files = getClient().listFiles();
        //Construyendo la lista de ficheros y directorios
        //del directorio de trabajo actual
        fillList(files, getDireclnicial());
        //preparar campos de pantalla
        getDirectoryTree().setText("« ARBOL DE DIRECTORIOS CONSTRUIDO »");
        serverLabel.setText("Servidor FTP: " + getServer());
        userLabel.setText("Usuario: " + user);
        rootLabel.setText("DIRECTORIO RAIZ: " + getDireclnicial());
        //Preparación de la lista
        //se configura el tipo de selección para que solo se pueda
        //seleccionar un elemento de la lista
        getListDirec().setSelectionMode(ListSelectionModel.
                SINGLE_SELECTION);
        //barra de desplazamiento para la lista
        JScrollPane barraDesplazamiento = new
                JScrollPane(getListDirec());
        barraDesplazamiento.setPreferredSize
                (new Dimension(335, 420));
        barraDesplazamiento.setBounds(new Rectangle
                (5, 65, 335, 420));
        c.add(barraDesplazamiento);
        c.setLayout(new FlowLayout());
        //se añaden el resto de los campos de pantalla
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(510, 600);
        //ACCIONES AL PULSAR en la lista
        getListDirec().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (e.getValueIsAdjusting()) {
                    setFileSelec("");
                    //elemento que se ha seleccionado de la lista
                    String fic = getListDirec().getSelectedValue().toString();

                    if (getListDirec().getSelectedIndex() == 0) {
                        //Se hace clic en el primer elemento del JList
                        //se comprueba si es el directorio inicial
                        if (!fic.equals(getDireclnicial())) {
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
                                getDirectoryTree().setText("");
                                //se llena la lista con fich. del directorio padre
                                fillList(ff2, getDirecSelec());
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }
                        }
                    } //No se hace clic en el primer elemento del JList
                    //Puede ser un fichero o un directorio
                    else {
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
                                getClient().changeWorkingDirectory(direcSelec2);
                                ff2 = getClient().listFiles();
                                getDirectoryTree().setText("DIRECTORIO: " + fic + ", "
                                        + ff2.length + " elementos");
                                //directorio actual
                                setDirecSelec(direcSelec2);
                                //se llena la lista con datos del directorio
                                fillList(ff2, getDirecSelec());
                            } catch (IOException e2) {
                                e2.printStackTrace();
                            }
                        } else {
                            try {
                                setFileSelec(dirFileNameOnView.get(listDirec.getSelectedIndex() - 1).getName());
                            } catch (Exception ex) {
                            }
                        }
                    }
                }
                System.out.println(getFileSelec());
                System.out.println(getDirecSelec());
            }
        });
        btnCreateDir.addActionListener(new CreateDirectory(getClient(), getField()));
        btnDelDir.addActionListener(new DeleteDirectory(getClient(), getField()));
        btnDownload.addActionListener(new DownloadFile(getClient()));
        //btnUpload.addActionListener(new UploadFiles(user, pasw, client));
        add(serverLabel);
        add(userLabel);
        add(rootLabel);
        add(getDirectoryTree());
        add(btnCreateDir);
        add(btnDelDir);
        add(btnUpload);
        add(btnDownload);
        add(btnDelFile);
        add(btnRenameFile);
        add(btnExit);
        setVisible(true);

    }

    public static void fillList(FTPFile[] files, String direc2) {
        if (files == null) return;
        //se crea un objeto DefaultListModel
        DefaultListModel modeloLista = new DefaultListModel();
        modeloLista = new DefaultListModel();
        //se definen propiedades para la lista, color y tipo de fuente
        getListDirec().setForeground(Color.blue);
        Font fuente = new Font("Courier", Font.PLAIN, 12);
        getListDirec().setFont(fuente);
        //se eliminan los elementos de la lista
        getListDirec().removeAll();
        try {
            //se establece el directorio de trabajo actual
            getClient().changeWorkingDirectory(direc2);
        } catch (IOException e) {
            e.printStackTrace();
        }
        setDirecSelec(direc2); //directorio actual
        //se añade el directorio de trabajo al listmodel, primer elemento
        modeloLista.addElement(direc2);
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
            dirFileNameOnView = Arrays.asList(getClient().listFiles());

        } catch (NullPointerException n) {
            ; //Se produce al cambiar de directorio
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static void main(String[] args) throws IOException {
        new Client();
    }

    public static JLabel getDirectoryTree() {
        return directoryTree;
    }

    public static void setDirectoryTree(JLabel directoryTree) {
        Client.directoryTree = directoryTree;
    }

    public static JLabel getField() {
        return field;
    }

    public static void setField(JLabel field) {
        Client.field = field;
    }

    public static JList getListDirec() {
        return listDirec;
    }

    public static void setListDirec(JList listDirec) {
        Client.listDirec = listDirec;
    }

    public static FTPClient getClient() {
        return client;
    }

    public static void setClient(FTPClient client) {
        Client.client = client;
    }

    public static String getDireclnicial() {
        return direclnicial;
    }

    public static void setDireclnicial(String direclnicial) {
        Client.direclnicial = direclnicial;
    }

    public static String getDirecSelec() {
        return direcSelec;
    }

    public static void setDirecSelec(String direcSelec) {
        Client.direcSelec = direcSelec;
    }

    public static String getFileSelec() {
        return fileSelec;
    }

    public static void setFileSelec(String fileSelec) {
        Client.fileSelec = fileSelec;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }
}

