package me.cameron.snake.objects;

import java.awt.Color;
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
		g.setColor(Color.GREEN);
		g.fillOval((int) (x * Utils.getAGSX()), (int) (y * Utils.getAGSY()), (int) Utils.getAGSX(),
				(int) Utils.getAGSY());
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
