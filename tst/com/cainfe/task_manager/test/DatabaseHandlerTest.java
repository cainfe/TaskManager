package com.cainfe.task_manager.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.cainfe.task_manager.model.Task;
import com.cainfe.task_manager.service.DatabaseHandler;

class DatabaseHandlerTest {
	private DatabaseHandler databaseHandler;
	private String testDatabaseName = "testtasks.db";

	@BeforeEach
	void setUp() throws Exception {
		databaseHandler = new DatabaseHandler();
		databaseHandler.connect(testDatabaseName);
	}

	@AfterEach
	void tearDown() throws Exception {
		this.deleteDatabase();
	}

	@Test
	void testConnect() throws Exception {
		assertTrue(new File(testDatabaseName).exists());
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
		assertEquals(null, databaseHandler.getTask(101));

		this.deleteDatabase();
		databaseHandler.connect(testDatabaseName);
		assertEquals(null, databaseHandler.getTask(1));
	}

	@Test
	void testGetAllTasks() throws Exception {
		List<Task> insertedTasks = new ArrayList<>();

		Task task1 = new Task("hello");
		task1.setIdIfNotSet(1);
		Task task2 = new Task("Hello to you");
		task2.setIdIfNotSet(2);
		Task task3 = new Task("No, I say hello!");
		task3.setIdIfNotSet(3);
		insertedTasks.add(task1);
		insertedTasks.add(task2);
		insertedTasks.add(task3);
		for (Task task : insertedTasks) {
			databaseHandler.insertTask(task);
		}

		List<Task> returnedTasks = Arrays.asList(databaseHandler.getAllTasks());

		assertEquals(3, returnedTasks.size());
		assertTrue(returnedTasks.containsAll(insertedTasks));

		this.deleteDatabase();
		databaseHandler.connect(testDatabaseName);
		assertEquals(0, databaseHandler.getAllTasks().length);
	}

	void deleteDatabase() throws Exception {
		databaseHandler.close();
		new File(testDatabaseName).delete();
	}

}
