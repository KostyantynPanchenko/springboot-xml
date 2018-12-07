package com.example.demo.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import nl.jqno.equalsverifier.EqualsVerifier;

public class PersonTest {

    @Test
    public void testEquals() {
        EqualsVerifier.forClass(Person.class).verify();
    }
    
    @Test
    public void testSetFirsName() {
        Person person = new Person();
        person.setFirstName("Mike");
        assertThat(person).hasFieldOrPropertyWithValue("firstName", "Mike");
    }
    
    @Test
    public void testSetLastName() {
        Person person = new Person();
        person.setLastName("Jordan");
        assertThat(person).hasFieldOrPropertyWithValue("lastName", "Jordan");
    }
    
    @Test
    public void testSetId() {
        Person person = new Person();
        person.setId(10L);
        assertThat(person).hasFieldOrPropertyWithValue("id", 10L);
    }
    
    @Test
    public void testToString() {
        Person jordan = new Person("Michael", "Jordan");
        jordan.setId(23L);
        String expected = "Person [id=23, firstName=Michael, lastName=Jordan]";
        assertThat(jordan.toString()).isEqualTo(expected);
    }

}
