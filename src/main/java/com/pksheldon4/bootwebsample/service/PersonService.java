package com.pksheldon4.bootwebsample.service;

import com.pksheldon4.bootwebsample.model.Person;

import java.util.Collection;
import java.util.Optional;

public interface PersonService {

    public Collection<Person> findAll();

    public Person findById(Long personId);

    public Collection<Person> findByFirstAndLastName(String firstNameLike, String lastNameLike);
}
