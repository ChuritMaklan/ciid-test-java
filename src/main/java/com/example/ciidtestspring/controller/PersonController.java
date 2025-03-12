package com.example.ciidtestspring.controller;

import com.example.ciidtestspring.dto.PersonRequest;
import com.example.ciidtestspring.entity.Person;
import com.example.ciidtestspring.service.PersonService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/persons")
public class PersonController {
    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public List<PersonRequest> getAllPersons() {
        return personService.getAllPersons();
    }

    @GetMapping("/{id}")
    public PersonRequest getPersonById(@PathVariable Long id) {
        return personService.getPersonById(id);
    }

    @PostMapping
    public Person createPerson(@RequestBody PersonRequest personRequest) {
        return personService.createPerson(personRequest);
    }

    @PutMapping(value = "/{id}")
    public Person updatePerson(@RequestBody PersonRequest personRequest) {
        return personService.updatePerson(personRequest);
    }

    @DeleteMapping("/{id}")
    public void deletePerson(@PathVariable Long id) {
        personService.deletePerson(id);
    }
}