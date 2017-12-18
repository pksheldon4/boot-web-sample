package com.pksheldon4.bootwebsample.service;

import com.pksheldon4.bootwebsample.model.Person;
import com.pksheldon4.bootwebsample.repository.PersonRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RunWith(SpringRunner.class)
public class PersonServiceTest {

    @Autowired
    private PersonService personService;

    @MockBean
    private PersonRepository personRepository;

    @Test
    public void testFindAllReturnsNotNull() throws Exception {

        Collection<Person> persons = personService.findAll();
        Assert.notNull(persons, "Person Service result should not be null.");
    }


    @Test
    public void testFindAllReturnsValidMockedPerson() throws Exception {

        Person catcher = setupPerson();
        Collection<Person> persons = personService.findAll();
        Assert.isTrue(persons.size() == 1, "Person Service should return 1 result.");
    }

    private Person setupPerson() {

        List<Person> persons = new ArrayList<>();
        Person person = new Person("Test","Person");
        persons.add(person);
        BDDMockito.given(personRepository.findAll()).willReturn(persons);
        return person;
    }

    @TestConfiguration
    static class PersonServiceTestConfiguration {

        @Bean
        public PersonService personService() {
            return new PersonServiceImpl();
        }
    }

}
