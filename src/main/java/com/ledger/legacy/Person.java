package com.ledger.legacy;
public class Person {
	private String name;
	private double owes = 0;
	private double isOwed = 0;
	private double net = 0;
	
	public Person(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public double getOwes() {
		return owes;
	}
	
	public double getNet() {
		return net;
	}
	
	public void addOwes(double owes) {
		this.owes += owes;
		calcNet();
	}
	
	public double getIsOwed() {
		return isOwed;
	}
	
	public void addOwed(double isOwed) {
		this.isOwed += isOwed;
		calcNet();
	}
	
	public void reduceOwes(double ro) {
		owes -= ro;
		calcNet();
	}
	
	public void reduceOwed(double ro) {
		isOwed -= ro;
		calcNet();
	}
	
	private void calcNet() {
		net = isOwed - owes;
//		System.out.println("calcnet");
	}
	
}
