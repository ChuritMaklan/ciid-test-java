package com.example.ciidtestspring.controller;

import com.example.ciidtestspring.entity.PersonType;
import com.example.ciidtestspring.service.PersonTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person-types")
public class PersonTypeController {
    @Autowired
    private PersonTypeService personTypeService;

    @GetMapping
    public List<PersonType> getAllPersonTypes() {
        return personTypeService.getAllPersonTypes();
    }

    @GetMapping("/{id}")
    public PersonType getPersonTypeById(@PathVariable Long id) {
        return personTypeService.getPersonTypeById(id);
    }

    @PostMapping
    public PersonType createPersonType(@RequestBody PersonType personType) {
        return personTypeService.createPersonType(personType);
    }

    @PutMapping("/{id}")
    public PersonType updatePersonType(@PathVariable Long id, @RequestBody PersonType personType) {
        return personTypeService.updatePersonType(id, personType);
    }

    @DeleteMapping("/{id}")
    public void deletePersonType(@PathVariable Long id) {
        personTypeService.deletePersonType(id);
    }
}