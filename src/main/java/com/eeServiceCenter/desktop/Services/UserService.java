package com.eeServiceCenter.desktop.Services;

import com.eeServiceCenter.desktop.Entity.User;
import com.eeServiceCenter.desktop.model.UserModel;
import com.eeServiceCenter.desktop.persistence.UserPersistence;
import javafx.scene.control.Alert;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

public class UserService {

    private UserPersistence userPersistence=new UserPersistence();
    private List<UserModel> userList;
    private static User loggedInUser;
    public static User getLoggedInUser(){
            return loggedInUser;
    }

    public boolean login(String username,String password) throws NoSuchAlgorithmException {
        String encryptedPassword=toHexString(getSHA(password));

        User temp=userPersistence.login(username,encryptedPassword);
        loggedInUser=temp;

        if(temp==null){
            new Alert(Alert.AlertType.ERROR,"Username or Password is Incorrect").show();

        }
        else{
            System.out.println("userId"+loggedInUser.getId()+
                    "username"+loggedInUser.getUserName());

            if(loggedInUser.getAuthorityLvl()==1){

            }
        }
        return temp!=null;
    }

    //these 2 methods are for encrypting the password
    public static byte[] getSHA(String input) throws NoSuchAlgorithmException
    {
        /* MessageDigest instance for hashing using SHA256 */
        MessageDigest md = MessageDigest.getInstance("SHA-256");

        /* digest() method called to calculate message digest of an input and return array of byte */
        return md.digest(input.getBytes(StandardCharsets.UTF_8));
    }

    public static String toHexString(byte[] hash)
    {
        /* Convert byte array of hash into digest */
        BigInteger number = new BigInteger(1, hash);

        /* Convert the digest into hex value */
        StringBuilder hexString = new StringBuilder(number.toString(16));

        /* Pad with leading zeros */
        while (hexString.length() < 32)
        {
            hexString.insert(0, '0');
        }

        return hexString.toString();
    }
    public boolean registerUser(UserModel user) throws NoSuchAlgorithmException {
        return userPersistence.save(new User(user.getId(),user.getUserName(),toHexString(getSHA(user.getPassword())),true,user.getDescription(),user.getAuthorityLvl()));
    }

    //METHODS USED BY CONTROLLER
    public UserModel getModel(String userId){
        if(userList!=null){
            for(UserModel model:userList){
                if(model.getId().equalsIgnoreCase(userId)){
                    return model;
                }
            }
        }
        return null;

    }

    public boolean assignToUserList(){
        try {
            List<User> userEntityList = userPersistence.getAllUsers();

            List<UserModel> userModelList = new LinkedList<>();

            for (User userEntity : userEntityList) {
                System.out.println(userEntity.toString()+"this is the user entity");
                userModelList.add(new UserModel(userEntity.getId(), userEntity.getUserName(), userEntity.getPassword(), userEntity.getDescription(), new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(userEntity.getCreatedAt()), (userEntity.isStatus() ? "Active" : "Innactive"), userEntity.getAuthorityLvl()));
            }
            userList=userModelList;
            return true;
        }
        catch (Exception e){
            e.printStackTrace();
            System.out.println("theres an error");
            return false;
        }
    }

    public List<UserModel> getUserList(){
        return userList;
    }

}
