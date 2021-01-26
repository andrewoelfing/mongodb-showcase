package de.huk.mongodbtest.demo;

import org.springframework.data.annotation.Id;

public class Lead {
    
    @Id
    private String id;

    private String firstName;
    private String lastName;
    private int seniority;

    public Lead() {}

    public Lead(String firstName, String lastName, int seniority) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.seniority = seniority;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getSeniority() {
        return this.seniority;
    }

    public void setSeniority(int seniority) {
        this.seniority = seniority;
    }

    @Override
    public String toString() {
        return String.format(
                "Lead[id=%s, firstName='%s', lastName='%s', seniority=%s]",
                id, firstName, lastName, seniority);
    }
}
