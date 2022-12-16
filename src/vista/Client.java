package vista;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import controlador.UploadEvent;
import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.*;

import java.awt.*;
import java.io.*;

public class Client extends JFrame {
    private static final long serialVersionUID = 1L;
    //Campos de cabecera parte superior
    static JLabel serverLabel = new JLabel();
    static JLabel userLabel = new JLabel();
    static JLabel rootLabel = new JLabel();
    //Campos de mensajes parte inferior
    private static JLabel directoryTree = new JLabel();
    //Botones
    JButton btnUpload = new JButton("Subir fichero");
    JButton btnDownload = new JButton("Descargar fichero");
    JButton btnDelFile = new JButton("Eliminar fichero");
    JButton btnCreateDir = new JButton("Crear carpeta");
    JButton btnDelDir = new JButton("Eliminar carpeta");
    JButton btnExit = new JButton("Salir");

    JButton btnRenameFile = new JButton("Renombrar fichero");

    //Lista para los datos del directorio
    static JList listDirec = new JList();
    //contenedor
    private final Container c = getContentPane();
    //Datos del servidor FTP - Servidor local
    static FTPClient client = new FTPClient();//cliente FTP
    String server = "127.0.0.1";
    String user = "pablo";
    String pasw = "1234";
    boolean login;
    static String direclnicial = "/";
    //para saber el directorio y fichero seleccionado
    static String direcSelec = direclnicial;
    static String ficheroSelec = "";

    public Client() throws IOException {
        super("CLIENTE BÁSICO FTP");
        //para ver los comandos que se originan
        client.addProtocolCommandListener(new PrintCommandListener
                (new PrintWriter(System.out)));
        client.connect(server); //conexión al servidor
        login = client.login(user, pasw);
        //Se establece el directorio de trabajo actual
        client.changeWorkingDirectory(direclnicial);
        //Oteniendo ficheros y directorios del directorio actual
        FTPFile[] files = client.listFiles();
        //Construyendo la lista de ficheros y directorios
        //del directorio de trabajo actual
        fillList(files, direclnicial);
        //preparar campos de pantalla
        directoryTree.setText("« ARBOL DE DIRECTORIOS CONSTRUIDO »");
        serverLabel.setText("Servidor FTP: " + server);
        userLabel.setText("Usuario: " + user);
        rootLabel.setText("DIRECTORIO RAIZ: " + direclnicial);
        //Preparación de la lista
        //se configura el tipo de selección para que solo se pueda
        //seleccionar un elemento de la lista
        listDirec.setSelectionMode(ListSelectionModel.
                SINGLE_SELECTION);
        //barra de desplazamiento para la lista
        JScrollPane barraDesplazamiento = new
                JScrollPane(listDirec);
        barraDesplazamiento.setPreferredSize
                (new Dimension(335, 420));
        barraDesplazamiento.setBounds(new Rectangle
                (5, 65, 335, 420));
        c.add(barraDesplazamiento);
        c.setLayout(new FlowLayout());
        //se añaden el resto de los campos de pantalla
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(510, 600);
        //ACCIONES AL PULSAR en la lista o en los botones
        listDirec.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (e.getValueIsAdjusting()) {
                    ficheroSelec = "";
                    //elemento que se ha seleccionado de la lista
                    String fic = listDirec.getSelectedValue().toString();

                    if (listDirec.getSelectedIndex() == 0) {
                        //Se hace clic en el primer elemento del JList
                        //se comprueba si es el directorio inicial
                        if (!fic.equals(direclnicial)) {
                            //si no estamos en el dictorio inicial, hay que
                            //subir al directorio padre
                            try {
                                client.changeToParentDirectory();
                                direcSelec = client.printWorkingDirectory();
                                FTPFile[] ff2 = null;
                                //directorio de trabajo actual=directorio padre
                                client.changeWorkingDirectory(direcSelec);
                                //se obtienen ficheros y directorios
                                ff2 = client.listFiles();
                                directoryTree.setText("");
                                //se llena la lista con fich. del directorio padre
                                fillList(ff2, direcSelec);
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
                                if (direcSelec.equals("/"))
                                    direcSelec2 = direcSelec + fic;
                                else
                                    direcSelec2 = direcSelec + "/" + fic;
                                FTPFile[] ff2 = null;
                                client.changeWorkingDirectory(direcSelec2);
                                ff2 = client.listFiles();
                                directoryTree.setText("DIRECTORIO: " + fic + ", "
                                        + ff2.length + " elementos");
                                //directorio actual
                                direcSelec = direcSelec2;
                                //se llena la lista con datos del directorio
                                fillList(ff2, direcSelec);
                            } catch (IOException e2) {
                                e2.printStackTrace();
                            }
                        }
                    }
                }
            }
        });
        add(serverLabel);
        add(userLabel);
        add(rootLabel);
        add(directoryTree);
        add(btnCreateDir);
        add(btnDelDir);
        add(btnUpload);
        add(btnDownload);
        add(btnDelFile);
        add(btnRenameFile);
        add(btnExit);
        btnUpload.addActionListener(new UploadEvent());
        setVisible(true);

    }

    public static void fillList(FTPFile[] files, String direc2) {
        if (files == null) return;
        //se crea un objeto DefaultListModel
        DefaultListModel modeloLista = new DefaultListModel();
        modeloLista = new DefaultListModel();
        //se definen propiedades para la lista, color y tipo de fuente
        listDirec.setForeground(Color.blue);
        Font fuente = new Font("Courier", Font.PLAIN, 12);
        listDirec.setFont(fuente);
        //se eliminan los elementos de la lista
        listDirec.removeAll();
        try {
            //se establece el directorio de trabajo actual
            client.changeWorkingDirectory(direc2);
        } catch (IOException e) {
            e.printStackTrace();
        }
        direcSelec = direc2; //directorio actual
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
            listDirec.setModel(modeloLista);
        } catch (NullPointerException n) {
            ; //Se produce al cambiar de directorio
        }

    }
    public static void main(String[] args) throws IOException {
        new Client();
    }
}

