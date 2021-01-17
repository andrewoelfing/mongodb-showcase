package de.huk.mongodbtest.demo;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface LeadRepository extends MongoRepository<Lead, String> {
    
    public Lead findByFirstName(String firstName);
    public List<Lead> findByLastName(String lastName);
}
