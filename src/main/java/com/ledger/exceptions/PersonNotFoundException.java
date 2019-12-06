package com.ledger.exceptions;

@SuppressWarnings("serial")
public class PersonNotFoundException extends Exception {
	
	public PersonNotFoundException(String message) {
		super(message);
	}
}
