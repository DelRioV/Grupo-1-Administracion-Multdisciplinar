package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionDB {
    private ConnectionData myData = new ConnectionData();

    public Statement getRemoteConnection() {
        try {
            Class.forName(myData.getDRIVER());
            String jdbcUrl = "jdbc:mysql://" + myData.getHOSTNAME() + ":" + myData.getPORT() + "/" + myData.getDBNAME() + "?user=" + myData.getUSERNAME() + "&password=" + myData.getPASSWORD();
            System.out.println(jdbcUrl);
            Connection con = DriverManager.getConnection(jdbcUrl);
            Statement myStatement = con.createStatement();
            return myStatement;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
