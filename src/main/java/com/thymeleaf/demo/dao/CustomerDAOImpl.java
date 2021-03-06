package com.thymeleaf.demo.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.thymeleaf.demo.entity.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

	// define a field for entity manager
	private EntityManager entityManager;

	// set up a constructor injection

	@Autowired
	public CustomerDAOImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	@Transactional
	public List<Customer> findAll() {

		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);

		// create a query
		Query<Customer> theQuery = currentSession.createQuery("from Customer", Customer.class);

		// execute query and get result list
		List<Customer> customerList = theQuery.getResultList();

		// return the list
		return customerList;
	}

	@Override
	@Transactional
	public Customer findByID(String mobile_number) {

		// get the current session object
		Session session = entityManager.unwrap(Session.class);

		// create the query
		Customer theCustomer = session.get(Customer.class, mobile_number);

		// return the customer
		return theCustomer;
	}

	@Override
	@Transactional
	public void save(Customer theCustomer) {

		// get the current session
		Session session = entityManager.unwrap(Session.class);

		// save the session object
		session.save(theCustomer);

	}

	@Override
	@Transactional
	public void updateCustomer(Customer theCustomer) {

		// get the current session
		Session session = entityManager.unwrap(Session.class);

		// update the session object
		Query theQuery = session.createQuery(
				"update Customer set first_name = :name , email = :email, six_digit_pin = :pin where mobile_number = :mobile_number");
		theQuery.setParameter("name", theCustomer.getFirst_name());
		theQuery.setParameter("email", theCustomer.getEmail());
		theQuery.setParameter("pin", theCustomer.getSix_digit_pin());
		theQuery.setParameter("mobile_number", theCustomer.getMobile_number());

		theQuery.executeUpdate();

	}

	@Override
	@Transactional
	public void deleteByID(String mobile_number) {

		// get the current session
		Session session = entityManager.unwrap(Session.class);

		// create the query
		Query theQuery = session.createQuery("delete from Customer where mobile_number=:mobile_number");

		// pass the input parameter to the query
		theQuery.setParameter("mobile_number", mobile_number);

		// execute the query
		theQuery.executeUpdate();

	}

	@Override
	@Transactional
	public String addCustomer(Customer theCustomer) {

		// result
		String result = "";

		// get the current session object
		Session session = entityManager.unwrap(Session.class);
		// store the data into database
		// check whether the customer already exist in the database or not
		Customer customer = session.get(Customer.class, theCustomer.getMobile_number());

		if (customer == null) {
			session.save(theCustomer);
			result.concat("Customer Registartion Successfully");
		} else {
			result.concat("Sorry " + theCustomer.getMobile_number() + "Already Registered with DairyMilk ");
		}

		return result;
	}

}
