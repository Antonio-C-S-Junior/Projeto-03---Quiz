/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import web.DbListener;
import java.sql.PreparedStatement;

/**
 *
 * @author 56235
 */
public class ResultUser {
    private String ResultUser;
    
    public static ArrayList<ResultUser> getList (String login) throws Exception{
        
        ArrayList<ResultUser> list = new ArrayList<>();
        Class.forName("org.sqlite.JDBC");
        Connection con = DriverManager.getConnection(DbListener.URL);
        PreparedStatement stmt = con.prepareStatement("SELECT * FROM results WHERE login=?");
        stmt.setString(1, login);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            list.add(new ResultUser(rs.getString("result")));
            }
        
        rs.close();
        stmt.close();
        con.close();
        return list;
    }

    public ResultUser(String ResultUser) {
        this.ResultUser = ResultUser;
    }

    public String getResultUser() {
        return ResultUser;
    }

    public void setResultUser(String ResultUser) {
        this.ResultUser = ResultUser;
    }
}
