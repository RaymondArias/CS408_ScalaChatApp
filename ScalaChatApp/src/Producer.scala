import org.apache.activemq.ActiveMQConnection;
import java.net.Socket
import org.apache.activemq.ActiveMQConnectionFactory
import javax.jms._
import java.util.Scanner
object Producer {
  val url: String = "tcp://ec2-35-160-86-191.us-west-2.compute.amazonaws.com:61616"
  val topicName: String = "chatroom"
  
  def main(args:Array[String])
  {
    //val sock = new Socket("ec2-35-162-109-101.us-west-2.compute.amazonaws.com", 61616);
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
      val userMessage = input.nextLine()
      textMessage.setText(userMessage)
      messageProducer.send(textMessage)
      println("Message sent to subscriber: '" + textMessage.getText + "'")
    }
    connection.close
    
  }
}