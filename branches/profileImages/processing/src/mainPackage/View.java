package mainPackage;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import processing.core.PApplet;
import processing.core.PImage;

/**
 * This class is responsible for configuring the JFrame and adding the processing applet (http://processing.org/) inside for animation.
 * 
 * @author Daniel Sandberg
 */
public class View extends JFrame
{
	public static final int WIDTH = 700;
	public static final int HEIGHT = 700;
	
	private ProcessingApplet embed;
	private TweetController tweetCTRL;
	private int drawingInterval;
	
	
	public View(TweetController tweetCTRL) 
	{
		
        super("");
        this.drawingInterval = 0;
        this.tweetCTRL = tweetCTRL;
        String searchQuery = JOptionPane.showInputDialog(this,"O que a Terra quer dizer sobre: (What does the world want to say about: )", "", JOptionPane.PLAIN_MESSAGE);
        if(searchQuery == null)
        	System.exit(0);
        //this.setIconImage(new ImageIcon("icon.png").getImage());

    	tweetCTRL.setSearchQuery(searchQuery);
        setSize(WIDTH,HEIGHT);
        setResizable(false);
        setLayout(new BorderLayout());
        embed = new ProcessingApplet();
        add(embed, BorderLayout.CENTER);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // important to call this whenever embedding a PApplet.
        // It ensures that the animation thread is started and
        // that other internal variables are properly set.
        embed.init();
    }
	
	/**
	 * This internal class contains calls to the Processing API (http://processing.org/) to animate the tweets.
	 * It contains one call to the TweetController to retrieve the next Tweet in the queue and animates that Tweet
	 * with its accompanying profile picture
	 * @author Daniel Sandberg
	 *
	 */
	class ProcessingApplet extends PApplet
	{
		public void setup()
		{
			size(View.WIDTH,View.HEIGHT);
			frameRate((float) 30);
			background(0);
			fill(255);
			ellipse(View.WIDTH/2,View.HEIGHT/2, 100,100);	
		}
		
		public void draw()
		{
			try{
				TweetViewModel currentTweet = tweetCTRL.nextTweet();
				if(currentTweet != null)
				{
					this.textFont(this.loadFont(currentTweet.getRandomFont(18)));
					fill(random(255),random(255),random(255));
					text(currentTweet.getText(), currentTweet.getX(), currentTweet.getY());
					PImage currentProfilePic = loadImage(currentTweet.getImageURL());
					image(currentProfilePic, currentTweet.getImageX(), currentTweet.getImageY());
			
					ellipse(View.WIDTH/2,View.HEIGHT/2, 100,100);
				}
				System.out.println("Drawing itera�‹o " + drawingInterval++ + " acabado!");
			}catch(Exception e){
				System.out.println("Error!");
			}
		}
		
	}

	
	
	

}
