package com.projetospring.api_spring.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "DISCIPLINA")
public class Disciplina {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "NOME", length = 50, nullable = false)
    private String nome;
}
