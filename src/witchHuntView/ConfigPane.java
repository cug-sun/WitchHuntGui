package witchHuntView;

import javax.swing.JPanel;



import WitchHunt.Game;
import WitchHunt.Identity;

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
	private Game model;
	
	/**
	 * Create the panel.
	 */
	public ConfigPane(ConfigFrame configFrame) {
		this.setLayout(null);
		startButton = new JButton("New game");
		exitButton = new JButton("Exit");
		//按钮凹下或者凸起
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
				model.getAccuse()[0] = 2;
				model.getAccuse()[1] = 3;
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
	
	public void setIdentity() {
		String[] options = {"Witch","Villager"};
		String identity = (String)JOptionPane.showInputDialog(null,"You choose to be", "Identity",JOptionPane.INFORMATION_MESSAGE, null,options,options[0] );
		if(identity == "Viilager") {
			model.getPlayerList().get(0).setIdentity(Identity.Villager);
		}
		else {
			model.getPlayerList().get(0).setIdentity(Identity.Witch);
		}
		
	}
	public void setPlayers() {
		Integer[] options = {3,4,5,6};
		int nPlayer = (int)JOptionPane.showInputDialog(null,"Number of players", "Initialize",JOptionPane.INFORMATION_MESSAGE, null,options, options[0]);
		String mes = "Each player has " + 12/nPlayer + " rumour cards";
		JOptionPane.showMessageDialog(null, mes);
		model.setnPlayer(nPlayer);
		model.initPlayer();
	}

}
