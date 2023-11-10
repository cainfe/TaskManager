package test.java.com.cainfe.task_manager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.WindowConstants;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.java.com.cainfe.task_manager.ui.TaskManagerGUI;

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
		assertEquals(WindowConstants.DO_NOTHING_ON_CLOSE, frame.getDefaultCloseOperation());
	}

	@Test
	void testJFrameJMenuBar() {
		JMenuBar menuBar = frame.getJMenuBar();
		assertNotNull(menuBar);

		JMenu fileMenu = menuBar.getMenu(0);
		assertEquals("File", fileMenu.getText());

		JMenuItem exitMenuItem = fileMenu.getItem(0);
		assertEquals("Exit", exitMenuItem.getText());
	}
}
