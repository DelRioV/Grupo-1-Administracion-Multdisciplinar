package controlador;

import modelo.ConnectionDB;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GetUsersCredentials {

    private final static String QUERY = "Select Email,Password from Users where Email = ";

    public boolean getResult(String user,String password) throws NoSuchAlgorithmException {
        boolean condition = false;
        Statement st = new ConnectionDB().getRemoteConnection();
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        MessageDigest md = MessageDigest.getInstance("SHA-512");
        md.update(salt);
        byte[] hashedPassword = md.digest(password.getBytes(StandardCharsets.UTF_8));
        ResultSet rs = null;
        try {
            rs = st.executeQuery(QUERY+user+" and Password "+hashedPassword);
            if(rs.next()){
                System.out.println(rs.getString(1));
                condition = true;
            }
        } catch (SQLException e) {
            //throw new RuntimeException(e);
        }
        return condition;
    }

}
