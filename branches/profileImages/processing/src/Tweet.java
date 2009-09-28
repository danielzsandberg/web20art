import java.util.Random;


public class Tweet 
{
	private String text;
	private int frameWidth;
	private int frameHeight;
	private Random r;
	
	public Tweet(String text, int frameWidth, int frameHeight)
	{
		this.text = text;
		this.frameWidth = frameWidth;
		this.frameHeight = frameHeight;
		r = new Random();
	}
	
	public String getText()
	{
		return text;
	}
	
	public int getRandomX()
	{
		return r.nextInt(frameWidth);
	}
	
	public int getRandomY()
	{
		return r.nextInt(frameHeight);
	}
	
	public String getRandomFont(int numberOfFontFiles)
	{
		return new Integer(r.nextInt(numberOfFontFiles) + 1).toString() + ".vlw";
	}

}
