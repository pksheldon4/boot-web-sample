package com.pksheldon4.bootwebsample.service;

import com.pksheldon4.bootwebsample.model.Person;
import com.pksheldon4.bootwebsample.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;


/**
 *
 * This class is where the business logic would go.
 *
 */
@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonRepository personRepository;

    public Collection<Person> findAll() {
        return (Collection<Person>) personRepository.findAll();
    }

    public Person findById(Long personId) {

        Optional<Person> person = personRepository.findById(personId);
        if (person.isPresent()) {
            return person.get();
        }
        return null;
    }

    public Collection<Person> findByFirstAndLastName(String firstNameLike, String lastNameLike) {

        return (Collection<Person>)personRepository.findByFirstNameIsLikeAndLastNameIsLike(firstNameLike, lastNameLike);
    }

}
