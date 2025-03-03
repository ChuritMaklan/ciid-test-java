package org.example.service;

import org.example.model.Person;
import org.example.repository.PersonRepository;

import java.sql.SQLException;
import java.util.List;

public class PersonService {
    private PersonRepository personRepository = new PersonRepository();

    // Add a new person
    public void addPerson(String name, String email, String phone, int typeId) throws SQLException {
        Person person = new Person(name, email, phone, typeId);
        personRepository.addPerson(person);
    }

    // Get person by ID
    public Person getPersonById(int id) throws SQLException {
        return personRepository.getPerson(id);
    }

    // Update a person
    public void updatePerson(int id, String name, String email, String phone, int typeId) throws SQLException {
        Person person = new Person(id, name, email, phone, typeId);
        personRepository.updatePerson(person);
    }

    // Delete a person
    public void deletePerson(int id) throws SQLException {
        personRepository.deletePerson(id);
    }

    // Get all persons
    public List<Person> getAllPersons() throws SQLException {
        return personRepository.getAllPersons();
    }
}
