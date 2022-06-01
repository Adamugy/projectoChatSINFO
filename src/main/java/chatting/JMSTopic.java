package chatting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.MessageListener;
import jakarta.jms.ObjectMessage;
import jakarta.jms.Session;
import jakarta.jms.Topic;
import jakarta.jms.TopicConnection;
import jakarta.jms.TopicConnectionFactory;
import jakarta.jms.TopicPublisher;
import jakarta.jms.TopicSession;
import jakarta.jms.TopicSubscriber;

public class JMSTopic implements MessageListener {
	
	public static final String TOPIC01 = "jms/Topico1";
	public static final String TOPIC02 = "jms/Topico2";
	@Override
	public void onMessage(Message message) {
		
		try {
			ObjectMessage objectMessage = (ObjectMessage) message;
			Mail mail = (Mail)objectMessage.getObject();
			System.out.print("Sender: " + mail.getName());
			System.out.println("Message: " + mail.getMessage());
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void main(String[] args) throws NamingException, JMSException, IOException {
		JMSTopic jmstopic1 = new JMSTopic();
		InitialContext initialContext = (InitialContext) JMSTopic.getContext();
		Topic topic01 =(Topic)initialContext.lookup(JMSTopic.TOPIC01);               
		Topic topic02 = (Topic)initialContext.lookup(JMSTopic.TOPIC02);
		 TopicConnectionFactory topicConnectionFactory = (TopicConnectionFactory) initialContext.lookup("topic/ConnectionFactory");                     
		 TopicConnection topicConnection = topicConnectionFactory.createTopicConnection();
		 topicConnection.start();
		 jmstopic1.subscrib(topicConnection, topic01);
		 jmstopic1.publish(topicConnection, topic02);
		 
	}
	
	public void subscrib(TopicConnection topicConnetion, Topic topic) throws JMSException {
		TopicSession subSession =  topicConnetion.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
		TopicSubscriber topicSubscriber = subSession.createSubscriber(topic);
		topicSubscriber.setMessageListener(this);
	}
	public void publish(TopicConnection topicConnetion, Topic topic) throws JMSException, IOException {
		TopicSession pubSession =  topicConnetion.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
		TopicPublisher topicPublisher = pubSession.createPublisher(topic);
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String username = reader.readLine();
		String message = null;
		while (true) {
			message = reader.readLine();
			if (message.equalsIgnoreCase("exit")) {
				topicConnetion.close();
				System.exit(0);
			}else {
				ObjectMessage o= pubSession.createObjectMessage();
				o.setObject(new Mail(username, message));
				topicPublisher.publish(o);
				
				
			}
		}
	}

	public static Context getContext() throws NamingException{
		
		Properties properties= new Properties();
		properties.setProperty("java.naming.factory.initial","com.sun.enterprise.naming.SerialInitContextFactory");
		properties.setProperty("java.naming.factory.url.pkgs","com.sun.enterprise.naming");
		properties.setProperty("java.naming.provider.url","iiop://localhost:3700");
		return new InitialContext(properties);
	}

}
