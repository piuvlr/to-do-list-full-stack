package br.com.caio.todo.tasks.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.caio.todo.tasks.model.Tasks;
import br.com.caio.todo.tasks.status.StatusTaskEnum;

@Repository
public interface TasksRepository extends JpaRepository<Tasks, Integer> {
	
	@Query(name = Tasks.LOAD_ALL_TASKS)
	public List<Tasks> allTasks();
	
	@Query(name = Tasks.FIND_TASKS_BY_USER_ID)
	public List<Tasks> loadTasks(Integer userId);
	
	@Query(name = Tasks.FIND_TASKS_BY_PERIOD)
	public List<Tasks> loadTasksByPeriod(Date iniDate, Date endDate, Integer userId);
	
	@Query(name = Tasks.FIND_TASK_BY_ID)
	public Tasks findTaskById();
	
	@Query(name = Tasks.FIND_TASKS_BY_PERIOD_AND_STATUS)
	public List<Tasks> loadTasksByPeriodAndStatus(Date iniDate, Date endDate, StatusTaskEnum statusTaskEnum); 

}
