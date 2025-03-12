package com.example.ciidtestspring.service;

import com.example.ciidtestspring.dto.PersonTypeRequest;
import com.example.ciidtestspring.entity.PersonType;
import com.example.ciidtestspring.repository.PersonTypeRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonTypeService {

    private final PersonTypeRepository personTypeRepository;

    public PersonTypeService(PersonTypeRepository personTypeRepository) {
        this.personTypeRepository = personTypeRepository;
    }

    public List<PersonTypeRequest> getAllPersonTypes() {
        List<PersonType> personTypes = personTypeRepository.findAll();
        List<PersonTypeRequest> personTypeRequests = new ArrayList<>();
        for(PersonType personType: personTypes){
            personTypeRequests.add(personTypeToPersonTypeRequest(personType));
        }
        return personTypeRequests;
    }

    public PersonTypeRequest getPersonTypeById(Long id) {
        return personTypeToPersonTypeRequest(personTypeRepository.findById(id).orElseThrow(() -> new RuntimeException("PersonType not found")));
    }
    public PersonTypeRequest personTypeToPersonTypeRequest(PersonType personType){
        PersonTypeRequest personTypeRequest = new PersonTypeRequest();
        personTypeRequest.setId(personType.getId());
        personTypeRequest.setTypeName(personType.getTypeName());
        return personTypeRequest;
    }

    public PersonType createPersonType(PersonType personType) {
        return personTypeRepository.save(personType);
    }

    public PersonType updatePersonType(PersonTypeRequest updatedPersonType) {
        PersonType personType = personTypeRepository.findById(updatedPersonType.getId()).orElseThrow(() -> new RuntimeException("PersonType not found"));
        personType.setTypeName(updatedPersonType.getTypeName());
        return personTypeRepository.save(personType);
    }
    public void deletePersonType(Long id) {
        personTypeRepository.deleteById(id);
    }
}