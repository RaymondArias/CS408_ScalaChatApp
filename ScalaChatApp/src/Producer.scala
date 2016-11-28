import org.apache.activemq.ActiveMQConnection;
import java.net.Socket
import org.apache.activemq.ActiveMQConnectionFactory
import javax.jms._
import java.util.Scanner
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date

class Producer(username : String) extends Runnable {
	val url: String = "tcp://ec2-35-160-86-191.us-west-2.compute.amazonaws.com:61616"
	val topicName: String = "chatroom"

	def run() {
	    val input: Scanner = new Scanner(System.in)
			val connectionFactory: ConnectionFactory = new ActiveMQConnectionFactory(url)
			val connection: Connection = connectionFactory.createConnection
			connection.start
			val session: Session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE)
			val destination: Topic = session.createTopic(topicName)
			println("Connected")
			val messageProducer: MessageProducer = session.createProducer(destination)
			val textMessage: TextMessage = session.createTextMessage("Hello Subscriber!")
	
			while(true) {
				    val userMessage = updateTime().concat("| " + username + " > " + input.nextLine())
						textMessage.setText(userMessage)
						messageProducer.send(textMessage)
						//println(textMessage.getText)
			}  
			connection.close
  }    
	def updateTime(): String = {
	  	val today = Calendar.getInstance().getTime()
			val timeFormat = new SimpleDateFormat("hh:mm:ss a")
	  	val time = timeFormat.format(today)
	  	return time
	}
}

