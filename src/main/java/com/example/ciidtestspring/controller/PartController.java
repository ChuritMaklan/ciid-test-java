package com.example.ciidtestspring.controller;

import com.example.ciidtestspring.dto.PartRequest;
import com.example.ciidtestspring.entity.Part;
import com.example.ciidtestspring.entity.PersonType;
import com.example.ciidtestspring.service.PartService;
import com.example.ciidtestspring.service.PersonTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/parts")
public class PartController {
    @Autowired
    private PartService partService;

    @GetMapping
    public List<Part> getAllPart() {
        return partService.getAllParts();
    }

    @GetMapping("/{id}")
    public Part getPart(@PathVariable Long id) {
        return partService.getPartById(id);
    }
    @PostMapping
    public ResponseEntity<Part> createPart(@RequestBody PartRequest partRequest) {
        Part createdPart = partService.createPart(partRequest);
        return ResponseEntity.status(createdPart == null ? HttpStatus.BAD_REQUEST: HttpStatus.CREATED).body(createdPart);

    }


    @PutMapping("/{id}")
    public ResponseEntity<Part> updatePart(@PathVariable Long id, @RequestBody PartRequest partRequest) {
        Part updatedPart = partService.updatePart(id, partRequest);
        return ResponseEntity.status(updatedPart == null ? HttpStatus.BAD_REQUEST: HttpStatus.OK).body(updatedPart);
    }

    @DeleteMapping("/{id}")
    public void deletePart(@PathVariable Long id) {
        partService.deletePart(id);
    }
}