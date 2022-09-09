package br.com.caio.todo.tasks.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.caio.todo.tasks.bo.TaskBO;
import br.com.caio.todo.tasks.bo.UserBO;
import br.com.caio.todo.tasks.model.Tasks;
import br.com.caio.todo.tasks.model.User;
import br.com.caio.todo.tasks.vo.TasksVO;
import br.com.caio.todo.tasks.vo.UserVO;

@Service
public class TaskServiceImpl implements TaskService {
	
	@Autowired
	TaskBO taskBO;
	
	@Autowired
	UserBO userBO;

	@Override
	public List<TasksVO> loadTasks(Integer userId) {
		List<Tasks> result = taskBO.loadTasks(userId);
		
		return TasksVO.parse(result);
	}

	@Override
	public TasksVO createTasks(TasksVO tasksVO) {
		User user = userBO.findUserByUserName(tasksVO.getUserName());
		
		Tasks tasks = TasksVO.parseToEntity(tasksVO, user);
		taskBO.createTask(tasks);
		
		List<TasksVO> tasksCreateVO = TasksVO.parse(Arrays.asList(tasks));
		
		return tasksCreateVO.get(0);
	}

}
