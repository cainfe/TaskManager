package com.cainfe.task_manager;

import java.awt.EventQueue;

import com.cainfe.task_manager.ui.TaskManagerGUI;

public class App {

	public static void main(String[] args) {
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
