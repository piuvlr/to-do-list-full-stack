package br.com.caio.todo.tasks.utils;

import java.util.Calendar;
import java.util.Date;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import br.com.caio.todo.tasks.model.User;

public class TaskUtils {

	public static Integer getUserID() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User user = (User) authentication.getPrincipal();

		return user.getId();
	}
	
	public static Date getEndTimeByDate(Date date) {
		Calendar result = Calendar.getInstance();
		result.setTime(date);
		result.set(Calendar.HOUR_OF_DAY, 23);
		result.set(Calendar.MINUTE, 59);
		result.set(Calendar.SECOND, 59);
		result.set(Calendar.MILLISECOND, 0);
		
		return result.getTime();
	}
	
	public static Date getInitialTimeByDate(Date date) {
		Calendar result = Calendar.getInstance();
		result.setTime(date);
		result.set(Calendar.HOUR_OF_DAY, 0);
		result.set(Calendar.MINUTE, 0);
		result.set(Calendar.SECOND, 0);
		result.set(Calendar.MILLISECOND, 0);
		
		return result.getTime();
	}
}
