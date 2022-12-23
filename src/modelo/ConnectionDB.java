package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
/**
 * @author -Ismael Orellana Bello
 *         -Pablo Salvador Del Río Vergara
 *         -Ángel Acedo Moreno
 *         -Javier Tienda
 *         -Jorge Luis López
 *         -José Ramón Gallego
 * @version 1.0
 * @date 23/12/2022
 * That class contains methods to connect to the database
 */
public class ConnectionDB {
    //Object with properties
    private ConnectionData myData = new ConnectionData();

    /**
     * Method that creates a connection
     * @return -Statement a statement
     */
    public Statement getRemoteConnection() {
        try {
            Class.forName(myData.getDRIVER());
            String jdbcUrl = "jdbc:mysql://" + myData.getHOSTNAME() + ":" + myData.getPORT() + "/" + myData.getDBNAME() + "?user=" + myData.getUSERNAME() + "&password=" + myData.getPASSWORD()+"&useSSL=false";
            Connection con = DriverManager.getConnection(jdbcUrl);
            Statement myStatement = con.createStatement();
            return myStatement;
        } catch (ClassNotFoundException e) {
            return null;
        } catch (SQLException e) {
           return null;
        }
    }
}
