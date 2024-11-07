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
@Table(name = "PROFESSOR")
public class Professor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "nome", nullable = false)
    private String nome;

}
