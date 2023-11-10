package main.java.com.cainfe.task_manager.service;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JFrame;

public class ExitApplication extends AbstractAction {
	private static final String NAME = "Exit";
	private static final String DESC = "Exit the application";
	private JFrame parentFrame;

	public ExitApplication() {
		super(NAME);
		putValue(SHORT_DESCRIPTION, DESC);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.exit(0);
	}

	public void setParentFrame(JFrame parentFrame) {
		this.parentFrame = parentFrame;
	}

	public JFrame getParentFrame() {
		return this.parentFrame;
	}
}
