package Repository;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import mainPackage.Tweet;
import winterwell.jtwitter.Status;
import winterwell.jtwitter.Twitter;
import winterwell.jtwitter.URLConnectionHttpClient;

class WinterwellTweetRepository implements ITweetRepository 
{
	private Twitter twitterClient;
	
	public WinterwellTweetRepository(Twitter twitterClient)
	{
		this.twitterClient = twitterClient;
	}
	
	public List<Tweet> getBySearchTerm(String searchTerm) 
	{
		List<Tweet> tweetsToReturn = new ArrayList<Tweet>();
		
		List<Status> statuses = twitterClient.search(searchTerm);
		
		//Convert the list of statuses to a list of strings (necessary for status comparison)
		for(int i = 0; i < statuses.size(); i++)
		{
			try 
			{
				String text = statuses.get(i).getText();
				String imageURL = statuses.get(i).getUser().getProfileImageUrl().toURL().toString();
				
				tweetsToReturn.add(new Tweet(text, imageURL));
			} 
			catch (MalformedURLException e) 
			{
				e.printStackTrace();
			}
		}
		
		return tweetsToReturn;
	}

}
