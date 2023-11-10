package com.cainfe.task_manager.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.swing.Action;
import javax.swing.JFrame;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.java.com.cainfe.task_manager.service.ExitApplication;

class ExitApplicationTest {
	private ExitApplication action;

	@BeforeEach
	void setUp() throws Exception {
		action = new ExitApplication();
	}

	@Test
	void testName() throws Exception {
		assertEquals("Exit", action.getValue(Action.NAME));
	}

	@Test
	void testDescription() throws Exception {
		assertEquals("Exit the application", action.getValue(Action.SHORT_DESCRIPTION));
	}

	@Test
	void testActionPerformed() throws Exception {
		// TODO: Test exit action JOptionPane optionPane = mock(JOptionPane.class);
	}

	@Test
	void testGetSetParentFrame() throws Exception {
		assertEquals(null, action.getParentFrame());
		JFrame testFrame = new JFrame();
		action.setParentFrame(testFrame);
		assertEquals(testFrame, action.getParentFrame());
	}
}
