package View;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * The component of {@link View.ConfigFrame} to show background image in the start window.
 * <p>
 * Inherits the superclass {@code JPanel}.
 * @author SUN Sun
 *
 */
public class BackgroundPanel extends JPanel {

	private Image im;
	/**
	 * Initializes the panel.
	 * @param im The background image
	 */
	public BackgroundPanel(Image im)
	{
	   this.im=im;
	   this.setOpaque(true);
	}
	
	
	/**
	 * Overrides the method {@code paintComponent()} to draw the background image.
	 * <p>
	 * Uses {@code drawImage()} to paint the background on the panel.
	 */
	@Override
	public void paintComponent(Graphics g)
	{
	   super.paintComponents(g);
	   g.drawImage(im,0,0,340,330, this);

	}

	

}
