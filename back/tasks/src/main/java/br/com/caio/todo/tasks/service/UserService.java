package br.com.caio.todo.tasks.service;

import org.springframework.stereotype.Service;

import br.com.caio.todo.tasks.vo.UserVO;

@Service
public interface UserService {
	
	public UserVO findUserByUserName(String userName);

}
