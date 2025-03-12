package com.example.ciidtestspring.controller;

import com.example.ciidtestspring.dto.PersonTypeRequest;
import com.example.ciidtestspring.entity.PersonType;
import com.example.ciidtestspring.service.PersonTypeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person-types")
@CrossOrigin
public class PersonTypeController {
    private final PersonTypeService personTypeService;

    public PersonTypeController(PersonTypeService personTypeService) {
        this.personTypeService = personTypeService;
    }

    @GetMapping
    public List<PersonTypeRequest> getAllPersonTypes() {
        return personTypeService.getAllPersonTypes();
    }

    @GetMapping("/{id}")
    public PersonTypeRequest getPersonTypeById(@PathVariable Long id) {
        return personTypeService.getPersonTypeById(id);
    }

    @PostMapping
    public PersonType createPersonType(@RequestBody PersonType personType) {
        return personTypeService.createPersonType(personType);
    }

    @PutMapping("/{id}")
    public PersonType updatePersonType(@RequestBody PersonTypeRequest personTypeRequest) {
        return personTypeService.updatePersonType(personTypeRequest);
    }

    @DeleteMapping("/{id}")
    public void deletePersonType(@PathVariable Long id) {
        personTypeService.deletePersonType(id);
    }
}