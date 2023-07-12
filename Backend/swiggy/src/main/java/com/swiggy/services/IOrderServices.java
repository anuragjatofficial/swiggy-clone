package com.swiggy.services;

import java.util.List;

import com.swiggy.model.Orders;

public interface IOrderServices {
	Orders placeOrder(Orders order , Integer customerId, Integer restaurantId);
	List<Orders> getOrders();
	Orders assignDeliveryPartnerToOrder(int orderId, int deliveryPartnerId);
}
