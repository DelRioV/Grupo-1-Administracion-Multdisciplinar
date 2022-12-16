package modelo;

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

    private final static int INBOXWINDOWNUMBUTTONS = 1, INBOXWINDOWNUMPANELS = 1, INBOXWINDOWNUMJTABLE = 1;
    private final static String INBOXWINDOWNAME = "Buzón de correo";
    private final static String[] INBOXWINDOWTABLEHEADERS = new String[]{"Usario", "Mensaje"}, INBOXWINDOWBTNSTXT = {"Volver"};

    private static String[] INBOXLBLTEXT = new String[]{"prueba@gmail.com"};

    public int getLengthtextfields() {
        return LENGTHTEXTFIELDS;
    }

    public String getLoginwindowname() {
        return LOGINWINDOWNAME;
    }

    public int getLoginwindownumbotones() {
        return LOGINWINDOWNUMBOTONES;
    }

    public int getLoginwindownumpanels() {
        return LOGINWINDOWNUMPANELS;
    }

    public int getLoginwindownumtextfields() {
        return LOGINWINDOWNUMTEXTFIELDS;
    }

    public int getLoginwindownumlabels() {
        return LOGINWINDOWNUMLABELS;
    }

    public String[] getLoginwindowlabelsnames() {
        return LOGINWINDOWLABELSNAMES;
    }

    public String getLoginwindowbuttonsame() {
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


    public String getINBOXWINDOWNAME() { return INBOXWINDOWNAME; }

    public int getINBOXWINDOWNUMBUTTONS() { return INBOXWINDOWNUMBUTTONS; }

    public int getINBOXWINDOWNUMPANELS() { return INBOXWINDOWNUMPANELS; }

    public int getINBOXWINDOWNUMJTABLE() { return INBOXWINDOWNUMJTABLE; }

    public String[] getINBOXWINDOWTABLEHEADERS() { return INBOXWINDOWTABLEHEADERS; }

    public String[] getINBOXWINDOWBTNSTXT(){ return INBOXWINDOWBTNSTXT; };

    public static String[] getINBOXLBLTEXT() {
        return INBOXLBLTEXT;
    }

    public static void setINBOXLBLTEXT(String[] INBOXLBLTEXT) {
        Modelo.INBOXLBLTEXT = INBOXLBLTEXT;
    }
}
