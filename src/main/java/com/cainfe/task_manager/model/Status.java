package main.java.com.cainfe.task_manager.model;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public enum Status {
	INCOMPLETE, COMPLETE;

	public ImageIcon getIcon() {
		try {
			BufferedImage image = ImageIO.read(this.getClass().getResourceAsStream("/images/" + this.toString() + ".png"));
			return new ImageIcon(image);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return new ImageIcon(); // TODO: Probably should return a placeholder image.
	}
}
