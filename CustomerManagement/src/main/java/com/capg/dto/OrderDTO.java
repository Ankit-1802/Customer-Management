package com.capg.dto;

import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public class OrderDTO {
	
	private Integer orderId;
	@NotNull(message = "Order name can not be empty")
//    @NotBlank(message = "Order name can not be empty")
	private String orderName;
    @NotNull(message = "quantity can not be empty")
//    @NotBlank(message = "quantity can not be empty")
    private Integer quantity;
    

    
    public OrderDTO() {
    	
    }
	public OrderDTO(String orderName, Integer quantity) {
		super();
		this.orderName = orderName;
		this.quantity = quantity;
	}
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public String getOrderName() {
		return orderName;
	}
	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	@Override
	public int hashCode() {
		return Objects.hash(quantity, orderName);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderDTO other = (OrderDTO) obj;
		return Objects.equals(quantity, other.quantity)
				&& Objects.equals(orderName, other.orderName);
	}
	@Override
	public String toString() {
		return "OrderDTO [orderId=" + orderId + ", orderName=" + orderName + ", Quantity=" + quantity + "]";
	}
	
	
}
