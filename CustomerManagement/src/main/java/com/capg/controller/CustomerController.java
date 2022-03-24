package com.capg.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.capg.dto.CustomerDTO;
import com.capg.dto.OrderDTO;
import com.capg.entity.Customer;
import com.capg.entity.Order;
import com.capg.service.CustomerService;

@RestController
@RequestMapping(value = "customers")
public class CustomerController {
	@Autowired
    private CustomerService customerService;

	@PostMapping("/create")
    public ResponseEntity<Customer> createCustomer(@Valid @RequestBody CustomerDTO customerDTO){
		return new ResponseEntity<Customer>(customerService.createCustomer(customerDTO) ,HttpStatus.CREATED);
    }
	@PutMapping("/update/{customerID}")
	public ResponseEntity<Customer> updateCustomer(@Valid @RequestBody CustomerDTO customerDTO,@PathVariable("customerID") int customerID){
		return new ResponseEntity<Customer>(customerService.updateCustomer(customerDTO,customerID), HttpStatus.ACCEPTED);
	}
	@PatchMapping("/update/{customerID}")
	public ResponseEntity<Customer> updateCustomerPartially(@RequestBody CustomerDTO customerDTO,@PathVariable("customerID") int customerID){
		return new ResponseEntity<Customer>(customerService.updateCustomerPartially(customerDTO,customerID), HttpStatus.ACCEPTED);
	}
	@GetMapping("/getAll")
	public List<CustomerDTO> fetchAllCustomers(){
		List<CustomerDTO> customerList = customerService.fetchAllCustomers();
		return customerList;
	}
	@GetMapping("/getbyID/{customerID}")
	public ResponseEntity<Customer> getCustomerById(@PathVariable("customerID") int customerID){
		return new ResponseEntity<Customer>(customerService.getCustomerById(customerID), HttpStatus.OK);
	}
	@DeleteMapping("/delete/{customerID}")  
	private ResponseEntity<String> deleteCustomer(@PathVariable("customerID") int customerID)   
	{  
		customerService.deleteCustomer(customerID);
		return new ResponseEntity<String>("Employee with ID "+customerID+" Deleted Successfully!",HttpStatus.NO_CONTENT);  
	}  
	
	@PutMapping("/addCustomerOrders/{customerID}")
	public ResponseEntity<Customer> addCustomerOrders(@RequestBody CustomerDTO customerDTO ,@PathVariable("customerID") int customerID){
		return new ResponseEntity<Customer>(customerService.addCustomerOrders(customerDTO,customerID), HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/deleteCustomerOrder/{customerID}/{orderId}")  
	private ResponseEntity<String> deleteCustomerOrder(@PathVariable("customerID") int customerID,@PathVariable("orderId") int orderId)   
	{  
		customerService.deleteCustomerOrder(customerID,orderId);
		return new ResponseEntity<String>("Employee with ID "+customerID+" Deleted Successfully!",HttpStatus.NO_CONTENT);  
	} 
	
	@GetMapping("/getOrderByCustID/{customerID}")
	public List<Order> getordersByCustomerId(@PathVariable("customerID") int customerID){
		return customerService.getordersByCustomerId(customerID);
	}
	
	@PutMapping("/updateCustomerOrder/{customerID}/{orderId}")
	public ResponseEntity<Order> updateCustomerOrder(@Valid @RequestBody OrderDTO orderDTO ,@PathVariable("customerID") int customerID,@PathVariable("orderId") int orderId){
		return  new ResponseEntity<Order>(customerService.updateCustomerOrder(orderDTO,customerID,orderId), HttpStatus.ACCEPTED);
	}
}

