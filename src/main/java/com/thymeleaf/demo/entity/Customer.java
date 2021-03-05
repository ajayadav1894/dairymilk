package com.thymeleaf.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "customer")
public class Customer {

	@Id
	@Column(name = "mobile_number")
	private String mobile_number;

	@Column(name = "first_name")
	private String first_name;

	@Column(name = "last_name")
	private String last_name;

	@Column(name = "email")
	private String email;

	@Column(name = "six_digit_pin")
	private String six_digit_pin;

	public Customer() {
		super();
	}

	public Customer(String mobile_number, String first_name, String last_name, String email, String six_digit_pin) {
		super();
		this.mobile_number = mobile_number;
		this.first_name = first_name;
		this.last_name = last_name;
		this.email = email;
		this.six_digit_pin = six_digit_pin;
	}

	public String getMobile_number() {
		return mobile_number;
	}

	public void setMobile_number(String mobile_number) {
		this.mobile_number = mobile_number;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSix_digit_pin() {
		return six_digit_pin;
	}

	public void setSix_digit_pin(String six_digit_pin) {
		this.six_digit_pin = six_digit_pin;
	}

	@Override
	public String toString() {
		return "Customer [mobile_number=" + mobile_number + ", first_name=" + first_name + ", last_name=" + last_name
				+ ", email=" + email + ", six_digit_pin=" + six_digit_pin + "]";
	}
}
