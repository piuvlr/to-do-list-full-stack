package br.com.caio.todo.tasks.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.caio.todo.tasks.bo.TaskBO;
import br.com.caio.todo.tasks.bo.UserBO;
import br.com.caio.todo.tasks.model.Tasks;
import br.com.caio.todo.tasks.model.User;
import br.com.caio.todo.tasks.status.StatusTaskEnum;
import br.com.caio.todo.tasks.utils.ParseUtils;
import br.com.caio.todo.tasks.utils.TaskUtils;
import br.com.caio.todo.tasks.vo.TasksVO;

@Service
public class TaskServiceImpl implements TaskService {
	
	@Autowired
	TaskBO taskBO;
	
	@Autowired
	UserBO userBO;
	
	@Override
	public List<Tasks> getAllTasks() {
		List<Tasks> result = taskBO.loadAllTasks();
		return result;
	}
	
	@Override
	public List<TasksVO> loadTasks() {
		List<Tasks> result = taskBO.loadTasks(TaskUtils.getUserID());
		
		return ParseUtils.parse(result);
	}

	@Override
	public TasksVO createTasks(TasksVO tasksVO) {
		User user = userBO.findUserById(TaskUtils.getUserID());
		Date today = new Date();
		
		if (tasksVO.getDeadlineDate() != null) {
			tasksVO.setDeadlineDate(TaskUtils.getEndTimeByDate(tasksVO.getDeadlineDate()));
		}
		if (today.after(tasksVO.getDeadlineDate())) {
			tasksVO.setStatusTask(StatusTaskEnum.DEADLINE);
		} else {
			tasksVO.setStatusTask(StatusTaskEnum.PROGRESS);
		}
		
		Tasks tasks = ParseUtils.parseToEntity(tasksVO, user);
		taskBO.createTask(tasks);
		
		TasksVO tasksCreateVO = ParseUtils.parseTask(tasks);
		
		return tasksCreateVO;
	}

	@Override
	public List<TasksVO> loadTasksPeriod(Date iniDate, Date endDate) {
		Integer userId = TaskUtils.getUserID();
		
		List<Tasks> result = taskBO.loadTasksPeriod(iniDate, endDate, userId);
		return ParseUtils.parse(result);
	}

	@Override
	public boolean deleteTask(Integer id) {
		boolean result = taskBO.deleteTask(id);
		
		return result;
	}

	@Override
	public TasksVO concluidTask(Integer id) {
		Tasks task = taskBO.findTaskById(id);
		
		task.setStatusTask(StatusTaskEnum.COMPLETED);
		
		TasksVO tasksVO = new TasksVO(task.getId(), task.getNameTask(), task.getDescription(), task.getStatusTask(), 
				task.getCreationDate(), task.getCompletedDate(), task.getDeadlineDate(), task.getUser().getUsername());
		
		return tasksVO;
	}
}
