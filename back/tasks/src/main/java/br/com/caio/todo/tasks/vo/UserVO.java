package br.com.caio.todo.tasks.vo;

import java.util.Date;

import br.com.caio.todo.tasks.status.EmailPermissionsUserEnum;

public class UserVO {
	private Integer id;
	private String userName;
	private Date creationDate;
	private String password;
	private String emailUser;
	private EmailPermissionsUserEnum emailPermissionsUserEnum;

	public UserVO(Integer id, String userName, Date creationDate, String emailUser, EmailPermissionsUserEnum emailPermissionsUserEnum) {
		this.id = id;
		this.userName = userName;
		this.creationDate = creationDate;
		this.emailUser = emailUser;
		this.emailPermissionsUserEnum = emailPermissionsUserEnum;
	}

	public Integer getId() {
		return id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getEmailUser() {
		return emailUser;
	}

	public void setEmailUser(String emailUser) {
		this.emailUser = emailUser;
	}

	public EmailPermissionsUserEnum getEmailPermissionsUserEnum() {
		return emailPermissionsUserEnum;
	}

	public void setEmailPermissionsUserEnum(EmailPermissionsUserEnum emailPermissionsUserEnum) {
		this.emailPermissionsUserEnum = emailPermissionsUserEnum;
	}

}
