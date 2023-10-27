package com.cainfe.task_manager.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.cainfe.task_manager.model.Status;
import com.cainfe.task_manager.model.Task;

class TaskTest {
	Task task;

	@BeforeEach
	public void setUp() {
		task = new Task("The initial task");
	}

	@Test
	public void testSetGetID() throws Exception {
		task.setIdIfNotSet(1);
		assertEquals(1, task.getId());
		task.setIdIfNotSet(2);
		assertEquals(1, task.getId());
		Task task2 = new Task("task2");
		task2.setIdIfNotSet(2);
		assertEquals(2, task2.getId());
	}

	@Test
	public void testSetGetTitle() throws Exception {
		assertEquals("The initial task", task.getTitle());
		task.setTitle("The new task");
		assertEquals("The new task", task.getTitle());
	}

	@Test
	public void testSetGetStatus() throws Exception {
		assertEquals(Status.INCOMPLETE, task.getStatus());
		task.setStatus(Status.COMPLETE);
		assertEquals(Status.COMPLETE, task.getStatus());
	}

	@Test
	public void testGetDefaultStatus() throws Exception {
		assertEquals(Status.INCOMPLETE, Task.getDefaultStatus());
	}

	@Test
	public void testEqual() throws Exception {
		Task task1 = new Task("task1");
		assertNotEquals(task1, new Object());
		assertEquals(task1, new Task("task1"));
		assertNotEquals(task1, new Task("task2"));

		Task task2 = new Task("task1");
		task2.setIdIfNotSet(1);
		assertNotEquals(task1, task2);

		Task task3 = new Task("task1");
		task3.setStatus(Status.COMPLETE);
		assertNotEquals(task1, task3);
	}
	
	@Test
	public void testIsEquivalentTo() throws Exception {
		assertTrue(task.isEquivalentTo(new Task("The initial task")));
		assertFalse(task.isEquivalentTo(new Task("a different title")));
		
		Task taskWithDifferentStatus = new Task("The initial task");
		taskWithDifferentStatus.setStatus(Status.COMPLETE);
		assertFalse(task.isEquivalentTo(taskWithDifferentStatus));
		
		Task taskWithDifferentId = new Task("The initial task");
		taskWithDifferentId.setIdIfNotSet(7);
		assertTrue(task.isEquivalentTo(taskWithDifferentId));
	}
	
	@Test
	public void testToString() throws Exception {
		assertEquals("Task{id=0, title='The initial task', status='INCOMPLETE'}", task.toString());
		
		Task task2 = new Task("Do the laundry");
		task2.setIdIfNotSet(7);
		task2.setStatus(Status.COMPLETE);
		assertEquals("Task{id=7, title='Do the laundry', status='COMPLETE'}", task2.toString());
	}
}