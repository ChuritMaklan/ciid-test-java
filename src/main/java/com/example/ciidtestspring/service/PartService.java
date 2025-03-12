package com.example.ciidtestspring.service;

import com.example.ciidtestspring.PersonTypeEnum;
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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PartService {
    private final PartRepository partRepository;
    private final PersonRepository personRepository;
    private final PersonTypeService personTypeService;
    private final CategoryRepository categoryRepository;

    public PartService(PartRepository partRepository, PersonRepository personRepository, PersonTypeService personTypeService, CategoryRepository categoryRepository) {
        this.partRepository = partRepository;
        this.personRepository = personRepository;
        this.personTypeService = personTypeService;
        this.categoryRepository = categoryRepository;
    }

    public List<PartRequest> getAllParts() {

        List<Part> parts = partRepository.findAll();
        List<PartRequest> partRequests = new ArrayList<>();
        for(Part part: parts){
            partRequests.add(partToPartRequest(part));
        }
        return partRequests;
    }

    public PartRequest getPartById(Long id) {
        Part part =  partRepository.findById(id).orElseThrow(() -> new RuntimeException("Part not found"));
        return partToPartRequest(part);
    }
    private PartRequest partToPartRequest(Part part){
        PartRequest partRequest = new PartRequest();

        partRequest.setId(part.getId());
        partRequest.setName(part.getName());
        partRequest.setDescription(part.getDescription());
        partRequest.setPrice(part.getPrice());
        partRequest.setQuantityInStock(part.getQuantityInStock());
        partRequest.setPersonId(part.getPerson().getId());
        partRequest.setCategoryIds(part.getCategories().stream()
                .map(Category::getId)
                .collect(Collectors.toSet()));
        return partRequest;
    }
    @Transactional
    public Part createPart(PartRequest partRequest) {
        if (partRequest.getPersonId() == PersonTypeEnum.SUPPLIER.getId()){
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

    public Part updatePart(PartRequest updatedPart) {
        if (updatedPart.getPersonId() == PersonTypeEnum.SUPPLIER.getId()){
            return null;
        }
        Part part = partRepository.findById(updatedPart.getId()).orElseThrow(() -> new RuntimeException("Part not found"));
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