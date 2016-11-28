import javax.jms._
import org.apache.activemq.ActiveMQConnection
import org.apache.activemq.ActiveMQConnectionFactory


class Consumer(username : String) extends Runnable {
	val url = "tcp://ec2-35-160-86-191.us-west-2.compute.amazonaws.com:61616"
			val topic = "chatroom"

	def run() {
		val connectionFactory  = new ActiveMQConnectionFactory(url)
		val connection = connectionFactory.createConnection
		connection.start()
    println("Started")
    val session: Session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE)
		val destination: Topic = session.createTopic(this.topic)
		val consumer: MessageConsumer = session.createConsumer(destination)
    val listener = new MessageListener {
		def onMessage(message: Message) {
		    message match {
		      case text: TextMessage => {
		        if (!text.getText().contains(username)) {
					    println("Received message: " + text.getText())
		        }
			    }
			    case _ => {
					    throw new Exception("Unhandled Message Type: " + message.getClass.getSimpleName)
			    }
		    }
		  }
		}
		consumer.setMessageListener(listener)
	}
}