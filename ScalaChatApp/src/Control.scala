import java.util.Scanner

class Control {
  def Control(){}
	def main(args: Array[String]) {
	  val input: Scanner = new Scanner(System.in)
	  println("Enter your username: ")
	  val user = input.next()
	  (new Thread(new Producer(user)).start())
	  (new Thread(new Consumer(user)).start())
	}
}