package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Person;
import com.example.demo.exception.PersonNotFoundException;
import com.example.demo.repository.PersonRepository;

@Service
public class PersonService {

    private PersonRepository personRepository;
    
    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }
    
    public Person findPersonById(long id) {
        Person person = personRepository.findById(id);
        if (person == null) {
            throw new PersonNotFoundException("Person with id = '" + id +"' not found");
        }
        return person;
    }

    public List<Person> findAll() {
        return personRepository.findAll();
    }
}
