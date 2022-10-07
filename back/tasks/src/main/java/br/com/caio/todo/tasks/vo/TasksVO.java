package br.com.caio.todo.tasks.vo;

import java.util.Date;

import javax.validation.constraints.NotNull;

import br.com.caio.todo.tasks.status.StatusTaskEnum;

public class TasksVO {

	private Integer id;
	
	@NotNull
	private String nameTask;
	
	@NotNull
	private String description;
	private StatusTaskEnum statusTask;
	private Date creationDate;
	private Date completedDate;
	private Date deadlineDate;
	private String userName;
	
	public TasksVO(Integer id, String nameTask, String description, StatusTaskEnum statusTask, Date creationDate,
			Date completedDate, Date deadlineDate, String userName) {
		this.id = id;
		this.nameTask = nameTask;
		this.description = description;
		this.statusTask = statusTask;
		this.creationDate = creationDate;
		this.completedDate = completedDate;
		this.deadlineDate = deadlineDate;
		this.userName = userName;
	}

	public Integer getId() {
		return id;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getNameTask() {
		return nameTask;
	}

	public void setNameTask(String nameTask) {
		this.nameTask = nameTask;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public StatusTaskEnum getStatusTask() {
		return statusTask;
	}

	public void setStatusTask(StatusTaskEnum statusTask) {
		this.statusTask = statusTask;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date getCompletedDate() {
		return completedDate;
	}

	public void setCompletedDate(Date completedDate) {
		this.completedDate = completedDate;
	}

	public Date getDeadlineDate() {
		return deadlineDate;
	}

	public void setDeadlineDate(Date deadlineDate) {
		this.deadlineDate = deadlineDate;
	}
	
}
