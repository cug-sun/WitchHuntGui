package View;

import javax.swing.JPanel;

import Controller.Game;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

/**
 * The main component of {@link View.ConfigFrame}.
 * <p>
 * Inherits the super class {@code JPanel}.
 * @author Sun
 *
 */
public class ConfigPane extends JPanel {

	private JButton startButton;
	private JButton exitButton;
	private Game game;
	
	/**
	 * Initializes the panel, adds the actionListener.
	 */
	public ConfigPane(ConfigFrame configFrame) {
		this.setLayout(null);
		startButton = new JButton("New game");
		exitButton = new JButton("Exit");
		startButton.setBounds(configFrame.getWidth()/2 - 50, configFrame.getHeight()/3, 100, 30);
		exitButton.setBounds(configFrame.getWidth()/2 - 50, configFrame.getHeight()/3 + 50, 100, 30);
		startButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				Game model = new Game();
				GameFrame gameFrame = new GameFrame(model);
				

				gameFrame.setVisible(true);
				configFrame.dispose();
				
			}
		});
		exitButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				System.exit(0);
				
			}
		});
		this.add(startButton);
		this.add(exitButton);
		
	}
	
	

}
