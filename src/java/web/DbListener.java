/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import java.util.Locale.Category;
import db.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Web application lifecycle listener.
 *
 * @author rlarg
 */
public class DbListener implements ServletContextListener {
    public static final String URL = "jdbc:sqlite:C:\\Users\\rlarg\\dbnot.db";
    
    public static String exceptionMessage = null;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        String step = "Starting database";
        try{
            Class.forName("org.sqlite.JDBC");
            Connection con = DriverManager.getConnection(URL);
            Statement stmt = con.createStatement();
            step = "'users' table creation";
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS users("
                    + "name VARCHAR(200) NOT NULL,"
                    + "login VARCHAR(20) PRIMARY KEY,"
                    + "password_hash LONG NOT NULL,"
                    + ")");
            stmt.close();
            con.close();
        }catch(Exception ex){
            exceptionMessage = step + ": " + ex.getMessage();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        
    }
}
