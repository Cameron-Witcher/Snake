package me.cameron.snake.utils;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import me.cameron.snake.Main;
import me.cameron.snake.objects.Snake;
import me.cameron.snake.objects.Wall;
import me.cameron.snake.utils.meta.GameObject;
import sun.awt.image.ToolkitImage;

public class Utils {

	public static double getAGSX() {
		return Main.getWindow().getScreen().getAGSX();
	}

	public static double getAGSY() {
		return Main.getWindow().getScreen().getAGSY();
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
		Image img = ImageUtils.getImage("images/"+level+".png");
		BufferedImage b = ((ToolkitImage) img).getBufferedImage();
//		BufferedImage img = ImageUtils.toBufferedImage(ImageUtils.getImage("images/" + level + ".png"));
//		Main.getWindow().getScreen().setGridSize(img.getWidth(null));
		
//		for(int x=1;x!=b.getWidth();x++) {
//			for(int y=1;y!=b.getHeight();y++) {
//				if(b.getRGB(x, y) 
//						== Color.decode("#5B5B5B").getRGB()) {
//					objects.add(new Wall(x,y));
//				}
//			}
//		}
		
		return objects;
	}

	public static Color generateColor(int seed, double frequency, double shift) {

		int amp = 105;
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
