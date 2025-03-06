package com.example.ciidjsp.service;

import com.example.ciidjsp.model.Person;
import com.example.ciidjsp.repository.PersonRepository;

import java.sql.SQLException;
import java.util.List;

public class PersonService {
    private PersonRepository personRepository = new PersonRepository();

    // Add a new person
    public void addPerson(String name, String email, String phone, int typeId) throws SQLException {
        Person person = new Person(email, phone, name, typeId);
        personRepository.addPerson(person);
    }

    // Get person by ID
    public Person getPersonById(int id) throws SQLException {
        return personRepository.getPerson(id);
    }

    // Update a person
    public void updatePerson(int id, String name, String email, String phone, int typeId) throws SQLException {
        Person person = new Person(id, email, phone, name, typeId);
        personRepository.updatePerson(person);
    }

    // Delete a person
    public void deletePerson(int id) throws SQLException {
        personRepository.deletePerson(id);
    }
    public List<Person> getAllSuppliers() throws SQLException {
        return personRepository.getAllSuppliers();
    }
    public List<Person> getAllCustomers() throws SQLException {
        return personRepository.getAllCustomers();
    }

    // Get all persons
    public List<Person> getAllPersons() throws SQLException {
        return personRepository.getAllPersons();
    }
}
