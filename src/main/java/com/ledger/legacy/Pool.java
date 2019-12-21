package com.ledger.legacy;

import java.util.HashMap;

public class Pool {
	private double balance;
	private HashMap<String, Person> people;
	
	public Pool() {
		balance = 0.00;
		people = new HashMap<String, Person>();
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public int getAmtOfPpl() {
		return people.size();
	}
	
	public void addPerson(Person person) {
		people.put(person.getName(), person);
	}
	
	public HashMap<String, Person> getPeople() {
		return people;
	}
	
	public void addTransaction(String ower, String owee, double amount) {
//		System.out.println(ower);
//		System.out.println(owee);
//		System.out.println(amount);
//		System.out.println(people.get(ower).getName());
		
//		people.get("Wesley")
		people.get(ower).addOwes(amount);
		people.get(owee).addOwed(amount);
	

	}
	
	private boolean isEven() {
		for(Person person : people.values()) {
			if(person.getNet() != 0) {
				return false;
			}
		}
		return true;
	}
	
	public void calculate() {
//		for (Person person : people.values()) {
//			double toGive = person.getOwes();
//			double toReceive = person.getIsOwed();
//			
//			double net = toReceive - toGive;
//			
//			System.out.printf("%s's net is: %.2f\n", person.getName(), net);
//		}
		
		while (!isEven()) {
			
			Person owes = getLowestNet();
			Person owed = getHighestNet();
			
			if(owed.getNet() > Math.abs(owes.getNet())) {
				//^ Owed more than can pay
				double amount = Math.abs(owes.getNet());
				
				//Cancel the owes
				owes.reduceOwes(amount);
				
				//Reduce the owed
				owed.reduceOwed(amount);
				
				System.out.printf("%s pays %s %.2f\n", owes.getName(), owed.getName(), amount);
			}else {
				//amount is +5
				double amount = owed.getNet();
				
				//Cancel the owed
				owed.reduceOwed(amount);
				
				//Reduced the owes
				owes.reduceOwes(amount);
				
				System.out.printf("%s pays %s %.2f\n", owes.getName(), owed.getName(), amount);
			}
		}
		
		
	}
	
	private Person getHighestNet() {
		Person highestNet = null;
		for(Person p : people.values()) {
			if(highestNet == null) {
				highestNet = p;
			}else if (p.getNet() > highestNet.getNet()) {
				highestNet = p;
			}
		}
		return highestNet;
	}
	
	private Person getLowestNet() {
		Person lowestNet = null;
		for(Person p : people.values()) {
			if(lowestNet == null) {
				lowestNet = p;
			}else if (p.getNet() < lowestNet.getNet()) {
				lowestNet = p;
			}
		}
		return lowestNet;
	}
}