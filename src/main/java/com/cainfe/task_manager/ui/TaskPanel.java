package main.java.com.cainfe.task_manager.ui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;

import main.java.com.cainfe.task_manager.model.Task;

public class TaskPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	public TaskPanel(Task[] tasks) {
		setLayout(new GridBagLayout());
		
		for (int i = 0; i < tasks.length; i++) {
			GridBagConstraints constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = i;
			constraints.weightx = 1;
			constraints.fill = GridBagConstraints.HORIZONTAL;
			
			if (i == tasks.length - 1) {
				constraints.weighty = 1;
				constraints.anchor = GridBagConstraints.NORTH; // Change anchor to NORTH
			}
			
			add(new TaskRow(tasks[i]), constraints);
		}
	}
}
