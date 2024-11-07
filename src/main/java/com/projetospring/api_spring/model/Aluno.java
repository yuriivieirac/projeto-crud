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
@Table(name = "ALUNO")
public class Aluno {

    //Metodo que contem a lógica para construir um objeto do tipo Aluno
    public void interfaceFluente(){
        Aluno aluno = Aluno.builder()
                .nome("")
                .endereco("")
                .telefone("")
                .build();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "NOME", length = 50, nullable = false)
    private String nome;

    @Column(name = "endereco", nullable = false)
    private String endereco;

    @Column(name = "telefone", nullable = false)
    private String telefone;

    }

