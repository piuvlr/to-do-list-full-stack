package br.com.caio.todo.tasks.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.caio.todo.tasks.model.User;
import br.com.caio.todo.tasks.repository.UserRepository;
import br.com.caio.todo.tasks.vo.UserVO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class AuthenticServiceImpl implements UserDetailsService, AuthenticService {

	@Value("${forum.jwt.expiration}")
	private String expiration;

	@Value("${forum.jwt.secret}")
	private String secret;
	
	@Autowired
	UserRepository userRepository;

	@Override
	public UsernamePasswordAuthenticationToken authenticConvert(UserVO userVO) {
		return new UsernamePasswordAuthenticationToken(userVO.getUserName(), userVO.getPassword());
	}

	@Override
	public String generateToken(Authentication authentication) {
		User userLogado = (User) authentication.getPrincipal();
		Date today = new Date();

		Date dateExpiration = new Date(today.getTime() + Long.parseLong(expiration));
		
		return Jwts.builder().setIssuer("API - TASKS").setSubject(userLogado.getId().toString())
				.setIssuedAt(today).setExpiration(dateExpiration).signWith(SignatureAlgorithm.HS256, secret).compact();
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = null;

		try {
			user = userRepository.loadUser(username);
		} catch (Exception e) {
			throw new UsernameNotFoundException("Dados inavalidos");
		}

		return user;
	}

	@Override
	public boolean isTokenValid(String token) {
		try {
			Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Integer getIdUser(String token) {
		Claims claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
		return Integer.valueOf(claims.getSubject());
	}

}
