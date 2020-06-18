<%-- 
    Document   : index
    Created on : 16/06/2020, 10:47:54
    Author     : 56235
--%>

<%@page import="db.User"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<% String ErrorMessege = null;
    ArrayList<User> list = new ArrayList<>();
    try{
    list = User.getList(); 
}catch(Exception e){
    ErrorMessege = e.getMessage();
}
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Projeto - QUIZ</title>
    </head>
    <body>
        <%@include file="WEB-INF/jspf/menu.jspf" %>
        <h1>Projeto - QUIZ</h1>
        <input type="submit" method="post" > Fazer QUIZ </input>
        
    </body>
</html>
