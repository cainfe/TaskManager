package main.java.com.cainfe.task_manager.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import main.java.com.cainfe.task_manager.model.Status;
import main.java.com.cainfe.task_manager.model.Task;

public class TaskRepository {
	private Connection connection;
	private final String TABLE_TASKS = "task";
	private final String COL_TITLE = "Title";
	private final String COL_STATUS = "Status";
	private final String COL_ID = "Id";

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
		statement += COL_ID + ", ";
		statement += COL_TITLE + ", ";
		statement += COL_STATUS;
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
		statement += COL_TITLE + ", ";
		statement += COL_STATUS;
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

		ResultSet resultSet = this.selectTasks(COL_ID + ", " + COL_TITLE + ", " + COL_STATUS,
				COL_ID + " = " + id);

		if (resultSet.next()) {
			retrievedTask = new Task(resultSet.getString(COL_TITLE));
			retrievedTask.setStatus(Status.valueOf(resultSet.getString(COL_STATUS)));
			retrievedTask.setIdIfNotSet(Integer.parseInt(resultSet.getString(COL_ID)));
		}
		resultSet.close();

		return retrievedTask;
	}

	public Task[] getAllTasks() throws SQLException {
		Task[] retrievedTasks;

		ResultSet resultSet = this.selectTasks("*");

		List<Task> tasks = new ArrayList<>();
		while (resultSet.next()) {
			Task task = new Task(resultSet.getString(COL_TITLE));
			task.setStatus(Status.valueOf(resultSet.getString(COL_STATUS)));
			task.setIdIfNotSet(Integer.parseInt(resultSet.getString(COL_ID)));
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
				+ " WHERE " + COL_ID + " = " + taskID;
		
		PreparedStatement preparedStatement = connection.prepareStatement(statement);
		preparedStatement.execute();
	}
	
	private void ensureTasksTableExists() throws SQLException {
		Statement statement = connection.createStatement();
		statement.executeUpdate("CREATE TABLE IF NOT EXISTS " + TABLE_TASKS + " (" + COL_ID
				+ " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_TITLE + " TEXT, " + COL_STATUS
				+ " TEXT NOT NULL DEFAULT " + Task.getDefaultStatus() + ");");
		statement.close();
	}

	public void close() throws SQLException {
		connection.close();
	}
}
