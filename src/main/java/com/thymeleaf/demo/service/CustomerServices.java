package com.thymeleaf.demo.service;

import java.util.List;

import com.thymeleaf.demo.entity.Customer;

public interface CustomerServices {

	public List<Customer> findAll();

	public Customer findByID(String mobile_number);

	public void save(Customer theCustomer);

	public void deleteByID(String mobile_number);

	public void update(Customer theCustomer);

	public String addCustomer(Customer theCustomer);
}
