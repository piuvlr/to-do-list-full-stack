package br.com.caio.todo.tasks.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.caio.todo.tasks.service.TaskService;
import br.com.caio.todo.tasks.utils.TaskUtils;
import br.com.caio.todo.tasks.vo.TasksVO;

@RestController
@RequestMapping("/tasks")
public class TaskController {
	
	@Autowired
	private TaskService taskService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<TasksVO>> loadTasks(HttpServletRequest httpServletRequest) {
		List<TasksVO> result = taskService.loadTasks();
		
		return new ResponseEntity<List<TasksVO>>(result, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<TasksVO> createTask(@RequestBody @Valid TasksVO tasksVO) {
		
		TasksVO result = taskService.createTasks(tasksVO);
		return new ResponseEntity<TasksVO>(result, HttpStatus.CREATED);
	}
	
	@RequestMapping(method = RequestMethod.DELETE)
	public ResponseEntity<Boolean> deleteTask(
			@RequestParam(required = true, value = "id") Integer id) {
		
		boolean result = taskService.deleteTask(id);
		return new ResponseEntity<Boolean>(result, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/load-tasks-period", method = RequestMethod.GET)
	public ResponseEntity<List<TasksVO>> loadTasksPeriod(
			@RequestParam(value = "ini", required = true) String dateInit,
			@RequestParam(value = "end", required = true) String dateEnd) {
		
		Date iniDate = new Date();
		Date endDate = new Date();
		
		try {
			SimpleDateFormat formatterDate = new SimpleDateFormat("yyyy-MM-dd");
		    iniDate = formatterDate.parse(dateInit);
		    endDate = formatterDate.parse(dateEnd);
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
		
		List<TasksVO> result = taskService.loadTasksPeriod(TaskUtils.getInitialTimeByDate(iniDate), TaskUtils.getEndTimeByDate(endDate));
		return new ResponseEntity<List<TasksVO>>(result, HttpStatus.OK);
	}
	
	@Transactional
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<TasksVO> concluedTask(@RequestBody int idTask) {
		
		TasksVO result = taskService.concluidTask(idTask);
		
		return new ResponseEntity<TasksVO>(result, HttpStatus.OK);
	}
	
	@RequestMapping(value = "edit", method = RequestMethod.POST)
	public ResponseEntity<Boolean> editTask(@RequestBody TasksVO tasksVO) {
		
		this.taskService.editTask(tasksVO);
		
		return new ResponseEntity<Boolean>(Boolean.TRUE, HttpStatus.OK);
	}
}
