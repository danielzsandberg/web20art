import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import processing.core.PApplet;
import processing.core.PImage;


public class View extends JFrame
{
	public static final int WIDTH = 700;
	public static final int HEIGHT = 700;
	
	private ProcessingApplet embed;
	private TweetController tweetCTRL;
	
	public View(TweetController tweetCTRL) 
	{
		
        super("web20art");
       
        this.tweetCTRL = tweetCTRL;
    	tweetCTRL.setSearchQuery(JOptionPane.showInputDialog("O que a Terra quer dizer sobre: "));
        setSize(WIDTH,HEIGHT);
        setLayout(new BorderLayout());
        embed = new ProcessingApplet();
        add(embed, BorderLayout.CENTER);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // important to call this whenever embedding a PApplet.
        // It ensures that the animation thread is started and
        // that other internal variables are properly set.
        embed.init();
    }
	
	
	class ProcessingApplet extends PApplet
	{
		public void setup()
		{
			size(View.WIDTH,View.HEIGHT);
			frameRate((float) 1);
			background(0);
			fill(255);
			ellipse(View.WIDTH/2,View.HEIGHT/2, 25,25);
			
			
			
			
		}
		
		public void draw()
		{
			Tweet[] newTweets = tweetCTRL.getTweets();
			for(int i = 0; i < newTweets.length; i++)
			{
				Tweet currentTweet = newTweets[i];
				this.textFont(this.loadFont(newTweets[i].getRandomFont(18)));
				fill(random(255),random(255),random(255));
				text(currentTweet.getText(), currentTweet.getX(), currentTweet.getY());
				PImage currentProfilePic = loadImage(currentTweet.getImageURL());
				image(currentProfilePic, currentTweet.getImageX(), currentTweet.getImageY());
			}
			fill(255);
			ellipse(View.WIDTH/2,View.HEIGHT/2, 25,25);
			System.out.println("One draw iteration finished!");	
		}
		
	}

	
	
	

}
