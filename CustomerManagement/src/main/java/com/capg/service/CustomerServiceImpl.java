package com.capg.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capg.dto.AddressDTO;
import com.capg.entity.Address;
import com.capg.dto.CustomerDTO;
import com.capg.dto.OrderDTO;
import com.capg.entity.Customer;
import com.capg.entity.Order;
import com.capg.entity.User;
import com.capg.execption.CustomerNotFoundException;
import com.capg.repository.CustomerRepository;
import com.capg.repository.OrderRepository;
import com.capg.repository.UserRepository;

@Service
public class CustomerServiceImpl implements CustomerService {
     @Autowired	
     private CustomerRepository customerRepository;
     @Autowired	
     private OrderRepository orderRepository;
     @Autowired
     private UserRepository userRepository;
	@Override
	public Customer getCustomerById(int customerID) {
		    Customer customer  = customerRepository.findById(customerID).orElseThrow(()->new CustomerNotFoundException("Customer with ID "+customerID+" not Found"));
	   	    return customer;
	}

	@Override
	@Transactional
	public Customer updateCustomer(CustomerDTO customerDTO, int customerID) {
		Customer customer = customerRepository.findById(customerID).orElseThrow(()->new CustomerNotFoundException("Customer with ID "+customerID+" not Found"));
	   	    customer.setFirstName(customerDTO.getFirstName());
	    	customer.setLastName(customerDTO.getLastName());
	   	    customer.setCustomerType(customerDTO.getCustomerType());
	   	    customer.setDateOfBirth(customerDTO.getDateOfBirth());
	   	    customer.setEmail(customerDTO.getEmail());

	   	    Address address = new Address();
	   	    address.setStreet(customerDTO.getAddress().getStreet());
	   	    address.setCity(customerDTO.getAddress().getCity());
	   	    customer.setAddress(address);
	   	    return customerRepository.save(customer);
	}

	@Override
	public void deleteCustomer(int id) {
			customerRepository.findById(id).orElseThrow(()->new CustomerNotFoundException("Customer with ID "+id+" not Found"));
			customerRepository.deleteById(id);
		
	}
	@Override
	@Transactional
	public Customer createCustomer(CustomerDTO customerDTO) {
		
		Customer customer = new Customer();
   	    customer.setFirstName(customerDTO.getFirstName());
    	customer.setLastName(customerDTO.getLastName());
   	    customer.setCustomerType(customerDTO.getCustomerType());
   	    customer.setDateOfBirth(customerDTO.getDateOfBirth());
   	    customer.setEmail(customerDTO.getEmail());
   	    
   	    List<OrderDTO> orderDtoList  = customerDTO.getOrders();  
   	    List<Order> orders = new ArrayList<>();
   	    for(OrderDTO o: orderDtoList) {
   	    	Order order = new Order();
   	    	order.setOrderName(o.getOrderName());
   	        order.setQuantity(o.getQuantity());
   	        orders.add(order);
   	    }
   	    customer.setOrders(orders);
   	    
   	    Address address = new Address();
   	    address.setStreet(customerDTO.getAddress().getStreet());
   	    address.setCity(customerDTO.getAddress().getCity());
   	    customer.setAddress(address);
   	    
   	    return customerRepository.save(customer);
   	    
	}
	@Override
	public List<CustomerDTO> fetchAllCustomers() {
	     List<CustomerDTO> CustomerList = new ArrayList<CustomerDTO>();  
	     customerRepository.findAll().forEach(customer ->
	     {
	    	 CustomerDTO customerDTO = new CustomerDTO();
	    	 customerDTO.setCustomerId(customer.getCustomerId());
	    	 customerDTO.setFirstName(customer.getFirstName());
	    	 customerDTO.setLastName(customer.getLastName());
	    	 customerDTO.setCustomerType(customer.getCustomerType());
	    	 customerDTO.setDateOfBirth(customer.getDateOfBirth());
	    	 customerDTO.setEmail(customer.getEmail());
	    	 
		   	 List<Order> orderList =  customer.getOrders();
		   	 List<OrderDTO> orders  = new ArrayList<>();
		   	    for(Order o : orderList) {
		   	    	OrderDTO orderDto = new OrderDTO();
		   	    	orderDto.setOrderId(o.getOrderId());
		   	    	orderDto.setOrderName(o.getOrderName());
		   	        orderDto.setQuantity(o.getQuantity());
		   	        orders.add(orderDto);
		   	    }
		   	 customerDTO.setOrders(orders);
		   	    
	    	 AddressDTO address = new AddressDTO();
	    	 address.setStreet(customer.getAddress().getStreet());
	    	 address.setCity(customer.getAddress().getCity());
	    	 customerDTO.setAddress(address);
	    	 CustomerList.add(customerDTO);
	     });
	     return CustomerList;
	}

	@Override
	public Customer updateCustomerPartially(CustomerDTO customerDTO, int customerID){
		Customer customer  = customerRepository.findById(customerID).orElseThrow(()->new CustomerNotFoundException("Customer with ID "+customerID+" not Found"));
		    if(customerDTO.getFirstName()!= null)
		        customer.setFirstName(customerDTO.getFirstName());
		    if(customerDTO.getLastName()!= null)
    	        customer.setLastName(customerDTO.getLastName());
    	    if(customerDTO.getCustomerType()!= null)
   	            customer.setCustomerType(customerDTO.getCustomerType());
    	    if(customerDTO.getDateOfBirth()!=null)
    	    	customer.setDateOfBirth(customerDTO.getDateOfBirth());
    	    if(customerDTO.getEmail()!= null)
    	    	customer.setEmail(customerDTO.getEmail());
    	    if(customerDTO.getAddress()!=null) {
    	    	
    	    	Address address = new Address();
    	    	if(customerDTO.getAddress().getStreet() != null)
    	   	    address.setStreet(customerDTO.getAddress().getStreet());
    	   	    if( customerDTO.getAddress().getCity()!=null)
    	    	address.setCity(customerDTO.getAddress().getCity());
    	   	    
    	   	    customer.setAddress(address);
    	    }
			return customerRepository.save(customer);
	}
	
	
	@Override
	public Customer addCustomerOrders(CustomerDTO customerDTO, int customerID){
		Customer customer = customerRepository.findById(customerID).orElseThrow(()->new CustomerNotFoundException("Customer with ID "+customerID+" not Found"));
		List<OrderDTO> orderDtoList  = customerDTO.getOrders();
   	    List<Order> orders = new ArrayList<>();
   	    for(OrderDTO o: orderDtoList) {
   	    	Order order = new Order();
   	    	System.out.println(order);
   	    	order.setOrderName(o.getOrderName());
   	        order.setQuantity(o.getQuantity());
   	        orders.add(order);
   	    }
   	    customer.setOrders(orders);
	   	return customerRepository.save(customer);
	}

	@Override
	public void deleteCustomerOrder(int customerID, int orderId) {
		customerRepository.findById(customerID).orElseThrow(()->new CustomerNotFoundException("Customer with ID "+customerID+" not Found"));
		Order order = orderRepository.findById(orderId).orElseThrow(()->new CustomerNotFoundException("Order with ID "+orderId+" not Found"));
	    orderRepository.delete(order);
	}


	@Override
	public List<Order> getordersByCustomerId(int customerID) {
		Customer customer  =  customerRepository.findById(customerID).orElseThrow(()->new CustomerNotFoundException("Customer with ID "+customerID+" not Found"));
		return customer.getOrders();
	}

	@Override
	public Order updateCustomerOrder(OrderDTO orderDTO,int customerID, int orderId) {
		customerRepository.findById(customerID).orElseThrow(()->new CustomerNotFoundException("Customer with ID "+customerID+" not Found"));
		Order order = orderRepository.findById(orderId).orElseThrow(()->new CustomerNotFoundException("Order with ID "+orderId+" not Found"));
		order.setOrderName(orderDTO.getOrderName());
		order.setQuantity(orderDTO.getQuantity());
		return orderRepository.save(order);
	}

	@Override
	public List<User> readUsers() {
		return userRepository.findAll();
	}
     
}
