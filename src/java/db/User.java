/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.util.ArrayList;

/**
 *
 * @author 56235
 */
public class User {
    private String login;
    private String name;
    
    public ArrayList<User> getList() throws Exception{
        ArrayList<User> list = new ArrayList<>();
        
        return list;
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