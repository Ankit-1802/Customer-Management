package com.capg.service;

import java.util.List;

import com.capg.dto.CustomerDTO;
import com.capg.dto.OrderDTO;
import com.capg.entity.Customer;
import com.capg.entity.Order;
import com.capg.entity.User;

public interface CustomerService {
	public Customer getCustomerById(int id) ;
	public Customer createCustomer(CustomerDTO customerDTO);
	public Customer updateCustomer(CustomerDTO customerDTO,int customerID);
	public Customer updateCustomerPartially(CustomerDTO  customerDTO,int customerID);
	public void deleteCustomer(int id);
	public List<CustomerDTO> fetchAllCustomers();  
	public List<Order> getordersByCustomerId(int cust_id);
	public List<User> readUsers();
	public Customer addCustomerOrders(CustomerDTO customerDTO, int customerID);
	public void deleteCustomerOrder(int customerID, int orderId);
	public Order updateCustomerOrder(OrderDTO orderDTO,int customerID, int orderId);
	
}
