package com.example.demo.domain;

import java.util.List;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "people")
public class People {
    
    @JacksonXmlElementWrapper(localName = "persons")
    @JacksonXmlProperty(localName = "person")
    private List<Person> persons;

    public People() { }
    
    public People(List<Person> players) {
        this.persons = players;
    }

    public List<Person> getPersons() {
        return persons;
    }

}
