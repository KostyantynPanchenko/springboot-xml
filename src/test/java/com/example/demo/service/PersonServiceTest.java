package com.example.demo.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.Mockito.when;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.example.demo.domain.Person;
import com.example.demo.exception.PersonNotFoundException;
import com.example.demo.repository.PersonRepository;

@RunWith(MockitoJUnitRunner.class)
public class PersonServiceTest {

    @Mock
    private PersonRepository personRepository;
    
    @InjectMocks
    private PersonService personService;
    
    @Rule
    public ExpectedException thrown = ExpectedException.none();
    
    @Test
    public void testFindByIdSouldReturnProperPerson() {
        Person person = new Person("Michaels", "Jordan");
        person.setId(10L);
        
        when(personRepository.findById(1L)).thenReturn(person);
        Person actual = personService.findPersonById(1L);

        assertThat(person).isEqualTo(actual);
    }
    
    @Test
    public void testFindByIdShouldShrowExceptionWhenNotFound() {
        when(personRepository.findById(999L)).thenReturn(null);
        thrown.expect(PersonNotFoundException.class);
        thrown.expectMessage(containsString("Person with id = '999' not found"));
        personService.findPersonById(999L);
    }

}
