package main.java.com.cainfe.task_manager.ui;

import java.awt.Point;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.WindowConstants;

import main.java.com.cainfe.task_manager.service.ExitApplication;

public class TaskManagerGUI extends JFrame {
	private ExitApplication exitApplication;
	private static final String WINDOW_TITLE = "Task Manager";

	public TaskManagerGUI() {
		this.setupWindow();
	}
	
	private void setupWindow() {
		setTitle(WINDOW_TITLE);
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 300, 450);

		this.setupExitApplication();
		this.addMenuBar();
		
		JScrollPane scrollPane = new JScrollPane(new TaskPanel());
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		scrollPane.getViewport().setViewPosition(new Point(0, 0));
		setContentPane(scrollPane);
		
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

	private void setupExitApplication() {
		exitApplication = new ExitApplication();
		exitApplication.setParentFrame(this);
	}
}
