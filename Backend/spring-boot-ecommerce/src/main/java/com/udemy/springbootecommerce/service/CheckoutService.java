package com.udemy.springbootecommerce.service;

import com.udemy.springbootecommerce.dto.Purchase;
import com.udemy.springbootecommerce.dto.PurchaseResponse;

public interface CheckoutService {

	PurchaseResponse placeOrder(Purchase purchase);
}
