package com.ledger.backend;

import com.ledger.model.Person;
import com.ledger.model.Pool;

public class PoolTest {
	Pool[] arrayTest = new Pool[2];
	Pool pool1 = new Pool(1);
	Pool pool2 = new Pool(2);
	
	PoolTest(){
		pool1.addPerson(new Person("Adam"));
		pool1.addPerson(new Person("Duc"));
		pool1.addPerson(new Person("Eddy"));
		pool1.addPerson(new Person("Matt"));
		pool2.addPerson(new Person("Billy"));
		pool2.addPerson(new Person("Cameron"));
		pool2.addPerson(new Person("David"));
		pool2.addPerson(new Person("Ethan"));
	}
	
	public Pool[] getPools() {
		return arrayTest;
	}

}
