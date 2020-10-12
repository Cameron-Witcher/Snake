package me.cameron.snake.utils;

import me.cameron.snake.Main;
import me.cameron.snake.objects.Apple;
import me.cameron.snake.objects.Snake;
import me.cameron.snake.utils.meta.GameObject;

public class Utils {

	public static int getAGSX() {
		return (int) Main.getWindow().getScreen().getAGSX();
	}

	public static int getAGSY() {
		return (int) Main.getWindow().getScreen().getAGSY();
	}

	public static GameObject getObject(int x, int y) {
		for (GameObject object : Main.getWindow().getScreen().objects) {
			if (!(object instanceof Snake)) {
				if (object.getX() == x && object.getY() == y) {
					return object;
				}
			}
		}
		return null;
	}

	public static void removeObject(GameObject object) {
		Main.getWindow().getScreen().objects__remove.add(object);
	}

}
