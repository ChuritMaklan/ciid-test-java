package com.example.ciidtestspring.repository;

import com.example.ciidtestspring.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
