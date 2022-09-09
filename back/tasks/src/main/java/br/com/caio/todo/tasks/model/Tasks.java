package br.com.caio.todo.tasks.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import br.com.caio.todo.tasks.status.StatusTaskEnum;

@Entity
@Table(name = "TASKS")
@NamedQueries({
	@NamedQuery(name = Tasks.FIND_TASKS_BY_USER_ID, query = "select t from Tasks t where t.user.id = :userId")
})
public class Tasks {

	public static final String FIND_TASKS_BY_USER_ID = "Tasks.FIND_TASKS_BY_USER_ID";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "USER_ID", nullable = false)
	private User user;

	@Column(name = "NAME_TASK", nullable = true, length = 80)
	private String nameTask;

	@Column(name = "DESCRIPTION_TASK", length = 250)
	private String description;

	@Column(name = "STATUS_TASK")
	private StatusTaskEnum statusTask = StatusTaskEnum.PROGRESS;

	@Column(name = "CREATION_DATE", nullable = true)
	private Date creationDate = new Date();

	@Column(name = "COMPLETED_DATE")
	private Date completedDate;

	@Column(name = "DEADLINE_DATE")
	private Date deadlineDate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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
