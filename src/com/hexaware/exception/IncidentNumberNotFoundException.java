package com.hexaware.exception;

public class IncidentNumberNotFoundException extends Exception {
	public IncidentNumberNotFoundException() {
		super("Given Incident ID does not exist.");
	}
	
	@Override
	public String toString(){
		return "IncidentNumberNotFoundException: "+getMessage();
		
	}
}
