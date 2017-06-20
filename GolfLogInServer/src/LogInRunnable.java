import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LogInRunnable implements Runnable {
	
	protected Socket logInSocket = null;
	ArrayList<Player> Players;
	String clientText;
	String userInput;
	
	public LogInRunnable(Socket logInSocket, ArrayList<Player> Players) //Thread constructor taking which takes client connection and Array List of player objects
	{
		this.logInSocket = logInSocket;
		this.Players = Players;
	}
	
	public void run(){
		try
		{
		PrintWriter out = new PrintWriter(logInSocket.getOutputStream(),true);
		BufferedReader in = new BufferedReader(new InputStreamReader(logInSocket.getInputStream()));

		while(true) //
		{
		userInput = in.readLine(); // receive user's choice from the client
			switch(userInput)
			{
			case "register":
				Player temp = new Player(in.readLine()); //Instantiates a player object 
				out.println("Your Code is " + temp.code); // send player code to client
				Players.add(temp); // Player object passed to the Array list
				break;
				
			case "score":
				String compCode;
				int score;
				compCode = in.readLine();
				score = Integer.parseInt(in.readLine());
				for (int i = 0; i < Players.size(); i ++) // loop through Player list and find Position where codes are equal
				{
				Player temp1 = Players.get(i); 
					if(temp1.code.equals(compCode)) // Where codes are equal, add new score
					{
						temp1.score = score;
						int position = Players.indexOf(temp1);
						Players.set(position, temp1);
					}
				}
				out.println("Score Registered"); // communicate success to client
				break;
				
			case "high":
				List<Integer> highscore = new ArrayList<Integer>(); // Create a new List of integers to store Player scores
				for (int i = 0; i < Players.size(); i ++)
				{
					Player temp2 = Players.get(i);
					int playerScore = temp2.score; 
					highscore.add(playerScore); // Add player score to list
					Collections.sort(highscore);// sort the list to place the lowest score at position zero
				}
				int winner = highscore.get(0); // set the lowest score as winner variable
				for(int i = 0; i < Players.size(); i ++)
				{
					Player temp3 = Players.get(i);
					if(winner == temp3.score ) // find the Player object with same lowest score
					{
					out.println("The Player with the lowest score is " + temp3.name + " with " + temp3.score + " points");	
					}
				}
				break;
			}
		}
		}
		catch(IOException e)
		{
			
		}
	}

}
