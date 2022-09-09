package br.com.caio.todo.tasks.vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.caio.todo.tasks.model.Tasks;
import br.com.caio.todo.tasks.model.User;
import br.com.caio.todo.tasks.status.StatusTaskEnum;

public class TasksVO {

	private Integer id;
	private String nameTask;
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

	public static List<TasksVO> parse(List<Tasks> taskList) {
		List<TasksVO> result = new ArrayList<>();
		for (Tasks task : taskList) {
			TasksVO tasksVO = new TasksVO(task.getId(), 
					task.getNameTask(), task.getDescription(), task.getStatusTask(), task.getCreationDate(), task.getCompletedDate(), 
					task.getDeadlineDate(), task.getUser().getUserName());
			result.add(tasksVO);
		}
		
		return result;
	}
	
	public static Tasks parseToEntity(TasksVO tasksVO, User user) {
		Tasks tasks = new Tasks();
		tasks.setNameTask(tasksVO.getNameTask());
		tasks.setDescription(tasksVO.getDescription());
		tasks.setDeadlineDate(tasksVO.getDeadlineDate());
		tasks.setUser(user);
		
		return tasks;
	}

	public Integer getId() {
		return id;
	}

	public String getUserName() {
		return userName;
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
