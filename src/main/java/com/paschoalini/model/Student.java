package com.paschoalini.model;

import javax.persistence.Entity;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@SuppressWarnings("serial")
@Entity
public class Student extends AbstractEntity {
	@NotEmpty(message = "O campo nome do estudante é obrigatório.")
	@Size(min = 3, max = 30)
	private String name;
	
	@NotEmpty(message = "O email do estudante é obrigatório.")
	@Email
	private String email;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
