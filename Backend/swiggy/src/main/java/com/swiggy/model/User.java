package com.swiggy.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userId;
	@Column(unique = true)
	private String username;
	@NotBlank(message = "password can't be blank")
	@JsonProperty(access = Access.WRITE_ONLY)
	private String password;
	@NotBlank(message = "role can't be blank")
	private String role;
	public User(String username, @NotBlank(message = "password can't be blank") String password,
			@NotBlank(message = "role can't be blank") String role) {
		super();
		this.username = username;
		this.password = password;
		this.role = role;
	}
}
