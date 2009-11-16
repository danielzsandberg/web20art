import java.util.Random;


public class Tweet 
{
	private String text;
	private String imageURL;
	private int frameWidth;
	private int frameHeight;
	private int x;
	private int y;
	private Random r;
	
	public Tweet(String text, String imageURL, int frameWidth, int frameHeight)
	{
		this.text = text;
		this.imageURL = imageURL;
		this.frameWidth = frameWidth;
		this.frameHeight = frameHeight;
		r = new Random();
		this.x = r.nextInt(frameWidth);
		this.y = r.nextInt(frameHeight);
		
	}
	
	public String getText()
	{
		return text;
	}
	
	public int getX()
	{
		return x;
	}
	
	public int getY()
	{
		return y;
	}
	
	public String getRandomFont(int numberOfFontFiles)
	{
		return new Integer(r.nextInt(numberOfFontFiles) + 1).toString() + ".vlw";
	}
	
	public int getImageX()
	{
		return x - 55;
	}
	
	public int getImageY()
	{
		return y-10;
	}
	
	public String getImageURL()
	{
		return imageURL;
	}

}
