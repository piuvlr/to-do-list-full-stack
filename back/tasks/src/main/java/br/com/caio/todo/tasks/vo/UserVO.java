package br.com.caio.todo.tasks.vo;

import java.util.Date;

public class UserVO {
	private Integer id;
	private String userName;
	private Date creationDate;
	
	public UserVO(Integer id, String userName, Date creationDate) {
		this.id = id;
		this.userName = userName;
		this.creationDate = creationDate;
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

}
