package com.thymeleaf.demo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

	@GetMapping("/showaddform")
	public String showAddCustForm(Model theModel) {
		Customer customer = new Customer();
		theModel.addAttribute("customer", customer);
		return "customer/showAddCustForm";
	}

	@GetMapping("/showformforupdate")
	public String showFormforUpdate(@RequestParam("customerId") String customerId, Model theModel) {
		Customer customer = customerServices.findByID(customerId);
		theModel.addAttribute("customer", customer);
		return "customer/updatecustomer";
	}

	// add a new customer
	@PostMapping("/addcustomer")
	public String save(@ModelAttribute("customer") Customer theCustomer) {
		customerServices.save(theCustomer);
		return "redirect:/dairymilk/customers";
	}

	@PostMapping("/updatecustomer")
	public String updatecustomer(@ModelAttribute("customer") Customer theCustomer) {
		customerServices.updateCustomer(theCustomer);
		return "redirect:/dairymilk/customers";
	}

	@GetMapping("/deletecustomer")
	public String deleteCustomer(@RequestParam("customerId") String theCustomer) {
		customerServices.deleteByID(theCustomer);
		return "redirect:/dairymilk/customers";
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

	/*
	 * @PostMapping("/addcustomer") public String addCustomer(@RequestBody Customer
	 * theCustomer) { return customerServices.addCustomer(theCustomer); }
	 */

	@GetMapping("/test")
	public String testFun() {
		return "Welcome in Dairy Milk";
	}

}
