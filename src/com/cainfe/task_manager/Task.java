package com.cainfe.task_manager;

public class Task {
	private String title;
	private Status status;
	private int id;

	/**
	 *
	 * @param title
	 */
	public Task(String title) {
		this.setTitle(title);
		this.setStatus(Status.INCOMPLETE);
	}

	public void setIdIfNotSet(int id) {
		if (this.id == 0) this.id = id;
	}

	public int getId() {
		return id;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Status getStatus() {
		return this.status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public static Status getDefaultStatus() {
		return Status.INCOMPLETE;
	}

	@Override
	public boolean equals(Object anObject) {
		if (!(anObject instanceof Task)) return false;
		else {
			Task otherTask = (Task) anObject;
			return this.getId() == otherTask.getId() 
					&& this.getTitle().equals(otherTask.getTitle())
					&& this.getStatus() == otherTask.getStatus();
		}
	}
}
