package me.cameron.snake.objects;

import java.awt.Graphics;

import me.cameron.snake.utils.Utils;
import me.cameron.snake.utils.meta.Food;

public class Apple implements Food {
	
	int x;
	int y;
	
	public Apple(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void draw(Graphics g) {
		g.fillOval(x*Utils.getAGSX(), y*Utils.getAGSY(), Utils.getAGSX(), Utils.getAGSY());
	}
	
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}

	@Override
	public int getPower() {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public void die() {
		Utils.removeObject(this);
	}

}
