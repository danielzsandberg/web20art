import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import winterwell.jtwitter.Twitter;
import winterwell.jtwitter.Twitter.Status;


public class TweetController 
{
	private LinkedList<String> previousTweetRepository; /**Contains all of the tweets printed on the screen*/
	private ArrayList<String> tweetsToPrint; /**Contains all of the tweets that need to printed by the current loop*/
	private boolean firstLoop; /**True if in the first loop*/
	
	public TweetController()
	{
		previousTweetRepository = new LinkedList<String>();
		tweetsToPrint = new ArrayList<String>();
		firstLoop = true;
	}
	
	private void getTweetStrings()
	{
		
	}
	
	public Tweet[] getTweets()
	{
		//Search twitter
		List<Status> newTweets = new Twitter().search("Maia Campbell");
		ArrayList<String> newTweetsString = new ArrayList<String>();
		
		//Convert the list of statuses to a list of strings (necessary for status comparison)
		for(int i = 0; i < newTweets.size(); i++)
		{
			newTweetsString.add(newTweets.get(i).getText());
		}
		
		//Determine where on the list to start reading
		int endIndex = 0;
		if(previousTweetRepository.size() != 0)
		{
			endIndex = newTweetsString.indexOf(previousTweetRepository.get(0));
		}
		
		//Append the first tweet to the beginning
		if(firstLoop)
		{
			previousTweetRepository.add(newTweetsString.get(0));
			System.out.println(newTweetsString.get(0));
			firstLoop = false;
		}
		
		//Add to the tweet repository and to the tweetsToPrint list
		for(int i = 0, j = endIndex - 1; i < endIndex; i++, j--)
		{
			previousTweetRepository.add(0, newTweetsString.get(j));
			System.out.println(newTweetsString.get(j));
			tweetsToPrint.add(newTweetsString.get(i));
		}
		
		//Conversion of tweetsToPrint into Tweet array
		Object[] newTweetStrings = tweetsToPrint.toArray();
		Tweet[] toReturn = new Tweet[newTweetStrings.length];
		for(int i = 0; i < newTweetStrings.length; i++)
		{
			toReturn[i] = new Tweet((String)newTweetStrings[i], View.WIDTH, View.HEIGHT);
		}
		
		//Reset tweetsToPrint list
		tweetsToPrint.clear();
			
		return toReturn;
	}

}
