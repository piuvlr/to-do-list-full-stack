package br.com.caio.todo.tasks.utils;

import java.util.List;
import java.util.ArrayList;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.caio.todo.tasks.vo.TasksVO;
import br.com.caio.todo.tasks.vo.UserVO;
import br.com.caio.todo.tasks.model.User;
import br.com.caio.todo.tasks.model.Tasks;

public class ParseUtils {
	// id - nametask - description - statustask - creationdate - completedDate - deadlineDate - userName
	
	public static TasksVO parseTask(Tasks task) {
		TasksVO taskVO = new TasksVO(task.getId(), task.getNameTask(), task.getDescription(), task.getStatusTask(), task.getCreationDate(), task.getCompletedDate(), task.getDeadlineDate(), task.getUserName());
		
		return taskVO;
	}
	
	
	public static UserVO parse(User user) {
		UserVO userVO = new UserVO(user.getId(), user.getUsername(), user.getCreationDate());
		
		return userVO;
	}
	
	/**
	 * Transforma o User em uma entidade.
	 * 
	 * @param userVO Recebe como parametro o Objeto userVO
	 * @return retorna o proprio user , porem, agora e uma entidade
	 */
	public static User parseToEntity(UserVO userVO) {
		User user = new User();
		user.setUserName(userVO.getUserName());
		user.setPassword(new BCryptPasswordEncoder().encode(userVO.getPassword()));
		
		return user;
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
	
	/**
	 * 
	 * Transforma a Tarefa em uma entidade.
	 * 
	 * @param tasksVO  Recebe como parametro o Objeto  tasksVO
	 * @param user	Recebe como parametro o Objeto user
	 * @return retorna a propria tarefa , porem, agora e uma entidade
	 */
	public static Tasks parseToEntity(TasksVO tasksVO, User user) {
		Tasks tasks = new Tasks();
		tasks.setNameTask(tasksVO.getNameTask());
		tasks.setDescription(tasksVO.getDescription());
		tasks.setDeadlineDate(tasksVO.getDeadlineDate());
		tasks.setUser(user);
		
		return tasks;
	}
	
}