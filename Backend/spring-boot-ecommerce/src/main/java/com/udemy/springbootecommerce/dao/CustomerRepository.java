package com.udemy.springbootecommerce.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.udemy.springbootecommerce.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

	Customer findByEmail(String email);
}
