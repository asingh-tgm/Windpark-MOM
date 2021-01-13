package windpark;


import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import windpark.model.WindengineData;

import javax.jms.*;

public class MOMSender {

  private static String user = ActiveMQConnection.DEFAULT_USER;
  private static String password = ActiveMQConnection.DEFAULT_PASSWORD;
  private static String url = ActiveMQConnection.DEFAULT_BROKER_URL;
  private static String subject = "windengine_001";
	
  public MOMSender() {
		
	  System.out.println( "Sender started." );

	  // Create the connection.
	  Session session = null;
	  Connection connection = null;
	  MessageProducer producer = null;
	  Destination destination = null;
			
	  try {
	    	
			ConnectionFactory connectionFactory = new ActiveMQConnectionFactory( user, password, url );
			connection = connectionFactory.createConnection();
			connection.start();
		
			// Create the session
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			destination = session.createTopic( subject );
				  
			// Create the producer.
			producer = session.createProducer(destination);
			producer.setDeliveryMode( DeliveryMode.NON_PERSISTENT );
			
			// Create the message

		  WindengineData data = new WindengineData();
//		  data.setWindengineID( inWindengineID );
//		  data.setWindspeed( getRandomDouble( 0, 80 ) );
//		  data.setTemperature( getRandomDouble( -40, 40 ) );
//		  data.setPower( getRandomDouble( 0, 2000 ) );
//		  data.setBlindpower( getRandomDouble( 0, 200 ) );
//		  data.setRotationspeed( getRandomDouble( 0, 200 ) );
//		  data.setBladeposition( getRandomInt( 0, 45 ) );
		  System.out.println(data);

			//TextMessage message = session.createTextMessage( "MaxMustermann [ xxx.xxx.xxx.xxx ]: This message was sent at (ms): " + System.currentTimeMillis() );
		  ObjectMessage message = session.createObjectMessage(data);
		  producer.send(message);
			System.out.println( message.toString() );

			connection.stop();
	      
	  } catch (Exception e) {
	  	
	  	System.out.println("[MessageProducer] Caught: " + e);
	  	e.printStackTrace();
	  	
	  } finally {
	  	
			try { producer.close(); } catch ( Exception e ) {}
			try { session.close(); } catch ( Exception e ) {}
			try { connection.close(); } catch ( Exception e ) {}
			
	  }
	  System.out.println( "Sender finished." );
      
  } // end main
	
}
