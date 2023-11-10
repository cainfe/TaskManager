package com.cainfe.task_manager.test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.sql.SQLException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.java.com.cainfe.task_manager.model.Task;
import main.java.com.cainfe.task_manager.service.DatabaseHandler;
import main.java.com.cainfe.task_manager.service.DeleteTaskWithID;

class DeleteTaskWithIDTest {

	@BeforeEach
	void setUp() throws Exception {
		
	}

	@Test
	void testActionPerformed() throws Exception {
		DatabaseHandler dbhandler = new DatabaseHandler();
		dbhandler.connect("testtasks.db");
		Task task = new Task("test task");
		task.setIdIfNotSet(1);
		Task task2 = new Task("test task");
		task.setIdIfNotSet(2);
		DeleteTaskWithID deleteTaskAction = new DeleteTaskWithID(dbhandler, task.getId());
		dbhandler.insertTask(task);
		dbhandler.insertTask(task2);
		assertNotNull(dbhandler.getTask(1));
		deleteTaskAction.actionPerformed(null);
		assertNull(dbhandler.getTask(1));
		new File("testtasks.db").delete();
		
		dbhandler.close();
	}

}
