package com.ibm.actrec;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
class PersonNotFoundException extends RuntimeException {

    public PersonNotFoundException(Long personId) {
        super("Could not find person with ID '" + personId + "'.");
    }
}
