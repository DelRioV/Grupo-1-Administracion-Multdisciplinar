package controlador;

import modelo.ConnectionDB;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class BDLogic {

    public boolean getAccess(String username,String password){
        boolean condition = false;
        int counter = 0;
        Statement st = new ConnectionDB().getRemoteConnection();
        String query = "Select Email from Users where Email = '"+username+"' or Username = '"+username+"'";
        String pass [] = getPass();
        boolean cond = false;
        for (int i = 0; i < pass.length; i++) {
            try {
                if (pass[i].equals(password)) {
                    cond = true;
                    i = pass.length;
                }
            }catch (NullPointerException npe){
                JOptionPane.showMessageDialog(null, "Contraseña o usuarios erroneos");
            }

        }
        try {
            ResultSet rs = st.executeQuery(query);
            if (rs.next() && cond) {
                condition = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return condition;
    }

    public void insertMove(int idUser,String move,String fileName){
        try {
            boolean cond = new ConnectionDB().getRemoteConnection().execute("Insert into Movements (idUser,TypeOperation,OperationDate,FileName) values " +
                    "('"+idUser+"','"+move+"','"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime())+"','"+fileName+"')");
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public String [] getPass(){
        String [] response = null;
        try {
            ResultSet rs = new ConnectionDB().getRemoteConnection().executeQuery("Select Password from Users");
            rs.last();
            response = new String[rs.getRow()];
            rs.first();
            int counter = 0;
            while(counter<response.length){
                rs.next();
                response[counter++] = new Encrypt().decrypt(rs.getString(1));
                System.out.println(response[counter-1]);
            }
        } catch (SQLException e) {
        }
        return response;
    }

}
