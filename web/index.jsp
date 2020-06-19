

<%@page import="db.User"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%  String ErrorMessege = null;
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
        <title id="t1"> Projeto - Quiz </title>
    </head>
    <body>
        <%@include file="WEB-INF/jspf/menu.jspf" %>
        
    </body>
</html>
