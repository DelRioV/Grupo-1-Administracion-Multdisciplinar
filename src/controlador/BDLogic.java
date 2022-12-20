package controlador;

import modelo.ConnectionDB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BDLogic {

    public boolean getAccess(String username,String password){
        boolean condition = false;
        int counter = 0;
        Statement st = new ConnectionDB().getRemoteConnection();
        String query = "";
        System.out.println(username);
        System.out.println(password);
        if(username.contains("@")){
            query = "Select Email from Users where Email = '"+username+"'";
        }
        else{
            query = "Select Username from Users where Username = '"+username+"'";
        }
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
            e.printStackTrace();
        }
        return condition;
    }

    public void insertMove(String move){

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
