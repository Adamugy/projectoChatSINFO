<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<html lang="en">
<%String username=(String)session.getAttribute("name"); %>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script type="text/javascript" src="addText.js"></script>
    <link rel="stylesheet" href="style.css" />
    <title>ChatRoom Application</title>
</head>
<body>
    <div id="wrapper">
        <div id="menu">
            <p id="result"></p>
            <p class="welcome">Welcome, <b><%=username%></b></p>
            <p class="logout"><a id="exit" href="exit.jsp">Exit Chat</a></p>
        </div>
        
        <div id="chatbox"></div>
        
 		<form action="" method="post">
            <input name="usermsg"  type="text" id="usermsg" value="<%=username%>" />
            <input name="submitmsg" type="submit" id="submitmsg" value="Send" onclick="text()"/>
        </form>
    </div>  
</body>
</html>