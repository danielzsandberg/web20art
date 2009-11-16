import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentLinkedQueue;

import winterwell.jtwitter.Twitter;
import winterwell.jtwitter.Twitter.Status;


public class TweetController extends TimerTask
{
	
	private LinkedList<String> previousTweetRepository; /**Contains all of the tweets printed on the screen*/
	private boolean firstLoop; /**True if in the first loop*/
	private View view;
	private Queue<Tweet> tweetQueue;
	private String searchQuery;
	
	private static final String DELIMITER = "                                              ";
	
	

	public TweetController()
	{
		previousTweetRepository = new LinkedList<String>();
		tweetQueue = new ConcurrentLinkedQueue<Tweet>();
		firstLoop = true;
		view = new View(this);
		view.setVisible(true);
	}
	
	
	public void getTweets()
	{
		
		//Search twitter
		List<Status> newTweets = new Twitter().search(searchQuery);
	
		ArrayList<String> newTweetsString = new ArrayList<String>();
		
		//Convert the list of statuses to a list of strings (necessary for status comparison)
		for(int i = 0; i < newTweets.size(); i++)
		{
			try 
			{
				newTweetsString.add(newTweets.get(i).getText() + TweetController.DELIMITER + newTweets.get(i).getUser().getProfileImageUrl().toURL().toString());
			} 
			catch (MalformedURLException e) 
			{
				e.printStackTrace();
			}
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
			firstLoop = false;
		}
		
		ArrayList<String> tweetsToPrint = new ArrayList<String>();

		//Add to the tweet repository and to the tweetsToPrint list
		for(int i = 0, j = endIndex - 1; i < endIndex; i++, j--)
		{
			previousTweetRepository.add(0, newTweetsString.get(j));
			tweetsToPrint.add(newTweetsString.get(i));
		}
		
		//Conversion of tweetsToPrint into Tweet array
		Object[] newTweetStrings = tweetsToPrint.toArray();
		Tweet[] toReturn = new Tweet[newTweetStrings.length];
		for(int i = 0; i < newTweetStrings.length; i++)
		{
			String[] parsedInfo = ((String)newTweetStrings[i]).split(TweetController.DELIMITER);
			String tweet = parsedInfo[0];
			String imageURL = parsedInfo[1];
			tweetQueue.add(new Tweet(tweet,imageURL, View.WIDTH, View.HEIGHT));
		}
		
		
	}

	
	public static void main(String[] args)
	{
		TimerTask ctrl = new TweetController();
		Timer timer = new Timer();
		timer.schedule(ctrl, 0, 8000);
	}
	public void setSearchQuery(String searchQuery) {
		this.searchQuery = searchQuery;
	}


	public Tweet nextTweet() {
		return tweetQueue.poll();
	}
	
	public void run() 
	{
		getTweets();
		System.out.println("Tamanho do Twitter repository: "+tweetQueue.size());
	}
}
