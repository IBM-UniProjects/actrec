package com.ibm.actrec;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Account {

    @OneToMany(mappedBy = "account")
    private Set<Bookmark> bookmarks = new HashSet<>();

    public Set<Bookmark> getBookmarks() {
        return bookmarks;
    }

    @Id
    @GeneratedValue
    private Long id;

    public Long getId() {
        return id;
    }

    @JsonIgnore
    public String password;

    public String getPassword() {
        return password;
    }

    public String username;

    public String getUsername() {
        return username;
    }

    public Account(String username, String password) {
        this.username = username;
        this.password = password;
    }

    Account() { // jpa only

    }
}