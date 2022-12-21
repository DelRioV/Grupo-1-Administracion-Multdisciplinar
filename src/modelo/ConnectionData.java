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
    private String DBNAME = "FTP_DB_Multidisciplinar";
    //Username of the account
    private String USERNAME = "admin";
    //Password of the account
    private String PASSWORD = "proyectomultidisciplinar";
    //The host Name of the data base
    private String HOSTNAME = "dbusuarios.cl8kpkuf6i1i.us-east-1.rds.amazonaws.com";
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
