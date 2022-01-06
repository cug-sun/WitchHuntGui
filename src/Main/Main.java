package Main;

import java.awt.EventQueue;
import java.io.IOException;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import View.ConfigFrame;




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
