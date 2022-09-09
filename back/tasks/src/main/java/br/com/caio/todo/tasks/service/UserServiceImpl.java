package br.com.caio.todo.tasks.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.caio.todo.tasks.bo.UserBO;
import br.com.caio.todo.tasks.model.User;
import br.com.caio.todo.tasks.vo.UserVO;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserBO userBO;

	@Override
	public UserVO findUserByUserName(String userName) {
		User user = userBO.findUserByUserName(userName);
		
		UserVO userVO = new UserVO(user.getId(), user.getUserName(), user.getCreationDate());
		
		return userVO;
	}
}
