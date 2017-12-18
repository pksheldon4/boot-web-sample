package com.pksheldon4.bootwebsample.repository;

import com.pksheldon4.bootwebsample.model.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import java.util.Collection;
import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PersonRepositoryTest {

    @Autowired
    private PersonRepository personRepository;

    @Test
    public void testFindAll() throws Exception {
        Collection<Person> people = (Collection<Person>)personRepository.findAll();
        Assert.notNull(people,"PersonRepository can not return null.");
        Assert.notEmpty(people, "PersonRepository should not return an empty List.");
        Assert.isTrue(people.size() == 3, String.format(" %s people found but should be 3 people returned.", people.size()));
    }
    @Test
    public void testFindByFirstNameLikeAndLastNameLike() throws Exception {
        Collection<Person> people = (Collection<Person>)personRepository.findByFirstNameIsLikeAndLastNameIsLike("%","Person%");
        Assert.notNull(people,"PersonRepository can not return null.");
        Assert.notEmpty(people, "PersonRepository should not return an empty List.");
        Assert.isTrue(people.size() == 1, String.format("One specific person was expected but %d people returned.", people.size()));
    }
}
