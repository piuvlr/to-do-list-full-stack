package br.com.caio.todo.tasks.utils;

import java.util.Calendar;
import java.util.Date;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import br.com.caio.todo.tasks.model.User;

public class TaskUtils {
	
	/**
	 * Função que busca o id do usuario que está autenticado
	 * na sessão
	 * @return Long userID
	 */
	public static Integer getUserID() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User user = (User) authentication.getPrincipal();

		return user.getId();
	}
	
	/**
	 * Função que recebe uma data qualquer e altera a data dela para
	 * os últimos segundos/minutos/hora do dia
	 * @param date
	 * @return Date dd/MM/yyyy 23h 59min 59s
	 */
	public static Date getEndTimeByDate(Date date) {
		Calendar result = Calendar.getInstance();
		result.setTime(date);
		result.set(Calendar.HOUR_OF_DAY, 23);
		result.set(Calendar.MINUTE, 59);
		result.set(Calendar.SECOND, 59);
		result.set(Calendar.MILLISECOND, 0);
		
		return result.getTime();
	}
	
	/**
	 * Função que recebe uma data qualquer e altera a data dela para
	 * os primeiros segundos/minutos/hora do dia
	 * @param date
	 * @return Date dd/MM/yyyy 00h 00min 00s
	 */
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
