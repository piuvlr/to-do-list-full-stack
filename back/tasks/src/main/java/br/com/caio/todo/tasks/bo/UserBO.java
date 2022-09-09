package br.com.caio.todo.tasks.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.caio.todo.tasks.model.User;
import br.com.caio.todo.tasks.repository.UserRepository;

@Repository
public class UserBO {
	
	@Autowired
	private UserRepository userRepository;
	
	
	public User findUserByUserName(String userName) {
		User user = userRepository.loadUser(userName);
		
		return user;
	}

}
