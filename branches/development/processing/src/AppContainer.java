import java.awt.BorderLayout;
import java.awt.Frame;

import javax.swing.JFrame;

import processing.core.PApplet;


public class AppContainer extends JFrame 
{
	public AppContainer() 
	{
        super("web20art");
        
        setSize(700,700);
        setLayout(new BorderLayout());
        PApplet embed = new View();
        add(embed, BorderLayout.CENTER);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // important to call this whenever embedding a PApplet.
        // It ensures that the animation thread is started and
        // that other internal variables are properly set.
        embed.init();
    }
	
	public static void main(String[] args)
	{
		new AppContainer().setVisible(true);
	}

}
