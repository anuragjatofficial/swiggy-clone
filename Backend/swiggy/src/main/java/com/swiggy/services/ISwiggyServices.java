package com.swiggy.services;

import java.util.List;

import com.swiggy.exceptions.SwiggyException;
import com.swiggy.model.Customer;
import com.swiggy.model.DeliveryPartner;
import com.swiggy.model.Orders;
import com.swiggy.model.Restaurant;

public interface ISwiggyServices {
	Customer addCustomer(Customer customer) throws SwiggyException; // cus
	Restaurant addRestaurant(Restaurant restaurant) throws SwiggyException; // done
	Orders placeOrder(Orders order , Integer customerId, Integer restaurantId); // or
	List<Orders> getOrders(); // or
	List<Customer> getAllCustomers(); // cus
	Customer getCustomerById(int customerId); // cus
	List<Restaurant> getAllRestaurants(); // done
	Restaurant getRestaurantById(int restaurantId); // done 
	Orders assignDeliveryPartnerToOrder(int orderId, int deliveryPartnerId); // or
	List<DeliveryPartner> getAllDeliveryPartners();
	DeliveryPartner getDeliveryPartnerById(int deliveryPartnerId);
	DeliveryPartner addDeliveryPartner(DeliveryPartner deliveryPartner);
}

