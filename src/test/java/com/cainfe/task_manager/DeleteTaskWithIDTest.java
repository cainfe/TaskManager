package test.java.com.cainfe.task_manager;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.io.File;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.java.com.cainfe.task_manager.model.Task;
import main.java.com.cainfe.task_manager.service.TaskRepository;
import main.java.com.cainfe.task_manager.service.DeleteTaskWithID;

class DeleteTaskWithIDTest {

	@BeforeEach
	void setUp() throws Exception {
		
	}

	@Test
	void testActionPerformed() throws Exception {
		TaskRepository dbhandler = new TaskRepository();
		dbhandler.connect();
		Task task = new Task("test task");
		task.setIdIfNotSet(1);
		Task task2 = new Task("test task");
		task.setIdIfNotSet(2);
		DeleteTaskWithID deleteTaskAction = new DeleteTaskWithID(dbhandler, task.getId());
		dbhandler.insertTask(task);
		dbhandler.insertTask(task2);
		assertNotNull(dbhandler.getTask(1));
		deleteTaskAction.actionPerformed(null);
		assertNull(dbhandler.getTask(1));
		new File("task_manager.db").delete();
		
		dbhandler.close();
	}

}
