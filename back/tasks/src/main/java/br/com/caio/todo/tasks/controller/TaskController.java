package br.com.caio.todo.tasks.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.caio.todo.tasks.service.TaskService;
import br.com.caio.todo.tasks.vo.TasksVO;

@RestController
@RequestMapping("/tasks")
public class TaskController {
	
	@Autowired
	private TaskService taskService;
	
	@RequestMapping(value = "/load-tasks")
	public ResponseEntity<List<TasksVO>> loadTasks(HttpServletRequest httpServletRequest,
			@RequestParam(required = true, value = "user-id") Integer userId) {
		
		List<TasksVO> result = taskService.loadTasks(userId);
		return new ResponseEntity<List<TasksVO>>(result, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ResponseEntity<TasksVO> loadTasks(@RequestBody TasksVO tasksVO) {
		
		TasksVO result = taskService.createTasks(tasksVO);
		return new ResponseEntity<TasksVO>(result, HttpStatus.CREATED);
	}
	

}
