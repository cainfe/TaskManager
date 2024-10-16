package main.java.com.cainfe.task_manager.ui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;

import main.java.com.cainfe.task_manager.model.Task;

public class TaskPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	public TaskPanel() {
		setLayout(new GridBagLayout());
		
		for (int i = 0; i < 25; i++) {
			GridBagConstraints constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = i;
			constraints.weightx = 1;
			constraints.fill = GridBagConstraints.HORIZONTAL;
			
			if (i == 24) {
				constraints.weighty = 1;
				constraints.anchor = GridBagConstraints.LINE_START;
			}
			
			add(new TaskRow(new Task("test task " + i)), constraints);
		}
	}

}
