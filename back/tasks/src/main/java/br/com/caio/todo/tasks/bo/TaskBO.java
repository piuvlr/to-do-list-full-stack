package br.com.caio.todo.tasks.bo;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.caio.todo.tasks.model.Tasks;
import br.com.caio.todo.tasks.repository.TasksRepository;
import br.com.caio.todo.tasks.status.StatusTaskEnum;

@Repository
public class TaskBO {
	
	@Autowired
	private TasksRepository tasksRepository;
	
	public List<Tasks> loadAllTasks() {
		List<Tasks> result = tasksRepository.allTasks();
		
		return result;
	}
	
	public List<Tasks> loadTasks(Integer userId) {
		List<Tasks> result = tasksRepository.loadTasks(userId);
		
		return result;
	}
	
	public Tasks createTask(Tasks tasks) {
		tasksRepository.save(tasks);
		System.out.println("Task criada com sucesso!");

		return tasks;
	}

	public List<Tasks> loadTasksPeriod(Date iniDate, Date endDate, Integer userId) {
		List<Tasks> result = tasksRepository.loadTasksByPeriod(iniDate, endDate, userId);
		
		return result;
	}

	public boolean deleteTask(Integer id) {
		tasksRepository.deleteById(id);
		
		return true;
	}

	public Tasks findTaskById(Integer id) {
		Optional<Tasks> task = tasksRepository.findById(id);
		return task.get();
	}

	public List<Tasks> loadTasksPeriodAndStatus(Date iniDate, Date endDate, StatusTaskEnum statusTaskEnum) {
		List<Tasks> result = tasksRepository.loadTasksByPeriodAndStatus(iniDate, endDate, statusTaskEnum);
		
		return result;
	}
}
