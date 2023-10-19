package com.cainfe.task_manager.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.cainfe.task_manager.model.Task;
import com.cainfe.task_manager.service.DatabaseHandler;

class DatabaseHandlerTest {
	private DatabaseHandler databaseHandler;

	@BeforeEach
	void setUp() throws Exception {
		databaseHandler = new DatabaseHandler();
		databaseHandler.connect("testtasks.db");
	}

	@AfterEach
	void tearDown() throws Exception {
		databaseHandler.close();
		new File("testtasks.db").delete();
	}

	@Test
	void testConnect() throws Exception {
		assertTrue(new File("testtasks.db").exists());
	}

	@Test
	void testInsertGetTask() throws Exception {
		String title = "test task";
		Task taskWithoutId = new Task(title);
		Task taskWithId = new Task(title);
		taskWithId.setIdIfNotSet(3);
		databaseHandler.insertTask(taskWithoutId);
		taskWithoutId.setIdIfNotSet(1);
		databaseHandler.insertTask(taskWithId);
		assertEquals(taskWithoutId, databaseHandler.getTask(1));
		assertEquals(taskWithId, databaseHandler.getTask(3));
	}

}
