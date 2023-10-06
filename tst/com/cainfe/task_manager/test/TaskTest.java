package com.cainfe.task_manager.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.cainfe.task_manager.Task;
import com.cainfe.task_manager.TaskStatus;

class TaskTest {
	Task task;
	
	@BeforeEach
	public void setUp() {
		task = new Task("The initial task");
	} // End setUp
	
	@Test
	public void testSetGetTitle() {
		assertEquals("The initial task", task.getTitle());
		task.setTitle("The new task");
		assertEquals("The new task", task.getTitle());
	} // End testSetGetTitle
	
	@Test
	public void testSetGetStatus() {
		assertEquals(TaskStatus.INCOMPLETE, task.getStatus());
		task.setStatus(TaskStatus.COMPLETE);
		assertEquals(TaskStatus.COMPLETE, task.getStatus());
	} // End testSetGetStatus
} // End class