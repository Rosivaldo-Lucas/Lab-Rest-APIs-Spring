package com.ufpb.cursorest.laboratorio02.domain.models;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Disciplina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    private String nome;
    private Double nota;
    private String comentario;
    private Integer likes;

    public Disciplina() {
        this.nota = 0.0;
        this.comentario = "";
        this.likes = 0;
    }
}
