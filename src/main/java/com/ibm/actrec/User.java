package com.ibm.actrec;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class User {

    @Id
    @GeneratedValue
    private Long id;

    public Long getId() {
        return id;
    }

    @Column(unique = true)
    private String name;

    public String getName() {
        return name;
    }

    public User(String name) {
        this.name = name;
    }

    User() { // jpa only

    }
}
