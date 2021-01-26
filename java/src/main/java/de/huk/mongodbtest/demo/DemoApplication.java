package de.huk.mongodbtest.demo;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Example;

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
		repository.save(new Lead("Christian", "Deinlein", 25));
		repository.save(new Lead("Udo", "Peter", 25));
		repository.save(new Lead("Peter", "Witoschek", 10));
		repository.save(new Lead("Philipp", "Bachmann", 5));
		repository.save(new Lead("Dennis", "Peetz", 10));
		repository.save(new Lead("Lieschen", "Müller", 35));

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
		System.out.println();

		// Update a document
		System.out.println("Update Lead with lastname('Peter'):");
		System.out.println("-------------------------------");
		List<Lead> leads = repository.findByLastName("Peter");
		for (Lead lead : leads) {
			if(lead.getFirstName().equals("Udo")) {
				lead.setFirstName("Stefan");
				repository.save(lead);
			}
		}

		// find Lead by seniority 
		System.out.println("Find Leads longer employed then:");
		System.out.println("-------------------------------");
		List<Lead> leadsBySeniority = repository.findByLongerEmployedThen(10);
		leadsBySeniority.forEach(lead -> System.out.printf("Leads employed more then %s years: %s\r\n", lead.getSeniority(), lead));
		
		// Delete a single Lead
		System.out.println("Delete Lead with findOne(byExample):");
		System.out.println("--------------------------------");
		Optional<Lead> leadToDelete = repository.findOne(Example.of(new Lead("Lieschen", "Müller", 35)));
		leadToDelete.ifPresent(lead -> repository.delete(lead));
		
	}
}
