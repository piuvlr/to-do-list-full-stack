package br.com.caio.todo.tasks.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.caio.todo.tasks.vo.TasksVO;

@Service
public interface TaskService {
	
	public List<TasksVO> loadTasks(Integer userId);

	public TasksVO createTasks(TasksVO tasksVO);

}
