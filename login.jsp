
<%@ page pageEncoding="UTF-8" %>
<%@page import="db.User"%>
<%@page import="web.DbListener"%>
<%@include file="../../index.jsp"%>
<%
     String loginError = null;
    if(request.getParameter("session.login")!=null){
        String login = request.getParameter("user.login");
        String password = request.getParameter("user.password");
        try{
            User user = User.getUser(login, password);
            if(user==null){
                loginError = "Login or password incorrect";
            }else{
                session.setAttribute("user.login", login);
                session.setAttribute("user.name", user.getName());
                response.sendRedirect(request.getRequestURI());
            }
        }catch(Exception ex){
            loginError = "Error: " + ex.getMessage();
        }
    }else if(request.getParameter("session.logoff")!=null){
        session.removeAttribute("user.login");
        session.removeAttribute("user.name");
        session.removeAttribute("user.role");
        response.sendRedirect(request.getRequestURI());
    }
%>
<html>
    <head>
        <meta charset="utf-8">
        <title>Login</title>
        <meta name="viewport" content="width=device-width, inicial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="style_1.css">
    </head>
    <body>
        <div class="container">
            <a class="links" id="paracadastro"></a>
            <a class="links" id="login"></a>
            <div class="content">
                <!-- FORMULÁRIO DE LOGIN -->
                <div id="login">
                    <%if(session.getAttribute("user.login")==null){%>
                    <form method="post" action="">
                        <h1>Login</h1>
                        <p>
                            <label for="user">Usuário</label>
                            <input id="nome-user" name="user.login" required="required" type="text">
                        </p>
                        <p>
                            <label for="password">Senha</label>
                            <input id="password" name="user.password" required="required" type="password">
                        </p>
                        <p>
                            <input type="submit" value="Entrar" name="session.login">
                        </p>
                        <p class="link">
                            Ainda não tem conta?
                            <a href="#paracadastro">Cadastre-se</a>
                        </p>
                    </form>
                </div>
                    <%if(loginError != null){%>
                    <div style="color:red"><%= loginError %></div>
                    <%}%>
                    <%}else{%>
                    
                <!--FORMULÁRIO PARA CADASTRO -->
                <div id="cadastro">
                    <form method="post" action="">
                        <h1>Cadastro</h1>
                        <p>
                            <label for="nome-cad">Nome Completo</label>
                            <input id="nome-cad" name="nome-cad" required="required" type="text" placeholder="Digite seu nome!">
                        </p>
                        <p>
                            <label for="senha-cad">Senha</label>
                            <input id="senha-cad" name="senha-cad" required="required" type="password" placeholder="Digite sua senha">
                        </p>
                        <p>
                            <input type="submit" value="Cadastrar"/>
                        </p>
                        
                        <p class="link">
                            Já tem conta?
                            <a href="#paralogin">Ir para login</a>
                        </p>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>