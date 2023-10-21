package com.cainfe.task_manager.ui;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import com.cainfe.task_manager.service.ExitApplication;

public class TaskManagerGUI extends JFrame {
	private ExitApplication exitApplication;
	private JPanel contentPane;

	public TaskManagerGUI() {
		setTitle("Task Manager");
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 300, 450);
		this.setExitApplication();

		this.addMenuBar();

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				exitApplication.actionPerformed(null);
			}
		});
	}

	private void addMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu fileMenu = new JMenu("File");
		menuBar.add(fileMenu);

		JMenuItem exitMenuItem = new JMenuItem(exitApplication);
		fileMenu.add(exitMenuItem);
	}

	private void setExitApplication() {
		exitApplication = new ExitApplication();
		exitApplication.setParentFrame(this);
	}
}
