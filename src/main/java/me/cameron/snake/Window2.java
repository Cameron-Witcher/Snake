package me.cameron.snake;

import javax.swing.JFrame;

public class Window2 extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	Screen screen;
	
	public Window2(){
		screen = new Screen();
		add(screen);
		pack();
		setSize(500, 500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public Screen getScreen() {
		return screen;
	}

}
