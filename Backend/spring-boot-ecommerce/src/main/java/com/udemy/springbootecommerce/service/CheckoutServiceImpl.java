package com.udemy.springbootecommerce.service;

import java.util.Set;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.udemy.springbootecommerce.dao.CustomerRepository;
import com.udemy.springbootecommerce.dto.Purchase;
import com.udemy.springbootecommerce.dto.PurchaseResponse;
import com.udemy.springbootecommerce.entity.Customer;
import com.udemy.springbootecommerce.entity.Order;
import com.udemy.springbootecommerce.entity.OrderItem;

@Service
public class CheckoutServiceImpl implements CheckoutService{

	private CustomerRepository customerRepository;
	
	public CheckoutServiceImpl(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}
	
	@Override
	@Transactional
	public PurchaseResponse placeOrder(Purchase purchase) {
		
		// retrieve the order info from dto
		Order order = purchase.getOrder();
		// generate tracking number
		String orderTrackingNumber = generateOrderTrackingNumber();
		order.setOrderTrackingNumber(orderTrackingNumber);
		
		// populate order with orderItems
		Set<OrderItem> orderItems = purchase.getOrderItems();
		orderItems.forEach(item -> order.add(item));
		
		// populate order with billingAddress and shippingAddress
		order.setBillingAddress(purchase.getBillingAddress());
		order.setShippingAddress(purchase.getShippingAddress());
		
		//populate customer with order
		Customer customer = purchase.getCustomer();
		
		// check if this is an existing cutomer
		String theEmail = customer.getEmail();
		
		Customer customerFromDB = customerRepository.findByEmail(theEmail);
		
		if(customerFromDB != null) {
			// we found them... let's assign them accordingly
			customer = customerFromDB;
		}
		customer.add(order);
		
		// save to the database
		customerRepository.save(customer);
		// return a respone 
		return new PurchaseResponse(orderTrackingNumber);
	}

	private String generateOrderTrackingNumber() {
		
		// generate a randon UUID number (UUID version-4)
		
		return UUID.randomUUID().toString();
	}

	
}
