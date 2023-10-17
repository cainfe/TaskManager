package com.cainfe.task_manager.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.cainfe.task_manager.DatabaseHandler;
import com.cainfe.task_manager.Task;

class DatabaseHandlerTest {
	private DatabaseHandler databaseHandler;

	@BeforeEach
	void setUp() throws Exception {
		databaseHandler = new DatabaseHandler();
		databaseHandler.connect("tasks.db");
	}

	@AfterEach
	void tearDown() throws Exception {
		// TODO: Close database connection
	}

	@Test
	void testConnect() throws Exception {
		assertTrue(new File("tasks.db").exists());
	}

	@Test
	void testInsertGetTask() throws Exception {
		String title = "test task";
		Task task = new Task(title);
		Task taskWithId = new Task(title);
		taskWithId.setIdIfNotSet(3);
		databaseHandler.insertTask(task);
		task.setIdIfNotSet(1);
		databaseHandler.insertTask(taskWithId);
		assertEquals(task, databaseHandler.getTask(1));
		assertEquals(taskWithId, databaseHandler.getTask(3));
	}

}
