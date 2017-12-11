package com.ibm.actrec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/location")
public class LocationREST {

    private final LocationRepository locationRepository;
    private final PersonRepository personRepository;

    @Autowired
    LocationREST(LocationRepository locationRepository, PersonRepository personRepository) {
        this.locationRepository = locationRepository;
        this.personRepository = personRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    Collection<Location> getLocations() {
        return this.locationRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{locationId}")
    Location getLocation(@PathVariable Long locationId) {
        return this.locationRepository.findOne(locationId);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/personId={personId}")
    Collection<Location> getPersonLocations(@PathVariable Long personId) {
        this.validatePersonId(personId);
        return this.locationRepository.findByPersonIdOrderByLastLocationTime(personId);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/personId={personId}")
    Location addLocation(@PathVariable Long personId, @RequestBody Location location) {
        this.validatePersonId(personId);
        Person person = this.personRepository.findOne(personId);
        location.setPerson(person);
        Location result = this.locationRepository.save(location);
        return result;
    }

    private void validatePersonId(Long personId) throws PersonNotFoundException {
        this.personRepository.findById(personId).orElseThrow(
                () -> new PersonNotFoundException(personId)
        );
    }
}
