package br.com.caio.todo.tasks.bo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.caio.todo.tasks.model.Tasks;
import br.com.caio.todo.tasks.repository.TasksRepository;
import br.com.caio.todo.tasks.vo.TasksVO;

@Repository
public class TaskBO {
	
	@Autowired
	private TasksRepository tasksRepository;
	
	public List<Tasks> loadTasks(Integer userId) {
		List<Tasks> result = tasksRepository.loadTasks(userId);
		
		return result;
	}
	
	public Tasks createTask(Tasks tasks) {
		tasksRepository.save(tasks);
		System.out.println("Task criada com sucesso!");

		return tasks;
	}

}
