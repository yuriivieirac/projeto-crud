package com.projetospring.api_spring.repository;

import com.projetospring.api_spring.model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Integer> {
}
