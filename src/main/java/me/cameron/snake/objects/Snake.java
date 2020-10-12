package me.cameron.snake.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.util.LinkedList;

import me.cameron.snake.utils.Utils;
import me.cameron.snake.utils.meta.Collider;
import me.cameron.snake.utils.meta.Food;
import me.cameron.snake.utils.meta.Keyable;

public class Snake implements Keyable {

	int x;
	int y;
	
	int length = 0;

	int dx = 0;
	int dy = 0;
	
	LinkedList<Point> mem = new LinkedList<>();

	boolean alive = true;

	public Snake(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void update() {
		if (alive) {
			mem.add(new Point(x,y));
			if(mem.size()>length)
				mem.removeFirst();
			x = x + dx;
			y = y + dy;
			for(Point p : mem)
				if(p.x == x && p.y == y)
					die();
			if (Utils.getObject(x, y) != null) {
				if (Utils.getObject(x, y) instanceof Collider) {
					die();
				}
				if(Utils.getObject(x, y) instanceof Food) {
					Food food = (Food) Utils.getObject(x, y);
					length = length + food.getPower();
					food.die();
				}
			}
		}
	}

	public void die() {
		x = x-dx;
		y = y-dy;
		dx = 0;
		dy = 0;
		alive = false;
	}

	public void draw(Graphics g) {
		g.setColor(alive ? Color.GREEN : Color.RED);
		for(Point p : mem)
			g.fillRect((int) ((p.x * Utils.getAGSX())), (int) ((p.y * Utils.getAGSY())), (int) Utils.getAGSX(),
					(int) Utils.getAGSY());
		g.fillRect((int) ((x * Utils.getAGSX())), (int) ((y * Utils.getAGSY())), (int) Utils.getAGSX(),
				(int) Utils.getAGSY());
	}

	@Override
	public void keyPressed(KeyEvent e) {

		int key = e.getKeyCode();

		if (key == KeyEvent.VK_W) {
			if (dy == 0) {
				dy = -1;
				dx = 0;
			}

		}
		if (key == KeyEvent.VK_A) {
			if (dx == 0) {
				dx = -1;
				dy = 0;
			}

		}
		if (key == KeyEvent.VK_S) {
			if (dy == 0) {
				dy = 1;
				dx = 0;
			}

		}
		if (key == KeyEvent.VK_D) {
			if (dx == 0) {
				dx = 1;
				dy = 0;
			}

		}
	}

	@Override
	public int getX() {
		return x;
	}

	@Override
	public int getY() {
		// TODO Auto-generated method stub
		return y;
	}

}
