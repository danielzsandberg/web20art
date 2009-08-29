import java.util.ArrayList;


public class TweetController 
{
	private Object[] previousTweetRepository;
	
	private Object[] getTweetStrings()
	{
		ArrayList<String> newTweets = new ArrayList<String>();
		//TODO Get the xml file twitter
		//TODO Parse the xml file and store
		//TODO Determine where on the xml file to start reading
		//TODO Below should be a for loop to add all of the new tweets to the list for export out of the method
		newTweets.add("@Eric_theviking I think i might have a #twittercrush on you! *grins*");
		newTweets.add("I have more than 1 #twittercrush they are @souljaboytellem @thalegacy @BenJNewBoyz & @xXAudioMonkXx they all sexayyyy lol");
		newTweets.add("one of MY #twittercrush is @BlueyRobinson");
		newTweets.add("#twittercrush hmmmmmm @ hmmmm @");
		newTweets.add(" #twittercrush is (small voice) @Wale");
		newTweets.add("@toritaylor haha aw tori <3 you're my #twittercrush too :)");
		newTweets.add("thought i just saw #snowcrash on trending topics (combined #snowleopard and #twittercrush). too good to be true...");
		
		Object[] currentTweetRepository = newTweets.toArray();
		
		previousTweetRepository = currentTweetRepository;
		
		return currentTweetRepository;
	}
	
	public Tweet[] getTweets()
	{
		Object[] newTweetStrings = getTweetStrings();
		Tweet[] toReturn = new Tweet[newTweetStrings.length];
		
		
		for(int i = 0; i < newTweetStrings.length; i++)
		{
			toReturn[i] = new Tweet((String)newTweetStrings[i], View.WIDTH, View.HEIGHT);
		}
		
		return toReturn;
	}

}
