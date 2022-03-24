package com.capg.execption;


public class CustomerNotFoundException extends RuntimeException {
	public CustomerNotFoundException(String msg){
		super(msg);
	}
}
