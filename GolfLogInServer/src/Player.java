import java.util.Random;

public class Player {
	public String name;
	public String code;
	public int score;
	
	public Player(String name)
	{
	this.name = name;
	this.code = genCode(); // Assigns randomly generated code to player using below method
	this.score = 0;
	}
	
	private synchronized String genCode() // Synchronised method to ensure that the threaded program can synchronise access to the competition number
	{
	int charLength = 8;
	return String.valueOf(charLength < 1 ? 0: new Random().nextInt((8 * (int) Math.pow(10, charLength - 1)) - 1)
			+ (int) Math.pow(10, charLength - 1));
	}
}
