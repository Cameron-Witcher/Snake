package me.cameron.snake.utils.meta;

import java.awt.Graphics;

public interface GameObject {
	
	public void draw(Graphics g);
	
	public int getX();
	
	public int getY();
	
	
	public void die();
}
