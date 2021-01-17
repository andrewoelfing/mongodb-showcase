package de.huk.mongodbtest.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	@Autowired
	private LeadRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		repository.deleteAll();

		// save a couple of leads
		repository.save(new Lead("Christian", "Deinlein"));
		repository.save(new Lead("Udo", "Peter"));
		repository.save(new Lead("Peter", "Witoschek"));
		repository.save(new Lead("Philipp", "Bachmann"));
		repository.save(new Lead("Dennis", "Peetz"));

		// fetch all leads
		System.out.println("Leads found with findAll():");
		System.out.println("-------------------------------");
		for (Lead lead : repository.findAll()) {
			System.out.println(lead);
		}
		System.out.println();

		// fetch an individual lead
		System.out.println("Lead found with findByFirstName('Udo'):");
		System.out.println("--------------------------------");
		System.out.println(repository.findByFirstName("Udo"));

		System.out.println("Leads found with findByLastName('Peetz'):");
		System.out.println("--------------------------------");
		for (Lead customer : repository.findByLastName("Peetz")) {
			System.out.println(customer);
		}
	}
}
