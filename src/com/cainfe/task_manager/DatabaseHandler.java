package com.cainfe.task_manager;

import java.sql.*;
import java.util.ArrayList;

public class DatabaseHandler {
	private Connection connection;
	private final String TABLE_TASKS = "tasks";
	private final String COLUMN_TASKS_TITLE = "Title";
	private final String COLUMN_TASKS_STATUS = "Status";
	
	public DatabaseHandler() throws SQLException {
	}
	
	public void connect(String url) throws SQLException {
		connection = DriverManager.getConnection("jdbc:sqlite:" + url);
	}
	
	public void insertTask(Task task) throws SQLException {
		Task[] tasks = new Task[1];
		tasks[0] = task;
		this.insertTasks(tasks);
	}
	
	public void insertTasks(Task[] tasks) throws SQLException {
		this.createTasksTable();
		
		String statement =  "INSERT INTO " + TABLE_TASKS + " ("
				+ COLUMN_TASKS_TITLE + ", " 
				+ COLUMN_TASKS_STATUS
				+ ") VALUES (?, ?);";
		PreparedStatement preparedStatement = connection.prepareStatement(statement);
		for (int i = 0; i < tasks.length; i++) {
			preparedStatement.setString(1, tasks[i].getTitle());
			preparedStatement.setString(2, tasks[i].getStatus().toString());
			preparedStatement.addBatch();
		}
		preparedStatement.executeBatch();
	}
	
	public Task getTask(String title) throws SQLException {
		String[] titles = new String[1];
		titles[0] = title;
		return this.getTasks(titles)[0];
	}
	
	public Task[] getTasks(String[] titles) throws SQLException {
		ArrayList<Task> tasks = new ArrayList<>();
		
		String statement = "SELECT " + COLUMN_TASKS_TITLE + ", " + COLUMN_TASKS_STATUS + " FROM " + TABLE_TASKS + " WHERE " + COLUMN_TASKS_TITLE + " = ?;";
		PreparedStatement preparedStatement = connection.prepareStatement(statement);
		for (int i = 0; i < titles.length; i++) {
			preparedStatement.setString(1, titles[i]);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			Task task = new Task(resultSet.getString(COLUMN_TASKS_TITLE));
			task.setStatus(Status.valueOf(resultSet.getString(COLUMN_TASKS_STATUS)));
			tasks.add(task);
		}
		
		return (Task[]) tasks.toArray();
	}
	
	private void createTasksTable() throws SQLException {
		Statement statement = connection.createStatement();
		statement.executeUpdate("CREATE TABLE IF NOT EXISTS " + TABLE_TASKS + " ("
				+ COLUMN_TASKS_TITLE + " TEXT PRIMARY KEY,"
				+ COLUMN_TASKS_STATUS + "Status TEXT NOT NULL DEFAULT " + Task.getDefaultStatus()
				+ ") WITHOUT ROWID;");
		statement.close();
	}
}
