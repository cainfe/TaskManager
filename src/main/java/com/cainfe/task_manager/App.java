package main.java.com.cainfe.task_manager;

import java.awt.EventQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import main.java.com.cainfe.task_manager.ui.TaskManagerGUI;

public class App {
	private static final Logger LOGGER = LoggerFactory.getLogger(App.class);
	
	public static void main(String[] args) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("App started");
		}
		
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					TaskManagerGUI frame = new TaskManagerGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
