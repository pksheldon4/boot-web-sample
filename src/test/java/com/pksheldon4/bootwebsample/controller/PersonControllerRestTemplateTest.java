package com.pksheldon4.bootwebsample.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pksheldon4.bootwebsample.model.Person;
import com.pksheldon4.bootwebsample.service.PersonService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.AbstractConfigurableWebServerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Collection;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PersonControllerRestTemplateTest {


    @Autowired
    private AbstractConfigurableWebServerFactory webServerFactory;

    @Autowired
    private TestRestTemplate restTemplate;
    private static ObjectMapper mapper = new ObjectMapper();

    @MockBean
    private PersonService personService;


    @Before
    public void setupPersons() {

        Collection<Person> people = new ArrayList<>();
        Person person1 = new Person("Test", "Person1");
        people.add(person1);
        people.add(new Person("Test", "Person2"));
        given(personService.findAll()).willReturn(people);
        given(personService.findByFirstAndLastName("Test","Person")).willReturn(people);
        given(personService.findById(1L)).willReturn(person1);
    }

    @Test
    public void testGetListOfPeople() throws Exception {

        ResponseEntity<String> responseEntity = restTemplate.getForEntity("/people", String.class);
        Assert.notNull(responseEntity, "ResponseEntity should not be null.");
        assertThat(responseEntity.getStatusCode(), equalTo(HttpStatus.OK));
        JsonNode root = mapper.readTree(responseEntity.getBody());
        Assert.isTrue(root.size() == 2, String.format("%d People found, 2 expected.", root.size()));
    }

    @Test
    public void testGetSpecificListOfPeople() throws Exception {

        ResponseEntity<String> responseEntity = restTemplate.getForEntity("/specificpeople?firstName=Test&lastName=Person", String.class);
        Assert.notNull(responseEntity, "ResponseEntity should not be null.");
        assertThat(responseEntity.getStatusCode(), equalTo(HttpStatus.OK));
        JsonNode root = mapper.readTree(responseEntity.getBody());
        Assert.isTrue(root.size() == 2, String.format("%d People found, 2 expected.", root.size()));
    }

    @Test
    public void testGetPerson() throws Exception {

        ResponseEntity<Person> responseEntity = restTemplate.getForEntity("/person/1", Person.class);
        Assert.notNull(responseEntity, "ResponseEntity should not be null.");
        assertThat(responseEntity.getStatusCode(), equalTo(HttpStatus.OK));
        Person person  = (Person)responseEntity.getBody();
        Assert.notNull(person, "Person should not be null.");
        assertThat(person.getLastName(), equalTo("Person1"));
    }
}
