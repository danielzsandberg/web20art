package mainPackage;

public class Tweet 
{
	private String text;
	private String imageURL;
	
	public Tweet(String text, String imageURL)
	{
		this.text = text;
		this.imageURL = imageURL;
	}

	public String getText() {
		return text;
	}

	public String getImageURL() {
		return imageURL;
	}
	
	

}
