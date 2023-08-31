package com.swiggy.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer extends User {

	@Column(unique = true)
	private Integer customerId = getUserId();
	@NotBlank(message = "name can't be empty or null")
	private String name;

	@Email(regexp = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$", message = "email should be in a proper format i.e example@domain.com")
	@NotBlank
	@Column(unique = true)
	private String email;

	@JsonIgnore
	@OneToMany(mappedBy = "customer")
	private List<Orders> orders = new ArrayList<>();
	
}
