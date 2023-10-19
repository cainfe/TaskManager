package com.cainfe.task_manager.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.swing.WindowConstants;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.cainfe.task_manager.ui.TaskManagerGUI;

class TaskManagerGUITest {
	TaskManagerGUI frame;

	@BeforeEach
	void setUp() throws Exception {
		frame = new TaskManagerGUI();
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testJFrameTitle() throws Exception {
		assertEquals("Task Manager", frame.getTitle());
	}

	@Test
	void testJFrameDefaultSize() throws Exception {
		assertEquals(300, frame.getWidth());
		assertEquals(450, frame.getHeight());
	}

	@Test
	void testJFrameCloseOperation() {
		assertEquals(WindowConstants.EXIT_ON_CLOSE, frame.getDefaultCloseOperation());
	}
}
