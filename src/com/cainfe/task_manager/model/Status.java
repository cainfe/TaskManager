package com.cainfe.task_manager.model;

import javax.swing.ImageIcon;

public enum Status {
	INCOMPLETE, COMPLETE;

	public ImageIcon getIcon() {
		return new ImageIcon("images/" + this.toString() + ".png");
	}
}
