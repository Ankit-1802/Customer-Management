package com.capg.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.capg.annotations.isValidDob;

public class CustomerDTO {
	
	private Integer customerId;
	@Size(max = 20)
	@NotBlank(message = "First Name Should not be empty")
	@Pattern(regexp = "[A-Za-z]{1,}",message = "Fist name should consist only alphabet!")
    private String firstName;
	@Size(max = 20)
	@Pattern(regexp = "[A-Za-z]{1,}",message = "Last name should consist only alphabet!")
	@NotBlank(message = "Last Name Should not be empty")
    private String lastName;
	
	@isValidDob()
	private LocalDate dateOfBirth;
	
	
	@NotBlank(message = "Email id Should not be empty or blank")
	@Email(regexp = "[a-zA-Z0-9]{1,}@capgemini.com" ,message =  "Wrong email format! should end with @capgemini.com")
	private String email;
    @Enumerated(EnumType.STRING)
    private CustomerType customerType;
    
    
    @Valid
    @NotNull(message = "Address can not be null or empty")
    private AddressDTO address;
    
    @Valid
    @NotNull(message = "Oders list cant be null and can be empty!")
    private List<OrderDTO> orders;
    public CustomerDTO() {
    }
    
	public AddressDTO getAddress() {
		return address;
	}

	public void setAddress(AddressDTO address) {
		this.address = address;
	}

	public String getFirstName() {
		return firstName;
	}
	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
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
	public List<OrderDTO> getOrders() {
		return orders;
	}

	public void setOrders(List<OrderDTO> orders) {
		this.orders = orders;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	@Override
	public int hashCode() {
		return Objects.hash(address, customerType, dateOfBirth, email, firstName, lastName, orders);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CustomerDTO other = (CustomerDTO) obj;
		return Objects.equals(address, other.address) && customerType == other.customerType
				&& Objects.equals(dateOfBirth, other.dateOfBirth) && Objects.equals(email, other.email)
				&& Objects.equals(firstName, other.firstName) && Objects.equals(lastName, other.lastName)
				&& Objects.equals(orders, other.orders);
	}

	@Override
	public String toString() {
		return "CustomerDTO [firstName=" + firstName + ", lastName=" + lastName + ", dateOfBirth=" + dateOfBirth
				+ ", email=" + email + ", customerType=" + customerType + ", address=" + address + ", orders=" + orders
				+ "]";
	}
	
    

}
