package com.cainfe.task_manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DatabaseHandler {
	private Connection connection;
	private final String TABLE_TASKS = "tasks";
	private final String COLUMN_TASKS_TITLE = "Title";
	private final String COLUMN_TASKS_STATUS = "Status";
	private final String COLUMN_TASKS_ID = "Id";

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

		String statement =  "INSERT INTO " + TABLE_TASKS;
		statement += " (";
		statement += COLUMN_TASKS_ID + ", ";
		statement += COLUMN_TASKS_TITLE + ", ";
		statement += COLUMN_TASKS_STATUS;
		statement += ") VALUES (?, ?, ?);";
		PreparedStatement preparedStatement = connection.prepareStatement(statement);
		for (Task task : tasks) {
			if (task.getId() != 0) {
				preparedStatement.setString(1, task.getId() + "");
				preparedStatement.setString(2, task.getTitle());
				preparedStatement.setString(3, task.getStatus().toString());
				preparedStatement.addBatch();
			}
		}
		preparedStatement.executeBatch();
		
		statement =  "INSERT INTO " + TABLE_TASKS;
		statement += " (";
		statement += COLUMN_TASKS_TITLE + ", ";
		statement += COLUMN_TASKS_STATUS;
		statement += ") VALUES (?, ?);";
		preparedStatement = connection.prepareStatement(statement);
		for (Task task : tasks) {
			if (task.getId() == 0) {
				preparedStatement.setString(1, task.getTitle());
				preparedStatement.setString(2, task.getStatus().toString());
				preparedStatement.addBatch();
			}
		}
		preparedStatement.executeBatch();
	}

	public Task getTask(int id) throws SQLException {
		String statement = "SELECT " 
				+ COLUMN_TASKS_ID + ", " 
				+ COLUMN_TASKS_TITLE + ", " 
				+ COLUMN_TASKS_STATUS 
				+ " FROM " + TABLE_TASKS 
				+ " WHERE " + COLUMN_TASKS_ID + " = ?;";
		PreparedStatement preparedStatement = connection.prepareStatement(statement);
			
		preparedStatement.setString(1, id + "");

		ResultSet resultSet = preparedStatement.executeQuery();
		Task task = new Task(resultSet.getString(COLUMN_TASKS_TITLE));
		task.setStatus(Status.valueOf(resultSet.getString(COLUMN_TASKS_STATUS)));
		task.setIdIfNotSet(Integer.parseInt(resultSet.getString(COLUMN_TASKS_ID)));

		return task;
	}

	private void createTasksTable() throws SQLException {
		Statement statement = connection.createStatement();
		statement.executeUpdate("CREATE TABLE IF NOT EXISTS " + TABLE_TASKS + " ("
				+ COLUMN_TASKS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ COLUMN_TASKS_TITLE + " TEXT, "
				+ COLUMN_TASKS_STATUS + " TEXT NOT NULL DEFAULT " + Task.getDefaultStatus()
				+ ");");
		statement.close();
	}
}
