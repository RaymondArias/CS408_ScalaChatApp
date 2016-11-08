import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory
import javax.jms._
import java.util.Scanner
object Producer {
  val url: String = "tcp://localhost:61616"
  val topicName: String = "chatroom"
  
  def main(args:Array[String])
  {
    val input: Scanner = new Scanner(System.in)
    val connectionFactory: ConnectionFactory = new ActiveMQConnectionFactory(url)
    val connection: Connection = connectionFactory.createConnection
    connection.start
    val session: Session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE)
    val destination: Topic = session.createTopic(topicName)
    val messageProducer: MessageProducer = session.createProducer(destination)
    val textMessage: TextMessage = session.createTextMessage("Hello Subscriber!")
    while(true) {
      val userMessage = input.nextLine()
      textMessage.setText(userMessage)
      messageProducer.send(textMessage)
      println("Message sent to subscriber: '" + textMessage.getText + "'")
    }
    connection.close
    
  }
}