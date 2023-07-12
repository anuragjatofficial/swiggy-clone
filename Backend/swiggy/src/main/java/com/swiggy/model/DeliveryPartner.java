package com.swiggy.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
public class DeliveryPartner {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int deliveryPartnerId;
	@NotNull
	@NotBlank
	private String mobileNumber;
	@NotBlank(message = "name can't be blank")
	private String name;
	@NotBlank(message = "address can't be blank")
	private String address;
	@JsonIgnore
	@OneToMany(mappedBy = "deliveryPartner")
	private List<Orders> orders;
}
