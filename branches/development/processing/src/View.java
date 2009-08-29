import processing.core.PApplet;


public class View extends PApplet
{
	public static int WIDTH = 700;
	public static int HEIGHT = 700;
	
	private TweetController tweetCTRL;
	
	public View()
	{
		tweetCTRL = new TweetController();
	}
	public void setup()
	{
		size(WIDTH,HEIGHT);
		//frameRate(20);
		background(0);
		fill(255);
		ellipse(WIDTH/2,HEIGHT/2, 25,25);
		
		this.textFont(this.loadFont("DialogInput-14.vlw"));
		
		
	}
	
	public void draw()
	{
		Tweet[] newTweets = tweetCTRL.getTweets();
		for(int i = 0; i < newTweets.length; i++)
		{
			fill(random(255),random(255),random(255));
			text(newTweets[i].getText(), newTweets[i].getRandomX(), newTweets[i].getRandomY());
		}
		fill(255);
		ellipse(WIDTH/2,HEIGHT/2, 25,25);
	}
	
}
