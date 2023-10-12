package com.cainfe.task_manager.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.cainfe.task_manager.Task;
import com.cainfe.task_manager.Status;

class TaskTest {
	Task task;
	
	@BeforeEach
	public void setUp() {
		task = new Task("The initial task");
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
		assertNotEquals(task, new Object());

		Task task1 = new Task("task1");
		Task task2 = new Task("task2");
		assertNotEquals(task1,task2);
		
		Task task3 = new Task("task");
		Task task4 = new Task("task");
		assertEquals(task3, task4);
		
		Task task5 = new Task("task");
		Task task6 = new Task("task");
		task6.setStatus(Status.COMPLETE);
		assertNotEquals(task5,task6);
		
		Task task7 = new Task("task7");
		Task task8 = new Task("task8");
		task8.setStatus(Status.COMPLETE);
		assertNotEquals(task7,task8);
	}
}