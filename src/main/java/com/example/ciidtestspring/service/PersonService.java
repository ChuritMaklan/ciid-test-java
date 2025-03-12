package com.example.ciidtestspring.service;

import com.example.ciidtestspring.dto.PersonRequest;
import com.example.ciidtestspring.entity.Person;
import com.example.ciidtestspring.entity.PersonType;
import com.example.ciidtestspring.repository.PersonRepository;
import com.example.ciidtestspring.repository.PersonTypeRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonService {
    private final PersonRepository personRepository;
    private final PersonTypeRepository personTypeRepository;

    public PersonService(PersonRepository personRepository, PersonTypeRepository personTypeRepository) {
        this.personRepository = personRepository;
        this.personTypeRepository = personTypeRepository;
    }

    public List<PersonRequest> getAllPersons() {
        List<Person> persons = personRepository.findAll();
        List<PersonRequest> personRequests = new ArrayList<>();
        for(Person person: persons){
            personRequests.add(personToPersonRequest(person));
        }
        return personRequests;
    }

    public PersonRequest getPersonById(Long id) {
        Person person = personRepository.findById(id).orElseThrow(() -> new RuntimeException("Person not found"));
        return personToPersonRequest(person);
    }
    private PersonRequest personToPersonRequest(Person person){
        PersonRequest personRequest = new PersonRequest();
        personRequest.setId(person.getId());
        personRequest.setName(person.getName());
        personRequest.setEmail(person.getEmail());
        personRequest.setPhone(person.getPhone());
        personRequest.setTypeId(person.getType().getId());
        return personRequest;
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

    public Person updatePerson(PersonRequest updatedPerson) {
        Person person = personRepository.findById(updatedPerson.getId()).orElseThrow(() -> new RuntimeException("Person not found"));
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