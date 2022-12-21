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

/**
 * @author -Ismael Orellana Bello
 *         -Pablo Salvador Del Río Vergara
 *         -Ángel Acedo Moreno
 *         -Javier Tienda
 *         -Jorge Luis López
 * @version 1.0
 * @date 23/12/2022
 * BDLogic It is a class that contains all the logic that has to do with the database.
 */
public class BDLogic {
    /**
     * Method that do a query to the DB and get the username and password (Encrypted),
     * also check if it is correct
     *
     * @param username -String the text of the username TextField
     * @param password -String the text of the password Passfield
     * @return boolean condition
     *         true: if the username and the pass is correct
     *         false if the username and the pass is incorrect
     */
    public boolean getAccess(String username,String password){
        boolean condition = false;
        int counter = 0;
        Statement st = new ConnectionDB().getRemoteConnection();
        String query = "Select Email from Users where Email = '"+username+"' or Username = '"+username+"'";
        String pass [] = getPass();
        boolean cond = false;
        for(int i = 0;i<pass.length;i++){
            if(pass[i].equals(password)){
                cond = true;
                i = pass.length;
            }
        }
        try {
            ResultSet rs = st.executeQuery(query);
            if(rs.next() && cond){
                condition = true;
            }
        } catch (SQLException e) {
           new JOptionPane("Error al introducir el usuario o la contraseña");
        }
        return condition;
    }

    /**
     * Method that do an insert into the DB with the movement in the Server
     *
     * @param idUser -int The User Id
     * @param move -String the type of move
     * @param fileName -String the File name
     */
    public void insertMove(int idUser,String move,String fileName){
        try {
            boolean cond = new ConnectionDB().getRemoteConnection().execute("Insert into Movements (idUser,TypeOperation,OperationDate,FileName) values " +
                    "('"+idUser+"','"+move+"','"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime())+"','"+fileName+"')");
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    /**
     * Do a query and get an array with all the passwords and decrypt them
     *
     * @return String[] with all the passwords of the DB (Decrypted)
     */
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
