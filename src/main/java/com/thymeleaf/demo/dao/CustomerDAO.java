package com.thymeleaf.demo.dao;

import java.util.List;

import com.thymeleaf.demo.entity.Customer;

public interface CustomerDAO {

	public List<Customer> findAll();

	public Customer findByID(String mobile_number);

	public void save(Customer theCustomer);

	public void deleteByID(String mobile_number);

	public void updateCustomer(Customer theCustomer);

	public String addCustomer(Customer theCustomer);

}
