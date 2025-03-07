package com.example.ciidtestspring.repository;

import com.example.ciidtestspring.entity.PersonType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonTypeRepository extends JpaRepository<PersonType, Long> {
}
