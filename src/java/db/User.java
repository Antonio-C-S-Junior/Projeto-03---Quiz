/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import web.DbListener;

/**
 *
 * @author 56235
 */
public class User {
    private String login;
    private String name;
    
    public static ArrayList<User> getList() throws Exception{
        ArrayList<User> list = new ArrayList<>();
        Class.forName("org.sqlite.JDBC");
        Connection con = DriverManager.getConnection(DbListener.URL);
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("select * from users");
        while (rs.next()) {
            list.add(new User(rs.getString("login"), rs.getString("name")));
            }
        
        rs.close();
        stmt.close();
        con.close();
        return list;
    }
    
    public static User getUser(String login, String password)throws Exception{
        User user = null;
        Class.forName("org.sqlite.JDBC");
        Connection con = DriverManager.getConnection(web.DbListener.URL);
        PreparedStatement stmt = con.prepareStatement
        ("SELECT * from users WHERE login=? AND password_hash=?");
        stmt.setString(1, login);
        stmt.setLong(2, password.hashCode());
        ResultSet rs = stmt.executeQuery();
        if(rs.next()){
            user = new User(
                    rs.getString("login"), 
                    rs.getString("name")
            );
        }
        rs.close();
        stmt.close();
        con.close();
        return user;
    }

    public static void changePassword(String login, String newPassword) throws Exception{
        Class.forName("org.sqlite.JDBC");
        Connection con = DriverManager.getConnection(web.DbListener.URL);
        String SQL = "UPDATE users SET password_hash=? WHERE login=?";
        PreparedStatement stmt = con.prepareStatement(SQL);
        stmt.setLong(1, newPassword.hashCode());
        stmt.setString(2, login);
        stmt.execute();
        stmt.close();
        con.close();
    }
    
    public User(String login, String name) {
        this.login = login;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
