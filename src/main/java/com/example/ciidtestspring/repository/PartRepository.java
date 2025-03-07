package com.example.ciidtestspring.repository;

import com.example.ciidtestspring.entity.Part;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PartRepository extends JpaRepository<Part, Long> {
    List<Part> findByCategories_Id(Long categoryId);
}
