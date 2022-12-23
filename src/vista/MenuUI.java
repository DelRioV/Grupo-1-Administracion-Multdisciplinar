package vista;

import controlador.*;
import modelo.MenuData;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuUI extends JFrame {

    private JButton btnMail;
    private JButton btnSendMail;
    private JButton btnDelDir;
    private JButton btnCreateDir;
    private JButton btnDownload;
    private JButton btnUpload;
    private JButton btnDelFile;
    private JButton btnRenameFile;
    private JPanel pnlMenu;
    private JPanel pnlFTP;
    private JScrollPane barraDesplazamiento;
    private JPanel pnlMain;
    private JButton btnLogOut;
    private JLabel lblUserName;
    private static JLabel field = new JLabel();

    public MenuUI(){
        super("CLIENTE BÁSICO FTP");
        btnMail.addActionListener(new InboxWindowManager());
        btnSendMail.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SendMailWindowManager();
            }
        });
        btnLogOut.addActionListener(new LogOutEvent());
        btnDelDir.addActionListener(new DeleteDirectory(MenuData.getClient(), field));
        btnCreateDir.addActionListener(new CreateDirectory(MenuData.getClient(), field));
        btnDownload.addActionListener(new DownloadFile(MenuData.getClient()));
        btnUpload.addActionListener(new UploadEvent());
        btnDelFile.addActionListener(new DeleteFiles());
        btnRenameFile.addActionListener(new EventRename(MenuData.getClient(), MenuData.getServer(), MenuData.getUser(), MenuData.getPasw()));

    }

    public void setDifferentProperties(){
        setIconImage(new ImageIcon("src/modelo/resources/ftp.png").getImage());
        setContentPane(pnlMain);
        setMinimumSize(new Dimension(900, 500));
        setLocationRelativeTo(new JFrame());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
    }

    private void createUIComponents() {
        lblUserName = new JLabel(MenuData.getUserName());
        barraDesplazamiento = new JScrollPane(MenuData.getListDirec());
    }
}
