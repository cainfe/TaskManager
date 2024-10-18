package main.java.com.cainfe.task_manager.model;

import javax.swing.ImageIcon;

import main.java.com.cainfe.task_manager.util.IconLoader;

public enum Status {
	INCOMPLETE, COMPLETE;

	public ImageIcon getIcon() {
		String imageURL = "images/" + this.toString() + ".png";
		return IconLoader.loadIcon(imageURL);
	}
}
