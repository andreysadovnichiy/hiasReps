package com.solveast.rreps.account_manager;


public class Account implements java.io.Serializable {
	private Long id;
	private String login;
	private String password;
	private String personType;
	private String role = "ROLE_USER";
	private boolean active;

    protected Account() {
	}

	public Account(Long id, String login, String password, String role, boolean active) {
		this.id = id;
		this.login = login;
		this.password = password;
		this.role = role;
		this.active = active;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getPersonType() {
		return personType;
	}

	public void setPersonType(String personType) {
		this.personType = personType;
	}

	@Override
	public String toString() {
		return "Account{" +
				"id=" + id +
				", login='" + login + '\'' +
				", password='" + password + '\'' +
				", personType='" + personType + '\'' +
				", role='" + role + '\'' +
				", active=" + active +
				'}';
	}
}
