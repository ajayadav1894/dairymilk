package com.thymeleaf.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thymeleaf.demo.dao.CustomerDAO;
import com.thymeleaf.demo.entity.Customer;

@Service
public class CustomerServicesImpl implements CustomerServices {

	private CustomerDAO customerDAO;

	@Autowired
	CustomerServicesImpl(CustomerDAO customerDAO) {
		this.customerDAO = customerDAO;
	}

	@Override
	@Transactional
	public List<Customer> findAll() {
		return customerDAO.findAll();
	}

	@Override
	@Transactional
	public Customer findByID(String mobile_number) {
		// TODO Auto-generated method stub
		return customerDAO.findByID(mobile_number);
	}

	@Override
	@Transactional
	public void save(Customer theCustomer) {

		customerDAO.save(theCustomer);

	}

	@Override
	@Transactional
	public void deleteByID(String mobile_number) {

		customerDAO.deleteByID(mobile_number);
	}

	@Override
	@Transactional
	public void updateCustomer(Customer theCustomer) {

		customerDAO.updateCustomer(theCustomer);

	}

	@Override
	@Transactional
	public String addCustomer(Customer theCustomer) {
		String result = customerDAO.addCustomer(theCustomer);
		return result;
	}

}
