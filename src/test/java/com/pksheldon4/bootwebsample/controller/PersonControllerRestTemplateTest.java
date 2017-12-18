package com.pksheldon4.bootwebsample.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pksheldon4.bootwebsample.model.Person;
import com.pksheldon4.bootwebsample.service.PersonService;
import com.pksheldon4.bootwebsample.service.PersonServiceImpl;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

import javax.validation.constraints.AssertTrue;
import java.util.ArrayList;
import java.util.Collection;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.BDDMockito.given;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PersonControllerRestTemplateTest {


    @LocalServerPort
    private String port;

    private static RestTemplate restTemplate = new RestTemplate();
    private static ObjectMapper mapper = new ObjectMapper();

    @MockBean
    private PersonService personService;


    @BeforeClass
    public void setupPersons() {

        Collection<Person> people = new ArrayList<>();
        people.add(new Person("Test", "Person1"));
        people.add(new Person("Test", "Person2"));
        given(personService.findAll()).willReturn(people);
    }

    @Test
    public void testGetListOfPeople() throws Exception {

        ResponseEntity<String> responseEntity = restTemplate.getForEntity(getRestUrl("/people"), String.class);
        Assert.notNull(responseEntity, "ResponseEntity should not be null.");
        assertThat(responseEntity.getStatusCode(), equalTo(HttpStatus.OK));
        JsonNode root = mapper.readTree(responseEntity.getBody());
        Assert.isTrue(root.size() == 2, String.format("%d People found, 2 expected.", root.size()));
    }

    private String getRestUrl(String uri) {
        Assert.notNull(uri, "URI can not be null.");
        return "http://localhost:" + port + uri;
    }

}
