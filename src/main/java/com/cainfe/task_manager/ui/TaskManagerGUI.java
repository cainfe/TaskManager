package main.java.com.cainfe.task_manager.ui;

import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import main.java.com.cainfe.task_manager.service.ExitApplication;

public class TaskManagerGUI extends JFrame {
	private ExitApplication exitApplication;
	private JPanel taskListPanel;

	public TaskManagerGUI() {
		GraphicsEnvironment graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice defaultScreenDevice = graphicsEnvironment.getDefaultScreenDevice();
		GraphicsConfiguration graphicsConfiguration = defaultScreenDevice.getDefaultConfiguration();
		Rectangle screenSize = graphicsConfiguration.getBounds();

		setTitle("Task Manager");
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		setSize(300, 450);
		setLocation(screenSize.width - 50 - this.getWidth(), 100);
		this.setupExitApplication();
		this.addMenuBar();
		taskListPanel = new JPanel();
		setContentPane(taskListPanel);

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
