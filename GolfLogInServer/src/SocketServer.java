import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SocketServer {
	int portNumber = 12345;
	ArrayList<Player> Players = new ArrayList<>(); // Instantiate a new ArrayList to store Player Objects
	
	public void runServer()
	{
		try
		{
		ServerSocket socketServer = new ServerSocket(portNumber);
		Socket logInSocket = socketServer.accept(); // Connect to client
		System.out.println("Connected to client");
		
		LogInRunnable m = new LogInRunnable(logInSocket, Players); // Start a new thread and pass the clients connection array list to the thread
		m.run();
		}
		catch(IOException e)
		{
			
		}
	}
}

