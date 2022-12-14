package modelo;

import java.awt.*;

/**
 * @author -Ismael Orellana Bello
 * -Pablo Salvador Del Río Vergara
 * -Ángel Acedo Moreno
 * -Javier Tienda
 * -Jorge Luis López
 * -José Ramón Gallego
 * @version 1.0
 * @date 23/12/2022
 * That class contains information about all the project Information
 */
public class Modelo {

    // A lot of constants and Getter and Setters

    private final static int LENGTHTEXTFIELDS = 20;

    private final static String SENDMAILWINDOWNAME = "Enviar correo electrónico";
    private final static int SENDMAILWINDOWNUMBTNS = 3;
    private final static int SENDMAILWINDOWTNUMEXTFIELDS = 1;
    private final static int SENDMAILWINDOWNUMTEXTAREA = 1;
    private final static int SENDMAILWINDOWNUMLABELS = 2;
    private final static int SENDMAILWINDOWNUMPANELS = 2;
    private final static int SENDMAILWINDOWHEIGTH = 350;
    private final static int SENDMAILWINDOWWIDTH = 700;
    private final static String[] SENDMAILWINDOWLABELTXT = {"Asunto", "Mensaje"};
    private final static String[] SENDMAILWINDOWBTNSTXT = {"Cerrar", "Enviar", "Adjuntar Archivo: "};

    public int getLengthtextfields() {
        return LENGTHTEXTFIELDS;
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

    public static int getSENDMAILWINDOWHEIGTH() {
        return SENDMAILWINDOWHEIGTH;
    }

    public static int getSENDMAILWINDOWWIDTH() {
        return SENDMAILWINDOWWIDTH;
    }


    private static final int INBOXWINDOWNUMBUTTONS = 1,
            INBOXWINDOWJTABLE = 1,
            INBOXWINDOWJBUTTON = 1,
            INBOXWINDOWJPANEL = 1;

    private static final String[] INBOXWINDOWJBUTTONTXT = {"Volver"}, INBOXLBLTEXT = {MenuData.getEmail()}, INBOXWINDOWTABLEHEADERS = {"Usuario", "Mensaje"};

    private static final String INBOXWINDOWNAME = "Buzon de correo";

    public static Font fontInboxJTable = new Font("Consolas", Font.PLAIN, 11);
    public static Color bgColorInboxJTable = new Color(174, 214, 241);
    public static Color bgColorInboxPanel = new Color(45, 45, 45);
    public static Color bgColorInboxNorthSouthBorderLayout = new Color(12, 15, 65);
    public static Color bgColorInboxButton = new Color(223, 236, 243);

    public static final int windowsInboxWidth = 700, windowsInboxHeight = 500;

    public static final int NUMMAILS = 10;

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
    private static int INBOXWINDOWJLABEL = 1;


}
