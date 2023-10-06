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
	} // End constructor
	
	public String getTitle() {
		return this.title;
	} // end getTitle
	
	public void setTitle(String title) {
		this.title = title;
	} // End setTitle
	
	public TaskStatus getStatus() {
		return this.status;
	} // End getStatus
	
	public void setStatus(TaskStatus status) {
		this.status = status;
	} // End setStatus
} // End class
