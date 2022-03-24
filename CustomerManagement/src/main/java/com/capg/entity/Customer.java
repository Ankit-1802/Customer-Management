package com.capg.entity;

import java.util.Objects;
import javax.persistence.*;
import com.capg.dto.CustomerType;

import java.time.LocalDate;
import java.util.*;
@Entity
@Table(name = "Customer3")
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
     private Integer customerId;
     private String firstName;
     private String lastName;
     private LocalDate dateOfBirth;
     private String email;
     @Enumerated(EnumType.STRING)
     private CustomerType customerType;
     
     @OneToOne(cascade = CascadeType.ALL)
     @JoinColumn(name = "address_id",referencedColumnName = "addressId",unique = true)
     private Address address;
     
     @OneToMany(cascade = CascadeType.ALL )
     @JoinColumn(name = "cust_id",referencedColumnName = "customerId",nullable = false)
     private List<Order> orders;
     
     public Customer() {
     	
     }
	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public CustomerType getCustomerType() {
		return customerType;
	}

	public void setCustomerType(CustomerType customerType) {
		this.customerType = customerType;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public List<Order> getOrders() {
		return orders;
	}
	public void setOrders(List<Order> order) {
		this.orders = order;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	@Override
	public int hashCode() {
		return Objects.hash(address, customerId, customerType, dateOfBirth, email, firstName, lastName, orders);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		return Objects.equals(address, other.address) && Objects.equals(customerId, other.customerId)
				&& customerType == other.customerType && Objects.equals(dateOfBirth, other.dateOfBirth)
				&& Objects.equals(email, other.email) && Objects.equals(firstName, other.firstName)
				&& Objects.equals(lastName, other.lastName) && Objects.equals(orders, other.orders);
	}
	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", dateOfBirth=" + dateOfBirth + ", email=" + email + ", customerType=" + customerType + ", address="
				+ address + ", orders=" + orders + "]";
	}
    
     
}

