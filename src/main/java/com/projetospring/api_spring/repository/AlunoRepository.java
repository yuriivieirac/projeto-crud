package com.projetospring.api_spring.repository;

import com.projetospring.api_spring.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Integer> {
}
