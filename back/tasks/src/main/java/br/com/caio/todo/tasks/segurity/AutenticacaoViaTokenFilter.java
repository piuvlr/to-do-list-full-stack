package br.com.caio.todo.tasks.segurity;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.caio.todo.tasks.model.User;
import br.com.caio.todo.tasks.repository.UserRepository;
import br.com.caio.todo.tasks.service.AuthenticService;

public class AutenticacaoViaTokenFilter extends OncePerRequestFilter {

	private final int TIPO_AUTORIZACAO_REQUISICAO = 7;

	private AuthenticService authenticService;
	private UserRepository userRepository;

	public AutenticacaoViaTokenFilter(AuthenticService authenticService, UserRepository userRepository) {
		this.authenticService = authenticService;
		this.userRepository = userRepository;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String token = recuperarToken(request);
		boolean isTokenValid = authenticService.isTokenValid(token);

		if (isTokenValid) {
			autenticarUser(token);
		}

		filterChain.doFilter(request, response);

	}
	
	private String recuperarToken(HttpServletRequest request) {
		String token = request.getHeader("Authorization");
		if (token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
			return null;
		}
		
		return token.substring(TIPO_AUTORIZACAO_REQUISICAO, token.length());
	}
	
	private void autenticarUser(String token) {
		Integer idUser = authenticService.getIdUser(token);
		Optional<User> user = userRepository.findById(idUser);
		
		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(user.get(), null, user.get().getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
	}

}
