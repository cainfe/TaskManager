package main.java.com.cainfe.task_manager.ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import main.java.com.cainfe.task_manager.model.Task;
import main.java.com.cainfe.task_manager.util.IconLoader;

public class TaskRow extends JPanel {
	private Task task;
	
	public TaskRow(Task task) {
		this.task = task;
		GridBagConstraints constraints;
		
		setLayout(new GridBagLayout());
		
		constraints = new GridBagConstraints();
		constraints.gridx = 0;
		add(this.createStatusSection(), constraints);
		
		constraints = new GridBagConstraints();
		constraints.gridx = 1;
		constraints.weightx = 1;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		add(this.createTitleSection(), constraints);
		
		constraints = new GridBagConstraints();
		constraints.gridx = 2;
		add(this.createDeleteSection(), constraints);
	}
	
	private Component createStatusSection() {
		JButton statusButton = new JButton();
		statusButton.setIcon(task.getStatusIcon());
		statusButton.setBackground(Color.WHITE);
		statusButton.setBorder(null);
		return statusButton;
	}
	
	private Component createTitleSection() {
		JTextArea titleTextArea = new JTextArea(task.getTitle());
		return titleTextArea;
	}
	
	private Component createDeleteSection() {
		// Trash icon when clicked deletes the task
		JButton deleteButton = new JButton();
		deleteButton.setIcon(IconLoader.loadIcon("images/trash.png"));
		deleteButton.setBackground(Color.WHITE);
		deleteButton.setBorder(null);
		return deleteButton;
	}
	
	
}