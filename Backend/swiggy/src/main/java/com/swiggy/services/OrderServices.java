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
import com.swiggy.model.OrderStatus;
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
		order.setOrderStatus(OrderStatus.ASSIGNED);
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
	public List<Orders> getOrders(Integer page, Integer limit, String sortBy) {
		if (page != null && page == 0)
			throw new SwiggyException("Page index must not be zero");
		if (sortBy != null && !sortBy.equals("orderId") && !sortBy.equals("totalAmount"))
			throw new SwiggyException("please pass correct sorting criteria");
		if (page != null && limit != null && sortBy != null) {
			Pageable pageable = PageRequest.of(page - 1, limit, Sort.by(sortBy));
			return orderRepository.findAll(pageable).getContent();
		} else if (page != null && limit != null) {
			Pageable pageable = PageRequest.of(page - 1, limit);
			return orderRepository.findAll(pageable).getContent();
		} else {
			return orderRepository.findAll();
		}
	}

	@Override
	public Orders getOrderById(Integer orderId) {
		return orderRepository.findById(orderId)
				.orElseThrow(() -> new SwiggyException("can't find any order with id " + orderId));
	}
}
