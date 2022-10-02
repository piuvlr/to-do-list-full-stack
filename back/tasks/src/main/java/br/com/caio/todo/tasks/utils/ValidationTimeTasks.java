package br.com.caio.todo.tasks.utils;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.caio.todo.tasks.model.Tasks;
import br.com.caio.todo.tasks.service.TaskService;
import br.com.caio.todo.tasks.status.StatusTaskEnum;

@Component
public class ValidationTimeTasks {
	
	@Autowired
	EntityManager entityManager;
	
	@Autowired
	private TaskService taskService;
	
	    @Scheduled(cron = "00 00 00 * * *") 
	    @Transactional
	    public void verificationTasks() { 
	        System.out.println("Executrando tarefa" + new Date());
	        
	        List<Tasks> result = this.taskService.getAllTasks();
	        Date today = new Date();
	        
	        for (Tasks tasks : result) {
	        	if (tasks.getDeadlineDate() != null) {
	        		if (today.after(tasks.getDeadlineDate())) {
	        			tasks.setStatusTask(StatusTaskEnum.DEADLINE);
	        		}
	        	}
	        }
	    }

}
