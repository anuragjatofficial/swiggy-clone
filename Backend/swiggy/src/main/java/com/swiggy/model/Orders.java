package com.swiggy.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
@Entity
@Data
public class Orders {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int orderId;
	@NotNull
	@NotEmpty(message="please add atleast one item")
	private List<String> items;
	private double totalAmount;
	@ManyToOne
	private Customer customer;
	@ManyToOne
	private Restaurant restaurant;
	@ManyToOne
	private DeliveryPartner deliveryPartner;
}
