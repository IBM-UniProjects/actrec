package com.ibm.actrec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/person")
public class PersonREST {

    private final PersonRepository personRepository;

    @Autowired
    PersonREST(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    Collection<Person> getPeople() {
        Collection<Person> people = this.personRepository.findAll();
        return people;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    Person getPerson(@PathVariable Long id) {
        return this.personRepository.findOne(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    Person addPerson(@RequestBody Person person) {
        Person result = this.personRepository.save(person);
        return person;
    }

    private void validatePersonId(Long id) {
        this.personRepository.findById(id).orElseThrow(
                () -> new PersonNotFoundException(id)
        );
    }
}
