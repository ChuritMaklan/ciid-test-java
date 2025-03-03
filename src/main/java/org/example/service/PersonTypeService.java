package org.example.service;

import org.example.model.PersonType;
import org.example.repository.PersonTypeRepository;

import java.sql.SQLException;
import java.util.List;

public class PersonTypeService {
    private PersonTypeRepository personTypeRepository = new PersonTypeRepository();

    // Add a new person type
    public void addPersonType(String typeName) throws SQLException {
        PersonType personType = new PersonType(typeName);
        personTypeRepository.addPersonType(personType);
    }

    // Get person type by ID
    public PersonType getPersonTypeById(int id) throws SQLException {
        return personTypeRepository.getPersonType(id);
    }

    // Update a person type
    public void updatePersonType(int id, String typeName) throws SQLException {
        PersonType personType = new PersonType(id, typeName);
        personTypeRepository.updatePersonType(personType);
    }

    // Delete a person type
    public void deletePersonType(int id) throws SQLException {
        personTypeRepository.deletePersonType(id);
    }

    // Get all person types
    public List<PersonType> getAllPersonTypes() throws SQLException {
        return personTypeRepository.getAllPersonTypes();
    }
}
