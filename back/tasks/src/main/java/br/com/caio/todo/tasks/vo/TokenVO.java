package br.com.caio.todo.tasks.vo;

public class TokenVO {

	private String token;
	private String typeRequest;

	public TokenVO(String token, String typeRequest) {
		this.token = token;
		this.typeRequest = typeRequest;
	}

	public String getToken() {
		return token;
	}

	public String getTypeRequest() {
		return typeRequest;
	}

}
