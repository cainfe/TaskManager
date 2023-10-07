package com.cainfe.task_manager;

public class Task {
	private String title;
	private TaskStatus status;
	
	/**
	 * 
	 * @param title
	 */
	public Task(String title) {
		this.setTitle(title);
		this.setStatus(TaskStatus.INCOMPLETE);
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public TaskStatus getStatus() {
		return this.status;
	}
	
	public void setStatus(TaskStatus status) {
		this.status = status;
	}
}
