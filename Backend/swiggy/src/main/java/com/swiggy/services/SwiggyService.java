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
public class SwiggyService implements ISwiggyServices {
	@Autowired
	private CustomerRepostitory customerRepostitory;
	@Autowired
	private DeliveryPartnerRepository deliveryPartnerRepository;
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private RestaurantRepository restaurantRepository;

	// -- methods related to customer -- //

	@Override
	public Customer addCustomer(Customer customer) throws SwiggyException {
		if (customer == null)
			throw new SwiggyException("customer value can't be null");

//		 if (customerRepostitory.existsById(customer.getCustomerId()))
//			throw new SwiggyException("Customer already present in database ");
		return customerRepostitory.save(customer);
	}

	@Override
	public List<Customer> getAllCustomers() {
		return customerRepostitory.findAll();
	}

	@Override
	public Customer getCustomerById(int customerId) {
		return customerRepostitory.findById(customerId)
				.orElseThrow(() -> new SwiggyException("can't find any customer with id " + customerId));
	}

	// -- methods related to restaurant -- //

	@Override
	public List<Restaurant> getAllRestaurants() {
		return restaurantRepository.findAll();
	}

	@Override
	public Restaurant getRestaurantById(int restaurantId) {
		return restaurantRepository.findById(restaurantId)
				.orElseThrow(() -> new SwiggyException("can't find any restaurant with id " + restaurantId));
	}

	@Override
	public Restaurant addRestaurant(Restaurant restaurant) throws SwiggyException {
		if (restaurant == null)
			throw new SwiggyException("restaurant  value can't be null");
		if (restaurant.getRestaurantId() != null) {

			Restaurant restaurant2 = restaurantRepository.findById(restaurant.getRestaurantId()).orElse(null);
			if (restaurant2 != null)
				throw new SwiggyException("Restaurant already present in database ");
		}
		return restaurantRepository.save(restaurant);
	}

	// -- order related methods -- //

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

	// -- methods related to deliveryPartener -- //

	@Override
	public List<DeliveryPartner> getAllDeliveryPartners() {
		return deliveryPartnerRepository.findAll();
	}

	@Override
	public DeliveryPartner getDeliveryPartnerById(int deliveryPartnerId) {
		return deliveryPartnerRepository.findById(deliveryPartnerId)
				.orElseThrow(() -> new SwiggyException("can't find any delivery partner with id " + deliveryPartnerId));
	}
	
	@Override
	public DeliveryPartner addDeliveryPartner(DeliveryPartner deliveryPartner) {
//		if(deliveryPartnerRepository.findById(deliveryPartner.getDeliveryPartnerId()).orElse(null) != null) {
//			throw new SwiggyException("delivery person already available in database ");
//		}
		return deliveryPartnerRepository.save(deliveryPartner);
	}
}

