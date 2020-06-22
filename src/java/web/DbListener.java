/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import db.Question;
import db.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Web application lifecycle listener.
 *
 * @author 56235
 */
public class DbListener implements ServletContextListener {
    public static final String URL = "jdbc:sqlite:C:\\BD Projeto03\\projeto03.db";
    
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
                    + "login VARCHAR(20) UNIQUE NOT NULL,"
                    + "name VARCHAR(200) NOT NULL,"
                    + "password_hash LONG NOT NULL,"
                    + ")");
            if(User.getList().isEmpty()){
                step = "default users creations";
                stmt.executeUpdate("insert into users values("
                        + "'antonio' 'Antonio Carlos' "+"1234".hashCode()+")");
                stmt.executeUpdate("INSERT INTO users VALUES("
                        + "'teste', 'Teste Teste', "+"123".hashCode()+")");
                stmt.executeUpdate("insert into users values("
                        + "'noemi', 'Noemi', "+"123456".hashCode()+")");
            }
            
            
            stmt.close();
            con.close();
        }catch(Exception ex){
            exceptionMessage = step + ": " + ex.getMessage();
        }
    }
    
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
    
