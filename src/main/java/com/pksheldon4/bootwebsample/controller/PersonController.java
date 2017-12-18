package com.pksheldon4.bootwebsample.controller;

import com.pksheldon4.bootwebsample.model.Person;
import com.pksheldon4.bootwebsample.service.PersonService;
import com.pksheldon4.bootwebsample.service.PersonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping("/people")
    public Iterable<Person> listPeople() {

        return personService.findAll();
    }

    @GetMapping("/person")
    public Person findPerson(@RequestParam Long id) {

        return personService.findById(id);
    }

    @GetMapping("/specificpeople")
    public Iterable<Person> listPeopleByName(@RequestParam(required = false) String firstName, @RequestParam(required = false) String lastName) {

        return personService.findByFirstAndLastName(firstName, lastName);
    }

}
