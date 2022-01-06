package View;

import javax.swing.JPanel;

import Controller.Game;
import Model.Identity;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;

public class ConfigPane extends JPanel {

	private JButton startButton;
	private JButton exitButton;
	private Game game;
	
	/**
	 * Create the panel.
	 */
	public ConfigPane(ConfigFrame configFrame) {
		this.setLayout(null);
		startButton = new JButton("New game");
		exitButton = new JButton("Exit");
		//按钮凸起或者凹下
//		startButton.setBorder(BorderFactory.createRaisedBevelBorder());
//		exitButton.setBorder(BorderFactory.createLoweredBevelBorder());
		//透明效果
//		startButton.setContentAreaFilled(false);
		
		startButton.setBounds(configFrame.getWidth()/2 - 50, configFrame.getHeight()/3, 100, 30);
		exitButton.setBounds(configFrame.getWidth()/2 - 50, configFrame.getHeight()/3 + 50, 100, 30);
		startButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				Game model = new Game();
				GameFrame gameFrame = new GameFrame(model);
				

				gameFrame.setVisible(true);
				configFrame.dispose();
				
			}
		});
		exitButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				System.exit(0);
				
			}
		});
		this.add(startButton);
		this.add(exitButton);
		
	}
	
	

}
