package witchHuntView;



import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class BackgroundPanel extends JPanel {

	Image im;
	public BackgroundPanel(Image im)
	{
	   this.im=im;
	   this.setOpaque(true);
	}
	
	public BackgroundPanel(String fileName) throws IOException {
	    im = ImageIO.read(new File(fileName));
	  }
	@Override
	//Draw the back ground.
	public void paintComponent(Graphics g)
	{
	   super.paintComponents(g);
	   g.drawImage(im,0,0,340,330, this);

	}

	

}
