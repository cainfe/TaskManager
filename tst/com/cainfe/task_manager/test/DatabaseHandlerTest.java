package com.cainfe.task_manager.test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.sql.SQLException;

import org.junit.jupiter.api.AfterAll;
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
		databaseHandler.insertTask(task);
		assertEquals(task, databaseHandler.getTask(title));
	}

}
