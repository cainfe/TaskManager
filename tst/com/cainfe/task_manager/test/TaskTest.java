package com.cainfe.task_manager.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.cainfe.task_manager.Task;

class TaskTest {
	Task task;
	
	@BeforeEach
	public void setUp() {
		task = new Task("The initial task");
	} // End setUp
} // End class