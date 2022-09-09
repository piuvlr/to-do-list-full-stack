package br.com.caio.todo.tasks.model;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "USER_TASK")
@NamedQueries({ 
	@NamedQuery(name = User.FIND_USER_BY_USER_NAME, query = "select u from User u where u.userName = :userName"),
	})
public class User {

	public static final String FIND_USER_BY_USER_NAME = "User.FIND_USER_BY_USER_NAME";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Basic
	@Column(name = "USER_NAME", nullable = false, length = 30)
	private String userName;

	@Basic
	@Column(name = "CREATION_DATE")
	private Date creationDate = new Date();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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
