package com.example.ciidtestspring.service;

import com.example.ciidtestspring.dto.PersonRequest;
import com.example.ciidtestspring.entity.Part;
import com.example.ciidtestspring.entity.Person;
import com.example.ciidtestspring.entity.PersonType;
import com.example.ciidtestspring.repository.PersonRepository;
import com.example.ciidtestspring.repository.PersonTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private PersonTypeRepository personTypeRepository;

    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }

    public Person getPersonById(Long id) {
        return personRepository.findById(id).orElseThrow(() -> new RuntimeException("Person not found"));
    }

    public Person createPerson(PersonRequest personRequest) {
        Person person = new Person();
        person.setName(personRequest.getName());
        person.setEmail(personRequest.getEmail());
        person.setPhone(personRequest.getPhone());
        PersonType personType = personTypeRepository.findById(personRequest.getTypeId())
                .orElseThrow(() -> new RuntimeException("Part not found with ID: " + personRequest.getTypeId()));
        person.setType(personType);
        return personRepository.save(person);
    }

    public Person updatePerson(Long id, PersonRequest updatedPerson) {
        Person person = personRepository.findById(id).orElseThrow(() -> new RuntimeException("Person not found"));
        person.setName(updatedPerson.getName());
        person.setEmail(updatedPerson.getEmail());
        person.setPhone(updatedPerson.getPhone());
        PersonType personType = personTypeRepository.findById(updatedPerson.getTypeId())
                .orElseThrow(() -> new RuntimeException("Part not found with ID: " + updatedPerson.getTypeId()));
        person.setType(personType);
        return personRepository.save(person);
    }
    //public void

    public void deletePerson(Long id) {
        personRepository.deleteById(id);
    }
}