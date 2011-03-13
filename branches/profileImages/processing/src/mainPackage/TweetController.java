package mainPackage;

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

import Repository.ITweetRepository;
import Repository.WinterwellTweetRepository;


/**This controller class is responsible for retrieving streaming and caching tweets in a seperate thread.
 * It also defines and implements a thread to access that cache.
 * 
 * @author Daniel Sandberg
 * */

public class TweetController extends TimerTask
{
	private LinkedList<Tweet> previousTweetRepository; /**Contains all of the tweets printed on the screen*/
	private boolean firstLoop; /**True if in the first loop*/
	private View view; /**Object representing the user interface.*/
	private Queue<TweetViewModel> tweetQueue; /**The Tweet cache in the form of a queue*/
	private String searchQuery; /**The search query from the user*/
	private ITweetRepository tweetRepository;

	public TweetController(ITweetRepository tweetRepository)
	{
		previousTweetRepository = new LinkedList<Tweet>();
		tweetQueue = new ConcurrentLinkedQueue<TweetViewModel>();
		firstLoop = true;
		//Instantiates and makes the view visible
		view = new View(this);
		view.setVisible(true);
		this.tweetRepository = tweetRepository;
	}
	
	/**
	 * 1. Gets the new tweets from the repository 
	 * 2. Add the new tweets to the Queue
	 */
	public void getTweets()
	{
		
		//Search twitter using the Winterwell Twitter library
		List<Tweet> newTweets = tweetRepository.getBySearchTerm(searchQuery);
	
		//Determine where on the list to start reading
		int endIndex = 0;
		if(previousTweetRepository.size() != 0)
		{
			endIndex = newTweets.indexOf(previousTweetRepository.get(0));
		}
		
		//Append the first tweet to the beginning
		if(firstLoop)
		{
			previousTweetRepository.add(newTweets.get(0));
			firstLoop = false;
		}
		
		ArrayList<Tweet> tweetsToPrint = new ArrayList<Tweet>();

		//Add to the tweet repository and to the tweetsToPrint list
		for(int i = 0, j = endIndex - 1; i < endIndex; i++, j--)
		{
			previousTweetRepository.add(0, newTweets.get(j));
			tweetsToPrint.add(newTweets.get(i));
		}
		
		//Conversion of tweetsToPrint into Tweet array
		for(int i = 0; i < newTweets.size(); i++)
		{
			Tweet currentTweet = newTweets.get(i);
			tweetQueue.add(new TweetViewModel(currentTweet, View.WIDTH, View.HEIGHT));
		}
		
		
	}
	
	public static void main(String[] args)
	{
		TimerTask ctrl = new TweetController(new WinterwellTweetRepository());
		Timer timer = new Timer();
		timer.schedule(ctrl, 0, 8000);
	}
	/**Change the search query for the tweets. This is normally accessed by the View object after 
	 * it receives input from the user.
	 **/
	public void setSearchQuery(String searchQuery) {
		this.searchQuery = searchQuery;
	}

	/**
	 * This is used to take the most recent Tweet off of the queue. It is used by the View to access the most
	 * recent Tweet in the cache
	 * @return Tweet
	 */
	public TweetViewModel nextTweet() {
		return tweetQueue.poll();
	}
	
	/**
	 * The only thing that runs in the extra thread is the Tweet extraction and 
	 */
	public void run() 
	{
		getTweets();
		System.out.println("Tamanho do Twitter repository: "+tweetQueue.size());
	}
}
