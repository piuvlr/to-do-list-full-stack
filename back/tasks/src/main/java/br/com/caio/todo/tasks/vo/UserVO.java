package br.com.caio.todo.tasks.vo;

import java.util.Date;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.caio.todo.tasks.model.User;

public class UserVO {
	private Integer id;
	private String userName;
	private Date creationDate;
	private String password;

	public UserVO(Integer id, String userName, Date creationDate) {
		this.id = id;
		this.userName = userName;
		this.creationDate = creationDate;
	}
	
	public static UserVO parse(User user) {
		UserVO userVO = new UserVO(user.getId(), user.getUsername(), user.getCreationDate());
		
		return userVO;
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
	
	public User parseToEntity(UserVO userVO) {
		User user = new User();
		user.setUserName(userVO.getUserName());
		user.setPassword(new BCryptPasswordEncoder().encode(userVO.getPassword()));
		
		return user;
	}

}
