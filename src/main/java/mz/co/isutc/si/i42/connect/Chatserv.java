 package mz.co.isutc.si.i42.connect;

import javax.servlet.http.HttpServlet;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@WebServlet("/Chatserv")
public class Chatserv extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private  static ArrayList <String>message=new ArrayList<String>();
   
	public void init() {
		new JMSTopic();
    }

	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter();
		try {
			response.setContentType("text/html");
			
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bancodado?useSSL=false", "root", "");
			connection.createStatement();
			request.getParameter("username");
			String str2 = request.getParameter("submitmsg");
			
			message.add(str2);
			request.setAttribute("pega",str2);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		
	}
	

	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}