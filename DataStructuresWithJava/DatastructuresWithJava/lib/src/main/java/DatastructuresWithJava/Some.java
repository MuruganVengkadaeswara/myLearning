package DatastructuresWithJava;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import Entities.Person;
import Helpers.Randomizer;

public class Some {
	public static void main(String[] args) {

		List<Person> personList = new ArrayList<>();

		Randomizer randomiser = new Randomizer();

		for (int i = 1; i < 100000; i++) {
			Person person = new Person();
			person.setNId(new Long(i));
			person.setPersonName(randomiser.getRandomString(10));
			personList.add(person);
		}

		long startTime = System.currentTimeMillis();
		System.out.println(personList.get(9829));
		long endTime = System.currentTimeMillis();
		System.out.println(endTime - startTime);

	}
}
