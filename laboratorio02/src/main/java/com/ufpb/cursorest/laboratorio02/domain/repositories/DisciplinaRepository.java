package com.ufpb.cursorest.laboratorio02.domain.repositories;

import com.ufpb.cursorest.laboratorio02.domain.models.Disciplina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository
public interface DisciplinaRepository<T, id extends Serializable> extends JpaRepository<Disciplina, Long> {
    List<Disciplina> findByOrderByNotaDesc();
    List<Disciplina> findByOrderByLikesDesc();
}
