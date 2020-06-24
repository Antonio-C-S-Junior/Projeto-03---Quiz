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
    public static final String URL = "jdbc:sqlite:\\C:\\BD Projeto03\\projeto03.db";
    
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
                    + "login VARCHAR(20) UNIQUE NOT NULL, "
                    + "name VARCHAR(200) NOT NULL, "
                    + "password_hash LONG NOT NULL "
                    + ")");
            if(User.getList().isEmpty()){
                step = "default users creations";
                stmt.executeUpdate("INSERT INTO users VALUES("
                        + "'antonio', 'Antonio Carlos', "+"1234".hashCode()+")");
                stmt.executeUpdate("INSERT INTO users VALUES("
                        + "'thifany', 'Thifany Adelli', "+"123".hashCode()+")");
                stmt.executeUpdate("INSERT INTO users VALUES("
                        + "'noemi', 'Noemi Ribeiro', "+"123456".hashCode()+")");
                stmt.executeUpdate("INSERT INTO users VALUES("
                        + "'luciana', 'Luciana Machado', "+"123456".hashCode()+")");
            }
            
            Class.forName("org.sqlite.JDBC");
            step = "'questions' table creation";
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS questions("                   
                    + "question VARCHAR(200) UNIQUE NOT NULL, "
                    + "correctQuestion BOOLEAN NOT NULL, "
                    + "optionsQuestion NOT NULL "
                    + ")");
            if(Question.getList().isEmpty()){
                step = "default users creations";
                stmt.executeUpdate("INSERT INTO questions VALUES("
                        + "'antonio', 'Antonio Carlos', "+"1234".hashCode()+")");
                stmt.executeUpdate("INSERT INTO questions VALUES("
                        + "'thifany', 'Thifany Adelli', "+"123".hashCode()+")");
                stmt.executeUpdate("INSERT INTO questions VALUES("
                        + "'noemi', 'Noemi Ribeiro', "+"123456".hashCode()+")");
                stmt.executeUpdate("INSERT INTO questions VALUES("
                        + "'luciana', 'Luciana Machado', "+"123456".hashCode()+")");
            }
            
            Class.forName("org.sqlite.JDBC");
            step = "'results' table creation";
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS results("                   
                    + "resultUser NUMERIC(2) UNIQUE NOT NULL, "
                    + "resultNewest NUMERIC(2) NOT NULL, "
                    + "resultTopTen NUMERIC(2) NOT NULL, "
                    + "login VARCHAR(20) NOT NULL,"
                    + "CONSTRAINT result_users_login_fk FOREIGN KEY (login) REFERENCES users(login)"
                    + ")");
            
            
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
    
