package br.com.caio.todo.tasks.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import br.com.caio.todo.tasks.model.User;

public class TaskUtils {

	public static Integer getUserID() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User user = (User) authentication.getPrincipal();

		return user.getId();
		
	}
}
