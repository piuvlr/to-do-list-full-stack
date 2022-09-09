package br.com.caio.todo.tasks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.caio.todo.tasks.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	
	@Query(name = User.FIND_USER_BY_USER_NAME)
	public User loadUser(String userName);
	
}
