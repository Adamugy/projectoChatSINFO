<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<html lang="en">
<%String username=(String)session.getAttribute("name"); %>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="style.css" />
    <title>ChatRoom Application</title>
</head>
<script>
        function texte()
    {
        var username = document.getElementById('usermsg').value;
        var msg = document.getElementById('submitmsg').value;
        olist = document.getElementById('list');
        op = document.createElement('p');
        op.innerHTML = username + "- <g>" + msg + "</g>";
        ocontent = document.getElementById('chatbox');
        ocontent.appendChild(op);             //Add new line on click
        olist.scrollTop = olist.scrollHeight; //Adjust Height
        
        var xmlhttp;
        if (window.XMLHttpRequest)
        {// code for IE7+, Firefox, Chrome, Opera, Safari
            xmlhttp = new XMLHttpRequest();
        }
        else
        {// code for IE6, IE5
            xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
        }

        xmlhttp.open("POST", "chatserv?username=" + username + "&msg=" + msg, true);

        xmlhttp.onreadystatechange = function()
        {
            if (xmlhttp.readyState === 4 && xmlhttp.status === 200)
            {
                document.getElementById("result").innerHTML = 'sent';
                document.getElementById("result").innerHTML = '';
            }
        };
        xmlhttp.send(null);
}
</script>
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