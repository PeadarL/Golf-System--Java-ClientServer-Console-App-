import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class main {

	public static void main(String[] args) {
		String host ="127.0.0.1";		// Set property fields to connect to server
		int portNumber = 12345;
		
		try{ // Try connect to server socket & create methods for IO 
			Socket logInSocket = new Socket(host, portNumber);
			BufferedReader in = new BufferedReader(new InputStreamReader(logInSocket.getInputStream()));
			BufferedReader userConsoleIn = new BufferedReader(new InputStreamReader(System.in));
			PrintWriter out = new PrintWriter(logInSocket.getOutputStream(), true);
			
			while(true) //Create client side console menu
			{
				System.out.println("1. Register Player");
				System.out.println("2. Enter Score");
				System.out.println("3. Who is Winning?");
				System.out.println("Please Enter a Choice: ");
				
				int userInput = Integer.parseInt(userConsoleIn.readLine()); // Get Clients choice for Switch method
				
				switch(userInput){
				case 1:
					System.out.println("Register");
					out.println("register"); // Send string to Servers switch method
					System.out.println("Please Enter your Name");
					String name = userConsoleIn.readLine(); // Read in from clients console
					out.println(name); // Send Client Input to Server
					System.out.println(in.readLine()); // Server responds with confirmation
					break;
				case 2:
					System.out.println("Your Score");
					out.println("score");
					System.out.println("Please Enter your code: ");
					String code = userConsoleIn.readLine();
					out.println(code);
					System.out.println("Please Enter your Score: ");
					String score = userConsoleIn.readLine();
					out.println(score);
					System.out.println(in.readLine());
					break;
				case 3:
					System.out.println("ScoreBoard");
					out.println(("high"));
					System.out.println(in.readLine());
				}
			}
		}
		catch(IOException e)
		{
			
		}
		
	}

}
