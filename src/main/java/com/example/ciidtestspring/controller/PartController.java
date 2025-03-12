package com.example.ciidtestspring.controller;

import com.example.ciidtestspring.dto.PartRequest;
import com.example.ciidtestspring.entity.Part;
import com.example.ciidtestspring.service.PartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/parts")
public class PartController {
    private final PartService partService;

    public PartController(PartService partService) {
        this.partService = partService;
    }

    @GetMapping
    public List<PartRequest> getAllPart() {
        return partService.getAllParts();
    }

    @GetMapping("/{id}")
    public PartRequest getPart(@PathVariable Long id) {
        return partService.getPartById(id);
    }
    @PostMapping
    public ResponseEntity<Part> createPart(@RequestBody PartRequest partRequest) {
        Part createdPart = partService.createPart(partRequest);
        return ResponseEntity.status(createdPart == null ? HttpStatus.BAD_REQUEST: HttpStatus.CREATED).body(createdPart);

    }


    @PutMapping("/{id}")
    public ResponseEntity<Part> updatePart(@RequestBody PartRequest partRequest) {
        Part updatedPart = partService.updatePart(partRequest);
        return ResponseEntity.status(updatedPart == null ? HttpStatus.BAD_REQUEST: HttpStatus.OK).body(updatedPart);
    }

    @DeleteMapping("/{id}")
    public void deletePart(@PathVariable Long id) {
        partService.deletePart(id);
    }
}