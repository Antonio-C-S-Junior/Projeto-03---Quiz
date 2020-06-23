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
public class Question {
    private String question;
    private String answer;
    
    public static ArrayList<Question> getList() throws Exception{
        ArrayList<Question> list = new ArrayList<>();
        Class.forName("org.sqlite.JDBC");
        Connection con = DriverManager.getConnection(web.DbListener.URL);
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM questions");
        while (rs.next()) {
            list.add(new Question(rs.getString("question"), rs.getString("answer")));
            }
        
        rs.close();
        stmt.close();
        con.close();
        return list;
    }
    
    public static Question getQuestion(String rowid)throws Exception{
        Question question = null;
        Class.forName("org.sqlite.JDBC");
        Connection con = DriverManager.getConnection(web.DbListener.URL);
        PreparedStatement stmt = con.prepareStatement
        ("SELECT * FROM questions WHERE rowid=?");
        stmt.setString(1, rowid);
        ResultSet rs = stmt.executeQuery();
        if(rs.next()){
            question = new Question(
                    rs.getString("question"), 
                    rs.getString("optionsQuestion")
            );
        }
        rs.close();
        stmt.close();
        con.close();
        return question;
    }

    public Question(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
}
