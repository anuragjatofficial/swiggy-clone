package com.swiggy.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.swiggy.exceptions.SwiggyException;
import com.swiggy.model.Customer;
import com.swiggy.model.DeliveryPartner;
import com.swiggy.model.Orders;
import com.swiggy.model.Restaurant;
import com.swiggy.repository.CustomerRepostitory;
import com.swiggy.repository.DeliveryPartnerRepository;
import com.swiggy.repository.OrderRepository;
import com.swiggy.repository.RestaurantRepository;

@Service
public class OrderServices implements IOrderServices {
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private DeliveryPartnerRepository deliveryPartnerRepository;
	@Autowired
	private CustomerRepostitory customerRepostitory;
	@Autowired
	private RestaurantRepository restaurantRepository;

	@Override
	public Orders assignDeliveryPartnerToOrder(int orderId, int deliveryPartnerId) {
//		if(orderId.equals(null)) throw new SwiggyException("can't find any order with id " + order id)
		DeliveryPartner deliveryParnter = deliveryPartnerRepository.findById(orderId)
				.orElseThrow(() -> new SwiggyException("can't find any delivery partner with id " + deliveryPartnerId));

		Orders order = orderRepository.findById(orderId)
				.orElseThrow(() -> new SwiggyException("can't find any order with id " + deliveryPartnerId));

		order.setDeliveryPartner(deliveryParnter);
		return orderRepository.save(order);
	}

	@Override
	public Orders placeOrder(Orders order, Integer customerId, Integer restaurantId) {
		if (order == null)
			throw new SwiggyException("something went wrong try later");

//		if(order.ge)
		Customer customer = customerRepostitory.findById(customerId)
				.orElseThrow(() -> new SwiggyException("No customer found with id " + customerId));
		Restaurant restaurant = restaurantRepository.findById(restaurantId)
				.orElseThrow(() -> new SwiggyException("No restaurant found with id " + restaurantId));
		order.setRestaurant(restaurant);
		order.setCustomer(customer);

		return orderRepository.save(order);
	}

	@Override
	public List<Orders> getOrders() {
		Pageable pageable = PageRequest.of(0, 5, Sort.by("orderId"));
		List<Orders> orders = orderRepository.findAll(pageable).getContent();
		if (orders.isEmpty())
			throw new SwiggyException("No order found in database");
		return orders;
	}
}
