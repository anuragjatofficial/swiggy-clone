package com.swiggy.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.swiggy.model.OrderStatus;
import com.swiggy.model.Orders;
import com.swiggy.services.IOrderServices;
import jakarta.validation.Valid;

@RestController
@CrossOrigin
public class OrderController {

	@Autowired
	private IOrderServices iOrderServices;

	@PostMapping("/customers/{customerId}/orders/{restaurantId}")
	public ResponseEntity<Orders> placeOrder(@PathVariable int customerId, @PathVariable int restaurantId,
			@Valid @RequestBody Orders orders) {
		orders.setOrderStatus(OrderStatus.CONFIRMED);
		orders.setOrderDateAndTime(LocalDateTime.now());
		return new ResponseEntity<Orders>(iOrderServices.placeOrder(orders, customerId, restaurantId),
				HttpStatus.CREATED);
	}

	@PatchMapping("/orders/{orderId}/{deliveryPartnerId}")
	public ResponseEntity<Orders> assignDeliveryPartnerToOrder(@PathVariable int orderId,
			@PathVariable int deliveryPartnerId) {
		return new ResponseEntity<Orders>(iOrderServices.assignDeliveryPartnerToOrder(orderId, deliveryPartnerId),
				HttpStatus.ACCEPTED);
	}

	@GetMapping("/orders")
	public ResponseEntity<List<Orders>> getAllOrders(@RequestParam(required = false) Integer page,
			@RequestParam(required = false) Integer limit, @RequestParam(required = false) String sortBy) {
		return new ResponseEntity<List<Orders>>(iOrderServices.getOrders(page, limit, sortBy), HttpStatus.OK);
	}

	@GetMapping("/orders/{orderId}")
	public ResponseEntity<Orders> getOrderById(@PathVariable Integer orderId) {
		return new ResponseEntity<Orders>(iOrderServices.getOrderById(orderId), HttpStatus.OK);
	}

}
