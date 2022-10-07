package br.com.caio.todo.tasks.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.caio.todo.tasks.service.AuthenticService;
import br.com.caio.todo.tasks.service.UserService;
import br.com.caio.todo.tasks.vo.TokenVO;
import br.com.caio.todo.tasks.vo.UserVO;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private AuthenticService authenticService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<TokenVO> login(@RequestBody UserVO userVO) {
		UsernamePasswordAuthenticationToken dadosLogin = authenticService.authenticConvert(userVO);
		
		try {
			Authentication authentication = authenticationManager.authenticate(dadosLogin);
			String token = authenticService.generateToken(authentication);
			return ResponseEntity.ok(new TokenVO(token, "Bearer"));
		} catch (AuthenticationException e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<UserVO> register(@RequestBody UserVO userRequestVO) {
		UserVO result = userService.registerUser(userRequestVO);
		
		return new ResponseEntity<UserVO>(result, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	@Transactional
	public ResponseEntity<Boolean> logout() {
		return new ResponseEntity<Boolean>(Boolean.TRUE, HttpStatus.OK);
	}
}
