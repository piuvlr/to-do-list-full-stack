package br.com.caio.todo.tasks.utils;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Component;

import br.com.caio.todo.tasks.vo.TasksVO;


@Component
public class SendEmails {

	public void sendEmailDeadlineTasks(String emailUser, TasksVO task) {
		Properties properties = new Properties();

		// Conexão com o servidor do Gmail
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.socketFactory.port", "465");
		properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.port", "465");

		Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("tasks.para.fazer@gmail.com", "sjfzhrevvdjbdtpw");
			}
		});
		
		try {
			 Message message = new MimeMessage(session);
			 message.setFrom(new InternetAddress("tasks.para.fazer@gmail.com"));
			 
			 Address[] address = InternetAddress.parse(emailUser);
			 
			message.setRecipients(Message.RecipientType.TO, address);
			message.setSubject("Task chegando ao fim");
			message.setText("A sua tarefa vai vencer amanhã," 
					+ "Titulo: " + task.getNameTask());
			
			/**Método para enviar a mensagem criada*/
		    Transport.send(message);
		    
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}

}
