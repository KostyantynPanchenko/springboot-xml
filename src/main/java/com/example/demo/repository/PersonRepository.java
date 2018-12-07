package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

//import org.springframework.stereotype.Repository;

import com.example.demo.domain.Person;

@org.springframework.stereotype.Repository
public interface PersonRepository extends Repository<Person, Long> {

    @Query("SELECT p FROM Person p WHERE p.id = :id")
    Person findById(@Param(value = "id") long id);
    
    void save(Person person);

    List<Person> findAll();
}
