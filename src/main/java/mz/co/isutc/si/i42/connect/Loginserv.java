package mz.co.isutc.si.i42.connect;

import jakarta.servlet.http.HttpServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


/**
 * Servlet implementation class Loginserv
 */
@WebServlet("/Loginserv")
public class Loginserv extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public void init() {
	
	}
       
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter printWriter = response.getWriter();
		
		try {
			response.setContentType("text/html");
			
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bancodado?useSSL=false", "root", "");
			Statement statement = connection.createStatement();
			String str1 = request.getParameter("username");
			String str2 = request.getParameter("password");
			String str3  = "select * from login where username='" + str1 + "' AND password='" + str2 + "'";
			
			ResultSet resultset = statement.executeQuery(str3);
			
			if (resultset.next()) {
				String str4 = resultset.getString("username");
				HttpSession httpSession = request.getSession();
				httpSession.setAttribute("name", str4);
				printWriter.println("Welcome, " + str4.toUpperCase());
		        printWriter.println("<a href='chat.jsp'>Vamos no Chat Room</a>");
		        printWriter.println("<a href='exit.jsp' class='logout-chat'>Logout</a>");
			} else {
				printWriter.println("Incorrect Username or Password.");
			}
			
			connection.close();

		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
		
	}
	

}
