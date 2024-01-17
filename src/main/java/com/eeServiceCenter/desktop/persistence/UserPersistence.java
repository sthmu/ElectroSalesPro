package com.eeServiceCenter.desktop.persistence;

import com.eeServiceCenter.desktop.Entity.User;
import com.eeServiceCenter.desktop.db.DBConnection;
import com.eeServiceCenter.desktop.util.HibernateUtil;
import javafx.scene.control.Alert;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class UserPersistence {

    public User login(String un,String pwd){
            Session session=HibernateUtil.getSession();
            Transaction transaction=session.beginTransaction();
            Query query=session.createQuery("FROM User WHERE userName='"+un+"'  and  Password='"+pwd+"'");
            List<User> user=query.list();
            session.close();
            return user.get(0);
//            Connection conn= DBConnection.getInstance().getConnection();
//            String sql="SELECT * FROM USER where username='"+username+"' AND password='"+password+"'";
//            Statement statement=conn.createStatement();
//            ResultSet result=statement.executeQuery(sql);
//
//            if(result.next()){
//                return new User(
//                        result.getString(1),
//                        result.getString(2),
//                        result.getString(3),
//                        result.getBoolean(4),
//                        result.getString(7),
//                        result.getString(5),
//                        result.getInt(6)
//                );
//            }
//            else{
//                return null;
//            }
//
//        } catch (ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
    }

    public boolean save(User user){
        Session session= HibernateUtil.getSession();
        Transaction transaction=session.beginTransaction();

        session.saveOrUpdate(user);
        transaction.commit();
        session.close();
        return true;



//        try {
//            Connection conn= DBConnection.getInstance().getConnection();
//            System.out.println("got Connection");
//            String sql="insert into user (userId,Username,password,status,authorityLVL) values('"+
//                    user.getId()+"','"+
//                    user.getUserName()+"','"+
//                    user.getPassword()+"',"+
//                    user.isStatus()+","+
//                    user.getAuthorityLvl()+")";
//            System.out.println( "sql Statement   :"+sql);
//            Statement statement=conn.createStatement();
//            int linechanged=statement.executeUpdate(sql);
//            if(linechanged!=0){
//                new Alert(Alert.AlertType.INFORMATION,"Success").show();
//            }
//            return true;
//        } catch (ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }

    }

    public List<User> getAllUsers() {
        Session session=HibernateUtil.getSession();
        Transaction transaction=session.beginTransaction();
        Query query=session.createQuery("FROM User");
        List<User> userList=query.list();
        transaction.commit();
        session.close();
        return  userList;
    }
}
