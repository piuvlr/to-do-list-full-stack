package br.com.caio.todo.tasks.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.caio.todo.tasks.service.UserService;
import br.com.caio.todo.tasks.vo.UserVO;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ResponseEntity<UserVO> login(HttpServletRequest httpServletRequest,
			@RequestParam(required = true, value = "user-name") String userName) {
		
		UserVO userVO = userService.findUserByUserName(userName);
		return new ResponseEntity<UserVO>(userVO, HttpStatus.OK);
	}
}
