package me.cameron.snake.objects;

import java.awt.Color;
import java.awt.Graphics;

import me.cameron.snake.utils.Utils;
import me.cameron.snake.utils.meta.Food;

public class SuperApple implements Food {

	int x;
	int y;

	public SuperApple(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void draw(Graphics g) {
		g.setColor(Color.YELLOW);
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
		return 5;
	}

	@Override
	public void die() {
		Utils.removeObject(this);
	}

}
