package br.com.caio.todo.tasks.service;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.com.caio.todo.tasks.vo.UserVO;

@Service
public interface AuthenticService {

	public UsernamePasswordAuthenticationToken authenticConvert(UserVO userVO);

	public String generateToken(Authentication authentication);

	public boolean isTokenValid(String token);

	public Integer getIdUser(String token);

}
