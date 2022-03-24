package com.capg.dto;

import java.util.Objects;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class AddressDTO {
	@NotBlank(message = "Street can't be empty")
	@NotNull(message = "Street can't be null")
	private String street;
	@NotBlank(message = "City can't be empty")
	@NotNull(message = "City can't be null")
	private String city;
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	@Override
	public int hashCode() {
		return Objects.hash(city, street);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AddressDTO other = (AddressDTO) obj;
		return Objects.equals(city, other.city) && Objects.equals(street, other.street);
	}
	
	
}
