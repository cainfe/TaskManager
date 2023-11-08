package com.cainfe.task_manager.service;

import java.awt.event.ActionEvent;
import java.sql.SQLException;

import javax.swing.AbstractAction;

public class DeleteTaskWithID extends AbstractAction {
	private DatabaseHandler dbHandler;
	private int taskID;
	
	public DeleteTaskWithID(DatabaseHandler dbHandler, int taskID) {
		this.dbHandler = dbHandler;
		this.taskID = taskID;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			this.dbHandler.deleteTaskWithID(taskID);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}
