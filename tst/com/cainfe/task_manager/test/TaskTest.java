package com.cainfe.task_manager.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.cainfe.task_manager.Status;
import com.cainfe.task_manager.Task;

class TaskTest {
	Task task;

	@BeforeEach
	public void setUp() {
		task = new Task("The initial task");
	}

	@Test
	public void testSetGetID() {
		task.setIdIfNotSet(1);
		assertEquals(1,task.getId());
		task.setIdIfNotSet(2);
		assertEquals(1,task.getId());
		Task task2 = new Task("task2");
		task2.setIdIfNotSet(2);
		assertEquals(2, task2.getId());
	}

	@Test
	public void testSetGetTitle() {
		assertEquals("The initial task", task.getTitle());
		task.setTitle("The new task");
		assertEquals("The new task", task.getTitle());
	}

	@Test
	public void testSetGetStatus() {
		assertEquals(Status.INCOMPLETE, task.getStatus());
		task.setStatus(Status.COMPLETE);
		assertEquals(Status.COMPLETE, task.getStatus());
	}

	@Test
	public void testGetDefaultStatus() {
		assertEquals(Status.INCOMPLETE, Task.getDefaultStatus());
	}

	@Test
	public void testEqual() {
		Task task1 = new Task("test1");
		task1.setIdIfNotSet(1);
		assertNotEquals(new Object(), task1);
		
		Task task2 = new Task("test2");
		task2.setIdIfNotSet(2);
		assertNotEquals(task1, task2);
		
		Task task3 = new Task("task3");
		task3.setIdIfNotSet(1);
		assertEquals(task1, task3);
		
	}
}