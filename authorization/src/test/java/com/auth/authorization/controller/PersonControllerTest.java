package com.auth.authorization.controller;


import com.auth.authorization.AuthorizationApplication;
import com.auth.authorization.domain.Person;
import com.auth.authorization.repository.PersonRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringBootTest(classes = AuthorizationApplication.class)
public class PersonControllerTest {

    private MediaType contentType = new MediaType(
            "application", "json");

    private MockMvc mockMvc;

    private String userName = "login";

    private HttpMessageConverter mappingJackson2HttpMessageConverter;

    private Person person;

    private List<Person> personList = new ArrayList<>();

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private WebApplicationContext webApplicationContext;


    @Autowired
    void setConverters(HttpMessageConverter<?>[] converters) {

        this.mappingJackson2HttpMessageConverter = Arrays.asList(converters).stream().filter(
                hmc -> hmc instanceof MappingJackson2HttpMessageConverter).findAny().get();

        Assert.assertNotNull("the JSON message converter must not be null",
                this.mappingJackson2HttpMessageConverter);
    }

    @Before
    public void setup() throws Exception {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();

        this.personRepository.deleteAllInBatch();

        this.person = personRepository.save(new Person(userName, "password"));

        personList.add(person);

    }

    @Test
    public void userNotFound() throws Exception {
        mockMvc.perform(get("/23")
                .content(this.json(new Person()))
                .contentType(contentType))
                .andExpect(status().isNotFound());
    }

    @Test
    public void readPerson() throws Exception {
        mockMvc.perform(get("/person/" +
                + personList.get(0).getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$.id", is(this.personList.get(0).getId())))
                .andExpect(jsonPath("$.login", is(this.personList.get(0).getLogin())))
                .andExpect(jsonPath("$.password", is(this.personList.get(0).getPassword())));
    }

    @Test
    public void createPerson() throws Exception {
        String personJson = json(new Person(
                "user", "password"));
        this.mockMvc.perform(post("/person/" )
                .contentType(contentType)
                .content(personJson))
                .andExpect(status().isCreated());
    }

    @Test
    public void updatePerson() throws Exception {
        person.setLogin("newUser");
        String personJson = json(person);
        this.mockMvc.perform(put("/person/" )
                .contentType(contentType)
                .content(personJson))
                .andExpect(status().isOk());
    }

    @Test
    public void deletePerson() throws Exception {
        String personJson = json(person);
        this.mockMvc.perform(delete("/person/" + person.getId())
                .contentType(contentType)
                .content(personJson))
                .andExpect(status().isOk());
    }

    protected String json(Object o) throws IOException {
        MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
        this.mappingJackson2HttpMessageConverter.write(
                o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
        return mockHttpOutputMessage.getBodyAsString();
    }


}
