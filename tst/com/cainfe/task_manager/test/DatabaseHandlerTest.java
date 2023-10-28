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
		System.out.println(Arrays.toString(insertedTasks.toArray()));
		System.out.println(Arrays.toString(returnedTasks.toArray()));
		
		assertTrue(returnedTasks.containsAll(insertedTasks));
	}

}
