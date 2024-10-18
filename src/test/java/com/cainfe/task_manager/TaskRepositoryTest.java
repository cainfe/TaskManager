package test.java.com.cainfe.task_manager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.java.com.cainfe.task_manager.model.Task;
import main.java.com.cainfe.task_manager.service.TaskRepository;

class TaskRepositoryTest {
	private TaskRepository taskRepository;
	private String databaseName = "task_manager.db";
	private Exception thrown;

	@BeforeEach
	void setUp() throws Exception {
		taskRepository = new TaskRepository();
		taskRepository.connect();
	}

	@AfterEach
	void tearDown() throws Exception {
		this.deleteDatabase();
	}

	@Test
	void testConnect() throws Exception {
		assertTrue(new File(databaseName).exists());
	}

	@Test
	void testInsertGetTask() throws Exception {
		String title = "test task";
		Task taskWithoutId = new Task(title);
		Task taskWithId = new Task(title);
		taskWithId.setIdIfNotSet(3);
		taskRepository.insertTask(taskWithoutId);
		taskWithoutId.setIdIfNotSet(1);
		taskRepository.insertTask(taskWithId);
		assertEquals(taskWithoutId, taskRepository.getTask(1));
		assertEquals(taskWithId, taskRepository.getTask(3));
		assertEquals(null, taskRepository.getTask(101));

		this.deleteDatabase();
		taskRepository.connect();
		assertEquals(null, taskRepository.getTask(1));
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
			taskRepository.insertTask(task);
		}

		List<Task> returnedTasks = Arrays.asList(taskRepository.getAllTasks());

		assertEquals(3, returnedTasks.size());
		assertTrue(returnedTasks.containsAll(insertedTasks));

		this.deleteDatabase();
		taskRepository.connect();
		assertEquals(0, taskRepository.getAllTasks().length);
	}

	void deleteDatabase() throws Exception {
		taskRepository.close();
		new File(databaseName).delete();
	}

	@Test
	void testSelectTasks() throws Exception {
		Task task = new Task("Test task");
		taskRepository.insertTask(task);
		ResultSet results = taskRepository.selectTasks("*");
		assertEquals("Test task", results.getString("Title"));
		results.close();

		results = taskRepository.selectTasks("*", null);
		assertEquals("Test task", results.getString("Title"));
		results.close();

		results = taskRepository.selectTasks("*", " ");
		assertEquals("Test task", results.getString("Title"));
		results.close();

		Task task2 = new Task("another test task");
		task2.setIdIfNotSet(101);
		taskRepository.insertTask(task2);
		results = taskRepository.selectTasks("*", "id = 101");
		results.next();
		assertEquals("another test task", results.getString("Title"));
		assertFalse(results.next());

		thrown = assertThrows(IllegalArgumentException.class, () -> {
			taskRepository.selectTasks(null, "");
		});
		assertEquals("The properties parameter cannot be null or empty.", thrown.getMessage());

		thrown = assertThrows(IllegalArgumentException.class, () -> {
			taskRepository.selectTasks(" ", "");
		});
		assertEquals("The properties parameter cannot be null or empty.", thrown.getMessage());

		thrown = assertThrows(IllegalArgumentException.class, () -> {
			taskRepository.selectTasks(null);
		});
		assertEquals("The properties parameter cannot be null or empty.", thrown.getMessage());

		thrown = assertThrows(IllegalArgumentException.class, () -> {
			taskRepository.selectTasks(" ");
		});
		assertEquals("The properties parameter cannot be null or empty.", thrown.getMessage());

		taskRepository.close();
	}

	@Test
	void testDeleteTaskWithID() throws Exception {
		taskRepository.insertTask(new Task("task"));
		taskRepository.insertTask(new Task("task2"));
		taskRepository.deleteTaskWithID(1);
		assertNull(taskRepository.getTask(1));
		assertNotNull(taskRepository.getTask(2));
	}
}
