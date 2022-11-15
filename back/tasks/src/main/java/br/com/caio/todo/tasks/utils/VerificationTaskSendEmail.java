package br.com.caio.todo.tasks.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import br.com.caio.todo.tasks.model.Tasks;
import br.com.caio.todo.tasks.service.TaskService;
import br.com.caio.todo.tasks.status.EmailPermissionsUserEnum;
import br.com.caio.todo.tasks.status.StatusTaskEnum;

@Component
public class VerificationTaskSendEmail {

	@Autowired
	EntityManager entityManager;

	@Autowired
	private TaskService taskService;

	@Autowired
	SendEmails sendEmails;

	@Scheduled(cron = "00 00 00 * * *")
	public void verificationTasks() {
		System.out.println("Executando tarefa => " + new Date());

		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, 1);

		Date iniDate = TaskUtils.getInitialTimeByDate(calendar.getTime());

		Date endDate = TaskUtils.getEndTimeByDate(calendar.getTime());

		List<Tasks> result = this.taskService.loadTasksPeriodAndStatus(iniDate, endDate, StatusTaskEnum.PROGRESS);

		for (Tasks tasks : result) {
			if (EmailPermissionsUserEnum.APPROVED.equals(tasks.getUser().getPermissionEMails())) {
				this.sendEmails.sendEmailDeadlineTasks(tasks.getUser().getEmailUser(), ParseUtils.parseTask(tasks));
			}
		}
	}

}
