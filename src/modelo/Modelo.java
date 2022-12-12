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

}
