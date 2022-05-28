<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
 pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login Chat Page</title>
<link rel="stylesheet" href="login.css" />
</head>
<body>
 <div align="Left">
  <h1>ChatRoom login</h1>
  <form action="<%=request.getContextPath()%>/Loginserv" method="post">

    <div class="login">
        <input type="text" placeholder="Username" name="username">  
      <input type="password" placeholder="password" name="password">  
      <a href="#" class="forgot">forgot password?</a>
      <input type="submit" value="Entrar no chatroom">
    </div>
    <div class="shadow"></div>
   
  </form>
 </div>
</body>
</html>