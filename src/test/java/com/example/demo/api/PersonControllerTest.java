package com.example.demo.api;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.xpath;

import java.io.File;
import java.nio.file.Files;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.annotation.TestConfig;
import com.example.demo.domain.Person;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@TestConfig
public class PersonControllerTest {
    
    @Autowired
    private MockMvc mvc;
    
    @Test
    public void test() throws Exception {
        String xmlContent = "<person><id>10</id><firstName>Michael</firstName><lastName>Jordan</lastName></person>";
        mvc.perform(get("/person/10").accept(MediaType.APPLICATION_XML))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.parseMediaType("application/xml;charset=UTF-8")))
            .andExpect(content().xml(xmlContent));
    }
    
    @Test
    public void testFindByIdShouldReturnCorrectXml() throws Exception {
        Person person = new Person("Michael", "Jordan");
        person.setId(10L);
        
        String xmlContent = new XmlMapper().writeValueAsString(person);
        mvc.perform(get("/person/10").accept(MediaType.APPLICATION_XML))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.parseMediaType("application/xml;charset=UTF-8")))
            .andExpect(content().xml(xmlContent));
    }
    
    @Test
    public void testFindAllShouldReturnAllPersons() throws JsonProcessingException, Exception {
        mvc.perform(get("/person").accept(MediaType.APPLICATION_XML))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.parseMediaType("application/xml;charset=UTF-8")))
            .andExpect(xpath("people/persons/person[1]").exists())
            .andExpect(xpath("people/persons/person[1]/*").nodeCount(is(3)))
            .andExpect(xpath("people/persons/person[1]/id").string(is("10")))
            .andExpect(xpath("people/persons/person[1]/firstName").string(is("Michael")))
            .andExpect(xpath("people/persons/person[1]/lastName").string(is("Jordan")))
            .andExpect(xpath("people/persons/person[2]").exists())
            .andExpect(xpath("people/persons/person[2]/*").nodeCount(is(3)))
            .andExpect(xpath("people/persons/person[2]/id").string(is("20")))
            .andExpect(xpath("people/persons/person[2]/firstName").string(is("Scotty")))
            .andExpect(xpath("people/persons/person[2]/lastName").string(is("Pippen")));
    }

    @Test
    public void testFindAllShouldReturnAllPersonsFromFile() throws JsonProcessingException, Exception {
        File file = new File(ClassLoader.getSystemClassLoader().getResource("files/xml/all_persons.xml").getFile());
        String xmlString = new String(Files.readAllBytes(file.toPath()));
        
        mvc.perform(get("/person").accept(MediaType.APPLICATION_XML))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.parseMediaType("application/xml;charset=UTF-8")))
            .andExpect(content().xml(xmlString));
    }
}
