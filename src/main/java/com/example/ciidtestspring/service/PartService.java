package com.example.ciidtestspring.service;

import com.example.ciidtestspring.dto.PartRequest;
import com.example.ciidtestspring.entity.Part;
import com.example.ciidtestspring.entity.Category;
import com.example.ciidtestspring.entity.Person;
import com.example.ciidtestspring.repository.PartRepository;
import com.example.ciidtestspring.repository.CategoryRepository;
import com.example.ciidtestspring.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PartService {
    @Autowired
    private PartRepository partRepository;
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private PersonTypeService personTypeService;
    @Autowired
    private CategoryRepository categoryRepository;

    public List<Part> getAllParts() {
        return partRepository.findAll();
    }

    public Part getPartById(Long id) {
        return partRepository.findById(id).orElseThrow(() -> new RuntimeException("Part not found"));
    }
    @Transactional
    public Part createPart(PartRequest partRequest) {
        if (personTypeService.checkIfSupplier(partRequest.getPersonId())){
            return null;
        }
        Part part = new Part();
        part.setName(partRequest.getName());
        part.setDescription(partRequest.getDescription());
        part.setPrice(partRequest.getPrice());
        part.setQuantityInStock(partRequest.getQuantityInStock());
        Person person = personRepository.findById(partRequest.getPersonId())
                .orElseThrow(() -> new RuntimeException("Person not found with ID: " + partRequest.getPersonId()));
        part.setPerson(person);

        // Fetch all Categories by their IDs
        Set<Category> categories = partRequest.getCategoryIds().stream()
                .map(categoryId -> categoryRepository.findById(categoryId)
                        .orElseThrow(() -> new RuntimeException("Category not found with ID: " + categoryId)))
                .collect(Collectors.toSet());

        // Associate Categories with the Part
        part.setCategories(categories);

        // Save the Part
        return partRepository.save(part);
    }

    public Part updatePart(Long id, PartRequest updatedPart) {
        if (personTypeService.checkIfSupplier(updatedPart.getPersonId())){
            return null;
        }
        Part part = partRepository.findById(id).orElseThrow(() -> new RuntimeException("Part not found"));
        part.setName(updatedPart.getName());
        part.setPrice(updatedPart.getPrice());
        part.setQuantityInStock(updatedPart.getQuantityInStock());
        part.setDescription(updatedPart.getDescription());
        Person person = personRepository.findById(updatedPart.getPersonId())
                .orElseThrow(() -> new RuntimeException("Person not found with ID: " + updatedPart.getPersonId()));
        part.setPerson(person);
        Set<Category> categories = new HashSet<>(categoryRepository.findAllById(updatedPart.getCategoryIds()));
        part.setCategories(categories);
        return partRepository.save(part);
    }

    public void deletePart(Long id) {
        partRepository.deleteById(id);
    }

    public List<Long> getPartsByCategoryId(Long categoryId) {
        return partRepository.findByCategories_Id(categoryId)
                .stream()
                .map(Part::getId)
                .collect(Collectors.toList());
    }
}