import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import processing.core.PApplet;


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
    	tweetCTRL.setSearchQuery(JOptionPane.showInputDialog("O que a Terra quer dizer a respeito: "));
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
			frameRate(1);
			background(0);
			fill(255);
			ellipse(View.WIDTH/2,View.HEIGHT/2, 25,25);
			
			this.textFont(this.loadFont("DialogInput-14.vlw"));
			
			
		}
		
		public void draw()
		{
			System.out.println("ANIMATING AGAIN!");
			Tweet[] newTweets = tweetCTRL.getTweets();
			for(int i = 0; i < newTweets.length; i++)
			{
				fill(random(255),random(255),random(255));
				text(newTweets[i].getText(), newTweets[i].getRandomX(), newTweets[i].getRandomY());
				System.out.println(newTweets[i].getText());
			}
			fill(255);
			ellipse(View.WIDTH/2,View.HEIGHT/2, 25,25);
		}
		
	}

	
	
	

}
