package com.ledger.backend;
import java.util.InputMismatchException;
import java.util.Scanner;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ledger.exceptions.PersonNotFoundException;
import com.ledger.model.Person;
import com.ledger.model.Pool;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
	Scanner keyboard = new Scanner(System.in);
	Pool pool = new Pool(3);
	Pool testPool= new Pool(4);
	Person wesley = new Person("SUPER");
	PoolTest pt;
	Pool[] pt2 = new Pool[2];
	
	Controller() {
		 pt = new PoolTest();
	}

	@CrossOrigin(origins = "http://localhost:3000")
	@GetMapping(value="/getPools/{username}")
	public Pool[] getPool(@PathVariable String username) {
//		if(username.equals("SUPER")) {
//			return pt.getPools();
//		} 
		Pool[] pt3 = pt.getPools();
		System.out.print(pt3);
		return pt3;

	}
	
	

	//Login Test
	@CrossOrigin(origins = "http://localhost:3000")
	@GetMapping(value="/login/{username}/{password}")
	public Person login(@PathVariable String username,@PathVariable String password) {
		Person retVal = new Person("Nobody");
		if(username.equals("wesley") && password.equals("123")) {
			retVal = wesley;
		} 
		return retVal;

	}
	
	//Simple interface for entering transactions (wes)
	@CrossOrigin(origins = "http://localhost:3000")
	@GetMapping(value="/login2/{username}/{password}")
	public String login2(@PathVariable String username,@PathVariable String password) {
		if(username.equals("wesley") && password.equals("123")) {
			return "working";
		} 
		return "not working";

	}

	//Simple interface for entering transactions (wes)
	@CrossOrigin(origins = "http://localhost:3000")
	@GetMapping(value="/lol")
	public String test() {
		return "1";
	}

	@CrossOrigin(origins = "http://localhost:3000")
	@GetMapping(value="/create/testPool")
	public String testPool() {
		testPool = new Pool(6);
		testPool.addPerson(new Person("Adam"));
		testPool.addPerson(new Person("Duc"));
		testPool.addPerson(new Person("Wes"));
		testPool.addPerson(new Person("Eddy"));
		testPool.addPerson(new Person("Matt"));
		return "Success";
	}

	@CrossOrigin(origins = "http://localhost:3000")
	@GetMapping(value="/getTestPool")
	public Pool getTestPool() {
		return testPool;
	}

	@GetMapping(value="/")
	public void menu() {
		System.out.println("Enter transactions in format: \n[Ower]:[Owee] [Amount]\n");
		System.out.println("After entering all transactions, enter 'C' to calculate");
		Scanner sc = new Scanner(System.in);
		String transaction = "default";
		boolean keepGoing = true;
		while(keepGoing){
			transaction = sc.nextLine();
			if(transaction.equals("")) {
				transaction = sc.nextLine();
			}else if(transaction.equals("c")){
				keepGoing = false;
			}
			else {
				int x = transaction.indexOf(":");
				int y = transaction.indexOf(" ");
				int amount = Integer.parseInt(transaction.substring(y+1, transaction.length()));
				String ower = transaction.substring(0,x);
				String owee = transaction.substring(x+1,y);

				//Create ower and owee if needed
				if(!(pool.getPeople().containsKey(ower))) {
					pool.addPerson(new Person(ower));
				}
				if(!(pool.getPeople().containsKey(owee))) {
					pool.addPerson(new Person(owee));
				}
				pool.addTransaction(ower, owee, amount);
			}

		}
	}

	//Sets the net of all people
	//STILL BUGGY
	public void maidSplit() {
		System.out.println("Enter payments/owes in format: \n[Person] paid [Amount] OR \n[Person] owes [Amount]");
		System.out.println("After entering all transactions, enter 'C' to calculate");
		Scanner sc = new Scanner(System.in);
		String info = "default";
		boolean keepGoing = true;
		while(keepGoing){
			info = sc.nextLine();
			if(info.equals("")) {
				info = sc.nextLine();
			}else if(info.equals("c")){
				keepGoing = false;
			}else {
				int x = info.indexOf(" ");
				//				System.out.println("Info = " + info);
				//				System.out.println("x " + x);
				String person = info.substring(0,x);
				//				System.out.println("Person " + person);
				boolean owes = true;
				if(info.charAt(x+1) == 'p') {
					owes = false;
				}
				int amount = Integer.parseInt(info.substring(x+6,info.length()));
				//				System.out.println("Amount " + amount);

				//Create ower and owee if needed
				if(!(pool.getPeople().containsKey(person))) {
					pool.addPerson(new Person(person));
				}
				if(owes){
					pool.getPeople().get(person).addOwes(amount);
				}else {
					pool.getPeople().get(person).addOwed(amount);
				}

			}
		}
	}


	public void start() {

		pool.addPerson(new Person("Duc"));
		pool.addPerson(new Person("Adam"));
		//				pool.addPerson(new Person("Eddy"));
		//				pool.addPerson(new Person("Wes"));
		//				pool.addPerson(new Person("Matt"));
		//				pool.addPerson(new Person("Val"));
		//				
		//				pool.addTransaction("Adam", "Duc", 2);
		//				pool.addTransaction("Wes", "Adam", 2);	


		//						pool.getPeople().get("Duc").addOwed(500);
		//						pool.getPeople().get("Adam").addOwes(50);

		pool.calculate();
		keyboard.close();
	}

	private void inputNames() {
		System.out.println("Please enter the names of the people within the pool.");
		System.out.println("When finished, type 'done'.\n");

		while (true) {
			System.out.print("Enter a name of a user: ");
			String name = keyboard.nextLine();

			if (name.equals("done") || name.equals("'done'")) {
				break;		
			}

			//If the pool already contains that name, don't add it.
			//TODO: make case insensitive? Meaning you can't have 'John' and 'john' in the same pool.
			if (pool.getPeople().containsKey(name)) {
				System.out.println("'" + name + "' is already in the pool. Please give another name.");
			} else {
				pool.addPerson(new Person(name));
			}
		}
	}

	private void addTransactions() {
		System.out.println("\nPlease enter the transactions made by the people in the pool.");
		System.out.println("Exit at any time by typing 'done'.");
		try {
			/*
			 * while true, get the name of the person who borrowed money,
			 * the name of the person who gave money, and the amount of
			 * money that was given.
			 */
			while (true) {
				System.out.print("Enter the name of the person who borrowed money: ");
				String borrower = keyboard.nextLine();
				personExists(borrower);
				if (borrower.equals("done")) {
					break;
				}

				System.out.print("\nEnter the name of the person who gave money: ");
				String giver = keyboard.nextLine();
				personExists(giver);
				if (giver.equals("done")) {
					break;
				}

				System.out.print("\nEnter the amount of money that was given");
				System.out.println(" (Integers and decimals can be entered, e.g. '5.00' and '5' are the same)");
				double amount = keyboard.nextDouble();
				//Clear the scanner buffer after entering a number.
				String clearKeyboard = keyboard.nextLine();

				pool.addTransaction(borrower, giver, amount);
			}
		} catch (PersonNotFoundException e) {
			e.printStackTrace();
		} catch (InputMismatchException z) {
			//Clear the scanner buffer after entering a number.
			String clearKeyboard = keyboard.nextLine();
		}
	}

	private void personExists(String name) throws PersonNotFoundException {
		if (!pool.getPeople().containsKey(name)) {
			throw new PersonNotFoundException(name + " does not exist in the pool.");
		}
	}	
}
