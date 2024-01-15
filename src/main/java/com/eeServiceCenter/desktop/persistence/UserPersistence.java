package com.eeServiceCenter.desktop.persistence;

import com.eeServiceCenter.desktop.Entity.User;
import com.eeServiceCenter.desktop.db.DBConnection;
import javafx.scene.control.Alert;

import javax.swing.plaf.nimbus.State;
import java.sql.*;

public class UserPersistence {

    public User login(String username,String password){
        try {
            Connection conn= DBConnection.getInstance().getConnection();
            String sql="SELECT * FROM USER where username='"+username+"' AND password='"+password+"'";
            Statement statement=conn.createStatement();
            ResultSet result=statement.executeQuery(sql);

            if(result.next()){
                return new User(
                        result.getString(1),
                        result.getString(2),
                        result.getString(3),
                        result.getBoolean(4),
                        result.getInt(6));
            }
            else{
                return null;
            }

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean register(User user){
        try {
            Connection conn= DBConnection.getInstance().getConnection();
            System.out.println("got Connection");
            String sql="insert into user (userId,Username,password,status,authorityLVL) values('"+
                    user.getUserId()+"','"+
                    user.getUserName()+"','"+
                    user.getPassword()+"',"+
                    user.isStatus()+","+
                    user.getAccessLevel()+")";
            System.out.println( "sql Statement   :"+sql);
            Statement statement=conn.createStatement();
            int linechanged=statement.executeUpdate(sql);
            if(linechanged!=0){
                new Alert(Alert.AlertType.INFORMATION,"Success").show();
            }
            return true;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


}
