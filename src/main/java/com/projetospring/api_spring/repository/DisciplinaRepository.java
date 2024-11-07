package com.projetospring.api_spring.repository;

import com.projetospring.api_spring.model.Disciplina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DisciplinaRepository extends JpaRepository<Disciplina, Integer> {
}
