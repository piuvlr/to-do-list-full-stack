package br.com.caio.todo.tasks.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.caio.todo.tasks.model.Tasks;

@Repository
public interface TasksRepository extends JpaRepository<Tasks, Integer> {
	
	@Query(name = Tasks.FIND_TASKS_BY_USER_ID)
	public List<Tasks> loadTasks(Integer userId);

}
