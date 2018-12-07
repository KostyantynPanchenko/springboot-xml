package com.example.demo.api;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.People;
import com.example.demo.domain.Person;
import com.example.demo.service.PersonService;

@RestController
public class PersonController {
    
    private final static Logger LOGGER = LoggerFactory.getLogger(PersonController.class);
    private PersonService personService;
    
    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }
    
    @GetMapping("/person/{id}")
    public Person findPerson(@PathVariable(value = "id") long id) {
        try {
            int i = 10/0;
        } catch (Exception e) {
            LOGGER.error("Caught exception {}", "RUNTIME", e);
            throw new RuntimeException(e);
        }
        
        System.out.println("\n\n\n");
        
//        try {
//            int i = 10/0;
//        } catch (Exception e) {
//            LOGGER.error("Caught exception {}", "RUNTIME");
//            throw new RuntimeException(e);
//        }
        
        return personService.findPersonById(id);
    }
    
    @GetMapping("/person")
    public People showAllPeople() {
        List<Person> allPeople =  personService.findAll();
        return new People(allPeople);
    }

}
