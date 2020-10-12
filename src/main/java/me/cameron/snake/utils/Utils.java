package me.cameron.snake.utils;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import me.cameron.snake.Main;
import me.cameron.snake.objects.Snake;
import me.cameron.snake.objects.Wall;
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
	
	public static Color generateColor(int seed, double frequency) {
		return generateColor(seed, frequency, 0);
	}
	public static List<GameObject> loadLevel(String level){
		List<GameObject> objects = new ArrayList<>();
		BufferedImage img = ImageUtils.toBufferedImage(ImageUtils.getImage("images/" + level + ".png"));
		Main.getWindow().getScreen().setGridSize(img.getWidth());
		for(int x=0;x!=img.getWidth();x++) {
			for(int y=0;y!=img.getHeight();y++) {
				if(img.getRGB(x, y) == Color.decode("#5B5B5B").getRGB()) {
					objects.add(new Wall(x,y));
				}
			}
		}
		
		return objects;
	}

	public static Color generateColor(int seed, double frequency, double shift) {

		int amp = 100;
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
}
