package de.huk.mongodbtest.demo;

import org.springframework.data.annotation.Id;

public class Lead {
    
    @Id
    public String id;

    public String firstName;
    public String lastName;

    public Lead() {}

    public Lead(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return String.format(
                "Lead[id=%s, firstName='%s', lastName='%s']",
                id, firstName, lastName);
    }
}
