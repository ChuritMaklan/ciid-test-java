package org.example.service;

import org.example.model.Part;
import org.example.repository.PartRepository;

import java.sql.SQLException;
import java.util.List;

public class PartService {
    private PartRepository partRepository = new PartRepository();

    // Add a new part
    public void addPart(String name, int personId, int price, String description, int quantityInStock, List<Integer> categoryIds) throws SQLException {
        Part part = new Part(name, personId, price, description, quantityInStock);
        partRepository.addPart(part, categoryIds);
    }

    // Get part by ID
    public Part getPartById(int id) throws SQLException {
        return partRepository.getPart(id);
    }

    // Update a part
    public void updatePart(int id, String name, int personId, int price, String description, int quantityInStock) throws SQLException {
        Part part = new Part(id, name, personId, price, description, quantityInStock);
        partRepository.updatePart(part);
    }

    // Delete a part
    public void deletePart(int id) throws SQLException {
        partRepository.deletePart(id);
    }

    // Get all parts
    public List<Part> getAllParts() throws SQLException {
        return partRepository.getAllParts();
    }

    // Get parts by category
    public List<Part> getPartsByCategory(int categoryId) throws SQLException {
        return partRepository.getPartsByCategory(categoryId);
    }
}
