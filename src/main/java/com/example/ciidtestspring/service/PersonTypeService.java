package com.example.ciidtestspring.service;

import com.example.ciidtestspring.entity.PersonType;
import com.example.ciidtestspring.repository.PersonTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonTypeService {
    private final long SUPPLIER_ID = 2;
    private final long CUSTOMER_ID = 1;
    @Autowired
    private PersonTypeRepository personTypeRepository;

    public List<PersonType> getAllPersonTypes() {
        return personTypeRepository.findAll();
    }

    public PersonType getPersonTypeById(Long id) {
        return personTypeRepository.findById(id).orElseThrow(() -> new RuntimeException("PersonType not found"));
    }

    public PersonType createPersonType(PersonType personType) {
        return personTypeRepository.save(personType);
    }

    public PersonType updatePersonType(Long id, PersonType updatedPersonType) {
        PersonType personType = personTypeRepository.findById(id).orElseThrow(() -> new RuntimeException("PersonType not found"));
        personType.setTypeName(updatedPersonType.getTypeName());
        return personTypeRepository.save(personType);
    }
    public boolean checkIfSupplier(long id){
        return id == SUPPLIER_ID;
    }
    public boolean checkIfCustomer(long id){
        return id == CUSTOMER_ID;
    }

    public void deletePersonType(Long id) {
        personTypeRepository.deleteById(id);
    }
}