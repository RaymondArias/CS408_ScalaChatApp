import java.util.Scanner

object Control { 
	def main(args: Array[String]) {
	  val input: Scanner = new Scanner(System.in)
	  println("Enter your username: ")
	  val user = input.next()
	  (new Thread(new Producer(user)).start())
	  (new Thread(new Consumer()).start())
	}
}