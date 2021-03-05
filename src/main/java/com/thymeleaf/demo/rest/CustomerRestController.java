package com.thymeleaf.demo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.thymeleaf.demo.entity.Customer;
import com.thymeleaf.demo.service.CustomerServices;

@Controller
@RequestMapping("/dairymilk")
public class CustomerRestController {

	// inject employee DAO
	private CustomerServices customerServices;

	// constructor injection to inject the
	@Autowired
	public CustomerRestController(CustomerServices customerServices) {
		this.customerServices = customerServices;
	}

	// expose /employee and return list of employee
	@GetMapping("/customers")
	public String findAll(Model theModel) {
		List<Customer> theCustomer = customerServices.findAll();
		theModel.addAttribute("customer", theCustomer);
		return "customer/list-customer";
	}

	// get customer by ID
	@GetMapping("/customer/{mobile_number}")
	public Customer getCustomerByID(@PathVariable String mobile_number) {
		Customer theCustomer = customerServices.findByID(mobile_number);

		if (theCustomer == null) {
			throw new RuntimeException("Customer id not found - " + mobile_number);
		}

		return theCustomer;
	}

	// add a new customer
	@PostMapping("/customer")
	public void save(@RequestBody Customer theCustomer) {
		// checking whether the customer exists or not
		Customer customerExists = customerServices.findByID(theCustomer.getMobile_number());

		if (customerExists == null) {
			customerServices.save(theCustomer);
		} else {
			throw new RuntimeException("Customer with customer id - " + theCustomer + " already exists");
		}
	}

	// add a new customer
	@PutMapping("/customerupdate")
	public void update(@RequestBody Customer theCustomer) {
		// checking whether the customer exists or not
		Customer customerExists = customerServices.findByID(theCustomer.getMobile_number());

		if (customerExists != null) {
			customerServices.save(theCustomer);
		} else {
			throw new RuntimeException("Customer with customer id - " + theCustomer + " not exists");
		}
	}

	@PostMapping("/addcustomer")
	public String addCustomer(@RequestBody Customer theCustomer) {
		return customerServices.addCustomer(theCustomer);
	}

	@GetMapping("/test")
	public String testFun() {
		return "Welcome in Dairy Milk";
	}

}
