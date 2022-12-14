package modelo;

public class ConnectionData {
    private String DBNAME = "FTP_DB_Multidisciplinar";
    private String USERNAME = "admin";
    private String PASSWORD = "proyectomultidisciplinar";
    private String HOSTNAME = "dbusuarios.cl8kpkuf6i1i.us-east-1.rds.amazonaws.com";
    private String PORT = "3306";
    private String DRIVER = "com.mysql.jdbc.Driver";

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
