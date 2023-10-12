package com.cainfe.task_manager;

public class Task {
	private String title;
	private Status status;

	/**
	 *
	 * @param title
	 */
	public Task(String title) {
		this.setTitle(title);
		this.setStatus(Status.INCOMPLETE);
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
		System.out.println("here");
		if (anObject instanceof Task) {
			Task otherTask = (Task) anObject;
			return ((this.title.equals(otherTask.title)) &&
					(this.status == otherTask.status));
		} else {
			return false;
		}
	}
}
