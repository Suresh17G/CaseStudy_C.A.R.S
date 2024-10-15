package com.hexaware.exception;

public class CaseNotFoundException extends Exception {
	public CaseNotFoundException() {
		super("Given Case ID does not exist.");
	}
	
	@Override
	public String toString(){
		return "CaseNotFoundException: "+getMessage();
		
	}
}
