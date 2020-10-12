package me.cameron.snake;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

import me.cameron.snake.objects.Apple;
import me.cameron.snake.objects.Snake;
import me.cameron.snake.objects.Wall;
import me.cameron.snake.utils.meta.GameObject;
import me.cameron.snake.utils.meta.Keyable;

public class Screen extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int DELAY = 75;
	Timer timer;
	Random random;

	private int gridSize = 50;
	double agsx;
	double agsy;

	public List<GameObject> objects = new ArrayList<>();
	public List<GameObject> objects__remove = new ArrayList<>();

	int lifetime = 0;

	private boolean paused = false;

	public Screen() {
		setBackground(Color.WHITE);
		timer = new Timer(DELAY, this);
		timer.start();
		random = new Random();
		MouseListener mouseListener = new MouseListener();
		addKeyListener(new TAdapter());
		addMouseMotionListener(mouseListener);
		addMouseListener(mouseListener);
		setFocusable(true);
		requestFocusInWindow();
		load();

	}

	public void load() {
		objects.add(new Snake(new Random().nextInt(gridSize-1), new Random().nextInt(gridSize-1)));
		
		for(int i=0;i!=gridSize;i++) {
			objects.add(new Wall(i,0));
			objects.add(new Wall(i,gridSize));
			objects.add(new Wall(0,i));
			objects.add(new Wall(gridSize,i));
		}
		
		objects.add(new Apple(new Random().nextInt(gridSize-1),new Random().nextInt(gridSize-1)));
	}

	public void unload() {
		objects.clear();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		repaint();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
//		g.setColor(Color.BLACK);
//		g.fillRect(0, 0, getWidth(), getHeight());
		agsx = Math.ceil(Main.getWindow().getWidth() / getGridSize()-1);
		agsy = Math.ceil(Main.getWindow().getHeight() / getGridSize())-1;
//		for (int i = 0; !(i >= Main.getWindow().getWidth() / agsx); i++) {
//			for (int j = 0; !(j >= Main.getWindow().getHeight() / agsy); j++) {
//				g.setColor(generateColor(i + j, 0.1));
//				g.fillRect((int) (i * agsx), (int) (j * agsy), (int) agsx, (int) agsy);
//			}
//		}
		
		for(GameObject object : objects__remove) {
			objects.remove(object);
		}
		objects__remove.clear();
		
		for(GameObject object : objects) {
			
			if(object instanceof Snake)
				((Snake)object).update();
			object.draw(g);
		}
		if(lifetime >= new Random().nextInt(50)+20) {
			objects.add(new Apple(new Random().nextInt(gridSize-1),new Random().nextInt(gridSize-1)));
			lifetime = 0;
			
		}

		for (int i = 0; !(i >= Main.getWindow().getWidth() / agsx); i++) {
			for (int j = 0; !(j >= Main.getWindow().getHeight() / agsy); j++) {
				g.setColor(Color.GRAY);
				g.drawLine(0, j * (int)agsy, getWidth(), j * (((int)agsy)));
				g.drawLine(j * (int)agsx, 0, j * (int)agsx, getHeight());
			}
		}

			lifetime = lifetime + 1;

		g.dispose();

	}

	public Color generateColor(int seed, double frequency) {
		return generateColor(seed, frequency, 0);
	}

	public Color generateColor(int seed, double frequency, double shift) {

		int amp = 50;
		if (amp > 127)
			amp = 127;
		int peak = 255 - amp;
		int red = (int) (Math.sin(frequency * (seed + shift) + 0) * amp + peak);
		int green = (int) (Math.sin(frequency * (seed + shift) + 2 * Math.PI / 3) * amp + peak);
		int blue = (int) (Math.sin(frequency * (seed + shift) + 4 * Math.PI / 3) * amp + peak);
		if (red > 255)
			red = 255;
		if (green > 255)
			green = 255;
		if (blue > 255)
			blue = 255;

		return new Color(red, green, blue);
	}

	private void reset() {
	}

	private class TAdapter extends KeyAdapter {

		@Override
		public void keyPressed(KeyEvent e) {
			
			for(GameObject object : objects) {
				if(object instanceof Keyable) {
					
					((Keyable)object).keyPressed(e);
				}
			}
			
			
			if (e.getKeyCode() == KeyEvent.VK_R) {
				unload();
				load();
			}
			if (e.getKeyCode() == KeyEvent.VK_SPACE) {
				pause();
			}
			if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
				reset();
				load();
			}

		}
	}

	private class MouseListener extends MouseAdapter {

		@Override
		public void mouseReleased(MouseEvent e) {
			int x = (int) Math.ceil(e.getX() / agsx);
			int y = (int) Math.ceil(e.getY() / agsy);
		}

		@Override
		public void mouseMoved(MouseEvent e) {

			// mx = e.getX();
			// my = e.getY();

		}
	}

	public void pause() {
		paused = !paused;
	}

	public int getGridSize() {
		return gridSize;
	}

	public void setGridSize(int gridSize) {
		this.gridSize = gridSize;
	}

	public double getAGSX() {
		return agsx;
	}

	public double getAGSY() {
		return agsy;
	}

}
