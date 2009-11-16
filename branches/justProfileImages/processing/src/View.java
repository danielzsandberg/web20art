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


public class View extends JFrame
{
	public static final int IMAGE_WIDTH = 48;
	public static final int IMAGE_HEIGHT = 48;
	public static final int WIDTH = IMAGE_WIDTH * 15;
	public static final int HEIGHT = IMAGE_HEIGHT * 15;
	
	private ProcessingApplet embed;
	private TweetController tweetCTRL;
	private int drawingInterval;
	private int currentX;
	private int currentY;
	
	public View(TweetController tweetCTRL) 
	{
		
        super("");
        this.currentX = 0;
        this.currentY = 0;
        this.drawingInterval = 0;
        this.tweetCTRL = tweetCTRL;
        String searchQuery = JOptionPane.showInputDialog(this,"Quem esta falando sobre: (Who's talking about:)", "", JOptionPane.PLAIN_MESSAGE);
        if(searchQuery == null)
        	System.exit(0);
        //this.setIconImage(new ImageIcon("icon.png").getImage());

    	tweetCTRL.setSearchQuery(searchQuery);
        setSize(WIDTH,HEIGHT + 24);
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
	public int getCurrentX()
	{
		return currentX;
	}
	
	public int getCurrentY()
	{
		return currentY;
	}
	
	public void addImage()
	{
		if(currentX < View.WIDTH)
		{
			currentX += View.IMAGE_WIDTH;
		}
		else if(currentY < View.HEIGHT)
		{
			currentX = 0;
			currentY += View.IMAGE_HEIGHT;
		}
		else
		{
			currentX = 0;
			currentY = 0;
		}
		
	}
	
	class ProcessingApplet extends PApplet
	{
		public void setup()
		{
			size(View.WIDTH,View.HEIGHT);
			frameRate((float) 30);
			background(0);
			fill(255);
			//ellipse(View.WIDTH/2,View.HEIGHT/2, 100,100);

		}
		
		public void draw()
		{
			try{
				Tweet currentTweet = tweetCTRL.nextTweet();
				if(currentTweet != null)
				{
					this.textFont(this.loadFont(currentTweet.getRandomFont(18)));
					fill(random(255),random(255),random(255));
					//text(currentTweet.getText(), currentTweet.getX(), currentTweet.getY());
					PImage currentProfilePic = loadImage(currentTweet.getImageURL());
					if(currentProfilePic.height != 48 || currentProfilePic.width != 48)
						currentProfilePic.resize(View.IMAGE_WIDTH, View.IMAGE_HEIGHT);
					image(currentProfilePic, getCurrentX(), getCurrentY());
					addImage();
					//ellipse(View.WIDTH/2,View.HEIGHT/2, 100,100);
				}
				System.out.println("Drawing itera‹o " + drawingInterval++ + " acabado!");
			}catch(Exception e){
				System.out.println("Error!");
			}
		}
		
		
		
	}

	
	
	

}
