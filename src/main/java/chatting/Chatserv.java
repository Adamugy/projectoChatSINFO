 package chatting;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;

import java.util.ArrayList;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
       




@WebServlet("/Chatserv")
public class Chatserv extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private  static ArrayList <String>message=new ArrayList<String>();
    
    
    
    public void init() {new JMSTopic();}
   	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter();
		try {
			response.setContentType("text/html");
			
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bancodado?useSSL=false", "root", "");
			connection.createStatement();
			request.getParameter("username");
			String msg = request.getParameter("submitmsg");
			
			message.add(msg);
			request.getAttribute(msg).toString();
			
		
            
			/*message.add(msg);
			request.setAttribute("pega",getInitParameter(msg));*/
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		
	}
	

	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		//produce();
		//consume();
		
	}

	
	 /*private void produce() {

	        try {

	            Connection conn = factory.createConnection(username, password);
	            Session session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
	            MessageProducer prod = session.createProducer(session.createTopic(destination));
	            prod.setDeliveryMode(DeliveryMode.PERSISTENT);
	            conn.start();
	            Message msg = session.createTextMessage("hello");
	            prod.send(msg);
	            conn.close();
	        } catch (JMSException e) {
	            log.info(e.getMessage());
	        }

	    }

	    private void consume() {

	        try {
	            Connection conn = factory.createConnection(username, password);
	            Session session = conn.createSession(false,
	                    Session.AUTO_ACKNOWLEDGE);
	            MessageConsumer cons = session.createConsumer(session.createTopic(destination));
	            conn.start();
	            Message msg = cons.receive(1000);
	            if (msg == null) {
	                log.info("received no message");
	            } else {
	                TextMessage t = (TextMessage) msg;
	                log.info(t.getText());
	            }
	            conn.close();
	        } catch (JMSException e) {
	            e.printStackTrace();
	        }

	    }*/
}
