package com.swiggy.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Data
public class Restaurant {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer restaurantId;
	@NotBlank(message = "restaurant name can't be empty")
	private String restaurantName;
	@NotBlank(message = "address can't be empty")
	private String address;
	@JsonIgnore
	@OneToMany(mappedBy = "restaurant")
	private List<Orders> orders;
}
