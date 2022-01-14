package Main;

import java.awt.EventQueue;
import java.io.IOException;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import View.ConfigFrame;

/**
 * WitchHunt! est Un jeu de cartes,Les joueurs choisissent leur identité et utilisent leurs mains. 
 * Le premier à obtenir cinq points gagne.
 * <p>
 *
 * @author SUN Sun 
 * @author ZHANG xiao
 * @version v1.0
 * 
 */


public class Main {

	public static void main(String[] args) throws IOException{
		// TODO 自动生成的方法存根
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConfigFrame configFrame = new ConfigFrame();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
