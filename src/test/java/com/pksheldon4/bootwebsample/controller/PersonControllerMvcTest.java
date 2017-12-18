package com.pksheldon4.bootwebsample.controller;

import com.pksheldon4.bootwebsample.model.Person;
import com.pksheldon4.bootwebsample.service.PersonService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(PersonController.class)
public class PersonControllerMvcTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private PersonService personService;


    @Test
    public void testGetListOfPeople() throws Exception {

        Person person = setupPeople();

        mvc.perform(get("/people")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].firstName", is(person.getFirstName())));

    }

    private Person setupPeople() {
        List<Person> people = new ArrayList<>();
        Person person = new Person("Test", "Person1");
        people.add(person);
        people.add(new Person("Test","Person2"));

        given(personService.findAll()).willReturn(people);
        return person;
    }
}

