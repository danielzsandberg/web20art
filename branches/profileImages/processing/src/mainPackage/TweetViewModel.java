package mainPackage;
import java.util.Random;

/**
 * This class represents the data structure of a Tweet displayed on the screen.
 * 
 * @author Daniel Sandberg
 * */

public class TweetViewModel 
{
	private String text; /**The text of the tweet.*/
	private String imageURL; /**The URL of the profile image of the user that tweets this tweet*/
	private int frameWidth; /**The width of the JFrame that contains application.*/
	private int frameHeight; /**The height of the JFrame that contains application.*/
	private int x; /**The x coordinate of the location of the tweet on the screen.*/
	private int y; /**The y coordinate of the location of the tweet on the screen.*/
	private Random r; /**The random number generator for the coordinates of the tweet.*/
	private Tweet tweet;
	
	
	
	public TweetViewModel(Tweet tweet, int frameWidth, int frameHeight)
	{
		this.tweet = tweet;
		this.frameWidth = frameWidth;
		this.frameHeight = frameHeight;
		r = new Random();
		this.x = r.nextInt(frameWidth);
		this.y = r.nextInt(frameHeight);
	
	}
	
	public String getText()
	{
		return tweet.getText();
	}
	
	public int getX()
	{
		return x;
	}
	
	public int getY()
	{
		return y;
	}
	/**Get a random font for the Tweet
	 * 
	 * @return String
	 * */
	public String getRandomFont(int numberOfFontFiles)
	{
		return new Integer(r.nextInt(numberOfFontFiles) + 1).toString() + ".vlw";
	}
	/**Based on the x coordinate of the location of the text, determine the x coordinate of the location for the profile image
	 * 
	 * @return int
	 * */
	public int getImageX()
	{
		return x - 55;
	}
	/**Based on the y coordinate of the location of the text, determine the y coordinate of the location for the profile image
	 * 
	 * @return int
	 * */
	public int getImageY()
	{
		return y-10;
	}
	
	public String getImageURL()
	{
		return tweet.getImageURL();
	}

}
