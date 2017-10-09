package com.ibm.actrec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collection;

@RestController
@RequestMapping("/activity")
public class ActivityRestController {

    private final UserRepository userRepository;

    @Autowired
    ActivityRestController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/users")
    Collection<User> getUsers() {
        return this.userRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/user/{id}")
    User getUser(@PathVariable Long id) {
        return this.userRepository.findOne(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/user")
    ResponseEntity<?> addUser(@RequestBody User user) {
        User result = this.userRepository.save(new User(user.getName()));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(result.getId()).toUri();
        return ResponseEntity.created(location).build();
    }


}
