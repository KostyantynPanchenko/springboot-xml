package com.example.demo.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.annotation.TestConfig;
import com.example.demo.domain.Person;

@RunWith(SpringRunner.class)
@TestConfig
@ActiveProfiles("test")
public class PersonRepositoryTest {

    @Autowired
    private PersonRepository personRepository;
    
    @Test
    public void testFindByIdShouldReturnProperInstance() {
        Person jordan = personRepository.findById(10L);
        Person realJordan = createJordan();
        realJordan.setId(10L);
        assertThat(jordan).isEqualTo(realJordan);
    }
    
    private Person createJordan() {
        Person jordan = new Person("Michael", "Jordan");
        jordan.setId(10L);
        return jordan;
    }
    
    @Test
    public void testFindAllShouldReturnFourRecords() {
        List<Person> all = personRepository.findAll();
        assertThat(all).hasSize(4);
        assertThat(all.get(0)).isEqualTo(createJordan());
    }

}
