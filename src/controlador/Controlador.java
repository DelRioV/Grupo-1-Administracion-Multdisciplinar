package controlador;

import modelo.ConnectionDB;
import modelo.ConnectionData;
import org.apache.commons.net.ftp.FTPClient;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Controlador {

    private ConnectionDB myCon = new ConnectionDB();
    public Controlador() {
        try {
            ResultSet rs = myCon.getRemoteConnection().executeQuery("SELECT * FROM Users");
            rs.next();
            System.out.println(rs.getInt(1) + rs.getString(2)+rs.getString(3) + rs.getString(4));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
