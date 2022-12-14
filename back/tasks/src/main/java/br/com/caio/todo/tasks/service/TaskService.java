package br.com.caio.todo.tasks.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.caio.todo.tasks.model.Tasks;
import br.com.caio.todo.tasks.status.StatusTaskEnum;
import br.com.caio.todo.tasks.vo.TasksVO;

@Service
public interface TaskService {
	
	public List<Tasks> getAllTasks();
	
	public List<TasksVO> loadTasks();

	public TasksVO createTasks(TasksVO tasksVO);

	public List<TasksVO> loadTasksPeriod(Date iniDate, Date endDate);

	public boolean deleteTask(Integer id);

	public TasksVO concluidTask(Integer id);
	
	public List<Tasks> loadTasksPeriodAndStatus(Date iniDate, Date endDate, StatusTaskEnum statusTaskEnum);

	public void editTask(TasksVO tasksVO);
}
