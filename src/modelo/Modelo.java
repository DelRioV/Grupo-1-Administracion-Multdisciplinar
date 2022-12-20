package modelo;

import java.awt.*;

public class Modelo {

    private final static String LOGINWINDOWNAME = "Ventana de inicio de sesión";
    private final static int LOGINWINDOWNUMBOTONES = 1;
    private final static int LOGINWINDOWNUMPANELS = 1;
    private final static int LOGINWINDOWNUMTEXTFIELDS = 2;
    private final static int LOGINWINDOWNUMLABELS = 2;
    private final static String [] LOGINWINDOWLABELSNAMES = {"Nombre de usuario","Contraseña"};
    private final static String LOGINWINDOWBUTTONSAME = "Iniciar sesión";

    private final static int LENGTHTEXTFIELDS = 20;

    private final static String SENDMAILWINDOWNAME = "Enviar correo electrónico";
    private final static int SENDMAILWINDOWNUMBTNS = 2;
    private final static int SENDMAILWINDOWTNUMEXTFIELDS = 2;
    private final static int SENDMAILWINDOWNUMTEXTAREA = 1;
    private final static int SENDMAILWINDOWNUMLABELS = 3;
    private final static int SENDMAILWINDOWNUMPANELS = 1;
    private final static String[] SENDMAILWINDOWLABELTXT = {"Correo", "Asunto", "Mensaje"};
    private final static String[] SENDMAILWINDOWBTNSTXT = {"Volver", "Enviar"};

    private final static String[] UPLOADWINDOSLABELS = {"Ruta de tu ordenador","Nombre del fichero"};

    private final static String UPLOADWINDOWBUTTONNAME = "Subir archivo";


    public int getLengthtextfields(){
        return LENGTHTEXTFIELDS;
    }
    public String getLoginwindowname(){
        return LOGINWINDOWNAME;
    }
    public int getLoginwindownumbotones(){
        return LOGINWINDOWNUMBOTONES;
    }
    public int getLoginwindownumpanels(){
        return LOGINWINDOWNUMPANELS;
    }
    public int getLoginwindownumtextfields(){
        return LOGINWINDOWNUMTEXTFIELDS;
    }
    public int getLoginwindownumlabels(){
        return LOGINWINDOWNUMLABELS;
    }
    public String [] getLoginwindowlabelsnames(){
        return LOGINWINDOWLABELSNAMES;
    }
    public String getLoginwindowbuttonsame(){
     return LOGINWINDOWBUTTONSAME;
    }

    public static String getSENDMAILWINDOWNAME() {
        return SENDMAILWINDOWNAME;
    }
    public static int getSENDMAILWINDOWNUMBTNS() {
        return SENDMAILWINDOWNUMBTNS;
    }
    public static int getSENDMAILWINDOWTNUMEXTFIELDS() {
        return SENDMAILWINDOWTNUMEXTFIELDS;
    }
    public static int getSENDMAILWINDOWNUMTEXTAREA() {
        return SENDMAILWINDOWNUMTEXTAREA;
    }
    public static int getSENDMAILWINDOWNUMLABELS() {
        return SENDMAILWINDOWNUMLABELS;
    }
    public static int getSENDMAILWINDOWNUMPANELS() {
        return SENDMAILWINDOWNUMPANELS;
    }
    public static String[] getSENDMAILWINDOWLABELTXT() {
        return SENDMAILWINDOWLABELTXT;
    }
    public static String[] getSENDMAILWINDOWBTNSTXT() {
        return SENDMAILWINDOWBTNSTXT;
    }




    private static final int INBOXWINDOWNUMBUTTONS = 1,
            INBOXWINDOWJTABLE = 1,
            INBOXWINDOWJBUTTON = 1,
            INBOXWINDOWJPANEL = 1;

    private static final String[] INBOXWINDOWJBUTTONTXT = {"Volver"}, INBOXLBLTEXT = {"prueba@prueba.com"}, INBOXWINDOWTABLEHEADERS = {"Usuario", "Mensaje"};

    private static final String INBOXWINDOWNAME = "Buzon de correo";

    public final static  Color bgColorInboxJTable = new Color(245, 195, 195),
            bgColorInboxNorthSouthBorderLayout = new Color(248, 224, 147),
            bgColorInboxButton = new Color(248, 224, 147),
            bgColorInboxButtonBorder = new Color(248, 224, 147),
            bgColorInboxPanel = new Color(248, 224, 147);

    public static final int windowsInboxWidth = 500, windowsInboxHeight = 500;
    public static final Font fontInboxJTable = new Font("Consolas", Font.PLAIN, 22);
    public static String getINBOXWINDOWNAME() {
        return INBOXWINDOWNAME;
    }

    public static int getINBOXWINDOWJPANEL() {
        return INBOXWINDOWJPANEL;
    }

    public static int getINBOXWINDOWNUMBUTTONS() {
        return INBOXWINDOWNUMBUTTONS;
    }


    public static int getINBOXWINDOWJTABLE() {
        return INBOXWINDOWJTABLE;
    }


    public static int getINBOXWINDOWJBUTTON() {
        return INBOXWINDOWJBUTTON;
    }


    public static String[] getINBOXWINDOWJBUTTONTXT() {
        return INBOXWINDOWJBUTTONTXT;
    }


    public static String[] getINBOXWINDOWJLABELTXT() {
        return INBOXWINDOWJLABELTXT;
    }

    public static int getINBOXWINDOWJLABEL() {
        return INBOXWINDOWJLABEL;
    }
    public static String[] getINBOXLBLTEXT() {
        return INBOXLBLTEXT;
    }
    public static String[] getINBOXWINDOWTABLEHEADERS() {
        return INBOXWINDOWTABLEHEADERS;
    }

    private static String[] INBOXWINDOWJLABELTXT = {"prueba@prueba.com"};
    private static int  INBOXWINDOWJLABEL = 1;


}
