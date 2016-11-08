import javax.jms._
import org.apache.activemq.ActiveMQConnection
import org.apache.activemq.ActiveMQConnectionFactory
 

object Consumer {
  val url = "tcp://localhost:61616"
  val topic = "chatroom"
  
  def main(args: Array[String])
  {
    val connectionFactory  = new ActiveMQConnectionFactory(url)
    val connection = connectionFactory.createConnection
    //connection.setClientID("ConsumerSynchronous")
    connection.start()
 
    println("Started")
 
    val session: Session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE)
    //val queue  = session.createQueue("SendSynchronousMsgQueue")
    val destination: Topic = session.createTopic(this.topic)
    val consumer: MessageConsumer = session.createConsumer(destination)
 
    val listener = new MessageListener {
      def onMessage(message: Message) {
        message match {
          case text: TextMessage => {
          //val replyProducer = session.cr
          //replyProducer.setDeliveryMode(DeliveryMode.NON_PERSISTENT)
 
          println("Received message: " + text.getText)
 
          //val replyMessage = session.createTextMessage("Yes I received your message!")
          //replyMessage.setJMSCorrelationID(text.getJMSCorrelationID())
 
          //println("Reply sent!")
             
          //replyProducer.send(replyMessage)
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