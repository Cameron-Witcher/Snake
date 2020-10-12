package me.cameron.snake.objects;

import java.awt.Color;
import java.awt.Graphics;

import me.cameron.snake.utils.Utils;
import me.cameron.snake.utils.meta.Collider;

public class Wall implements Collider {

	int x;
	int y;

	public Wall(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(Color.GRAY);
		g.fillRect(x * Utils.getAGSX(), y * Utils.getAGSY(), Utils.getAGSX(), Utils.getAGSY());
	}

	@Override
	public int getX() {
		return x;
	}

	@Override
	public int getY() {
		return y;
	}

	@Override
	public void die() {
		// TODO Auto-generated method stub
		
	}

}
