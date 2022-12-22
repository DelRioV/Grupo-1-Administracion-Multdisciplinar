package modelo;
/**
 * @author -Ismael Orellana Bello
 *         -Pablo Salvador Del Río Vergara
 *         -Ángel Acedo Moreno
 *         -Javier Tienda
 *         -Jorge Luis López
 * @version 1.0
 * @date 23/12/2022
 * That class contains variables of the Connection
 */
public class ConnectionData {
    //DataBase Name
    private String DBNAME = "u542011410_proyectoMult";
    //Username of the account
    private String USERNAME = "u542011410_admin";
    //Password of the account
    private String PASSWORD = "O3I=0pw|";
    //The host Name of the data base
    private String HOSTNAME = "sql507.main-hosting.eu";
    //The port of the database
    private String PORT = "3306";
    //The driver of the dataBase
    private String DRIVER = "com.mysql.jdbc.Driver";

    //Getter and Setters
    public String getDBNAME() {
        return DBNAME;
    }

    public String getUSERNAME() {
        return USERNAME;
    }

    public String getPASSWORD() {
        return PASSWORD;
    }

    public String getHOSTNAME() {
        return HOSTNAME;
    }

    public String getPORT() {
        return PORT;
    }

    public String getDRIVER() {
        return DRIVER;
    }
}
