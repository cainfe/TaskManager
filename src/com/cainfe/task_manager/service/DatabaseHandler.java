package com.cainfe.task_manager.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.cainfe.task_manager.model.Status;
import com.cainfe.task_manager.model.Task;

public class DatabaseHandler {
	private Connection connection;
	private final String TABLE_TASKS = "tasks";
	private final String COLUMN_TASKS_TITLE = "Title";
	private final String COLUMN_TASKS_STATUS = "Status";
	private final String COLUMN_TASKS_ID = "Id";

	public void connect(String url) throws SQLException {
		connection = DriverManager.getConnection("jdbc:sqlite:" + url);
	}

	public void insertTask(Task task) throws SQLException {
		Task[] tasks = { task };
		this.insertTasks(tasks);
	}

	public void insertTasks(Task[] tasks) throws SQLException {
		this.ensureTasksTableExists();

		String statement = "INSERT INTO " + TABLE_TASKS;
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

		statement = "INSERT INTO " + TABLE_TASKS;
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
		Task retrievedTask = null;

		ResultSet resultSet = this.selectTasks(COLUMN_TASKS_ID + ", " + COLUMN_TASKS_TITLE + ", " + COLUMN_TASKS_STATUS,
				COLUMN_TASKS_ID + " = " + id);

		if (resultSet.next()) {
			retrievedTask = new Task(resultSet.getString(COLUMN_TASKS_TITLE));
			retrievedTask.setStatus(Status.valueOf(resultSet.getString(COLUMN_TASKS_STATUS)));
			retrievedTask.setIdIfNotSet(Integer.parseInt(resultSet.getString(COLUMN_TASKS_ID)));
		}
		resultSet.close();

		return retrievedTask;
	}

	public Task[] getAllTasks() throws SQLException {
		Task[] retrievedTasks;

		ResultSet resultSet = this.selectTasks("*");

		List<Task> tasks = new ArrayList<>();
		while (resultSet.next()) {
			Task task = new Task(resultSet.getString(COLUMN_TASKS_TITLE));
			task.setStatus(Status.valueOf(resultSet.getString(COLUMN_TASKS_STATUS)));
			task.setIdIfNotSet(Integer.parseInt(resultSet.getString(COLUMN_TASKS_ID)));
			tasks.add(task);
		}
		resultSet.close();

		retrievedTasks = tasks.toArray(new Task[tasks.size()]);
		return retrievedTasks;
	}

	public ResultSet selectTasks(String properties, String conditions) throws SQLException {
		this.ensureTasksTableExists();
		if (properties == null || properties.isBlank()) {
			throw new IllegalArgumentException("The properties parameter cannot be null or empty.");
		}

		String statement = "SELECT " + properties + " FROM " + TABLE_TASKS;
		if (!(conditions == null || conditions.isBlank())) {
			statement += " WHERE " + conditions;
		}
		statement += ";";

		PreparedStatement preparedStatement = connection.prepareStatement(statement);
		return preparedStatement.executeQuery();
	}

	public ResultSet selectTasks(String properties) throws SQLException {
		return this.selectTasks(properties, null);
	}

	public void deleteTaskWithID(int taskID) throws SQLException {
		this.ensureTasksTableExists();
		String statement = "DELETE FROM " + TABLE_TASKS 
				+ " WHERE " + COLUMN_TASKS_ID + " = " + taskID;
		
		PreparedStatement preparedStatement = connection.prepareStatement(statement);
		preparedStatement.execute();
	}
	
	private void ensureTasksTableExists() throws SQLException {
		Statement statement = connection.createStatement();
		statement.executeUpdate("CREATE TABLE IF NOT EXISTS " + TABLE_TASKS + " (" + COLUMN_TASKS_ID
				+ " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_TASKS_TITLE + " TEXT, " + COLUMN_TASKS_STATUS
				+ " TEXT NOT NULL DEFAULT " + Task.getDefaultStatus() + ");");
		statement.close();
	}

	public void close() throws SQLException {
		connection.close();
	}
}
